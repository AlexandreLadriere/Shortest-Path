package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.maze.prims.Prims;
import main.java.fr.alexandreladriere.shortestpath.filehandler.FileHandler;
import main.java.fr.alexandreladriere.shortestpath.models.bfs.BreadthFirstSearch;
import main.java.fr.alexandreladriere.shortestpath.utils.Strings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Implement the controller
 */
public class Controller implements ActionListener {
    private final Gui gui;

    /**
     * Default constructor
     *
     * @param gui Gui
     */
    public Controller(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object cmd = actionEvent.getSource();
        // Compute the shortest path
        if (cmd.equals(gui.getFindPathButton()) && gui.getHasStartingPoint() && gui.getHasEndPoint()) {
            // BFS algorithm
            if (gui.getBfsRadioMenuItem().isSelected()) {
                long startTime = System.nanoTime();
                List<int[]> path = BreadthFirstSearch.shortestPath(gui.caseMatrixToIntMatrix(), gui.getUseDiag().getState());
                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / 1000000; // get in ms
                gui.setHasPath(true);
                gui.drawPath(path);
                gui.getPathInfo().setText("Path length = " + path.size() + "    |    Calculation time = " + duration + " ms");
            }
        }
        // Generate a maze according to the select algorithm
        if (cmd.equals(gui.getGenerateMazeButton())) {
            if (gui.getMazePrimRadioMenuItem().isSelected()) {
                int[][] maze = Prims.generateMaze(gui.getMatrix().length, gui.getMatrix()[0].length);
                gui.loadMatrix(maze);
            }
        }
        // reset the matrix/grid
        if (cmd.equals(gui.getResetButton())) {
            gui.resetMatrix();
        }
        if (cmd.equals(gui.getSaveAsMenuItem())) {
            String file = FileHandler.getSaveAsAbsoluteFilePath();
            if (file.endsWith(Strings.TXT_EXT)) {
                FileHandler.saveMatrixToFile(gui.caseMatrixToIntMatrix(), file);
            } else {
                JOptionPane.showConfirmDialog(gui, Strings.NO_TXT_MESSAGE + Strings.SAVE_ERR, Strings.NO_TXT_TITLE, JOptionPane.DEFAULT_OPTION);
            }
        }
        if (cmd.equals(gui.getLoadMenuItem())) {
            String file = FileHandler.getAbsoluteFilePath();
            if (file.endsWith(Strings.TXT_EXT)) {
                gui.loadMatrix(FileHandler.getMatrixFromFile(file));
            } else {
                JOptionPane.showConfirmDialog(gui, Strings.NO_TXT_MESSAGE + Strings.LOAD_ERR, Strings.NO_TXT_TITLE, JOptionPane.DEFAULT_OPTION);
            }
        }
        // display the popup dialog that requests new dimensions
        if (cmd.equals(gui.getChangeMatrixSizeMenuItem())) {
            Popup popupPanel = new Popup(gui.getMatrix().length, gui.getMatrix()[0].length);
            int rows = 0;
            int cols = 0;
            boolean cancel = false;
            while (rows < 2 || cols < 2 || rows * cols == 0) {
                if (JOptionPane.showConfirmDialog(null, popupPanel, Strings.CHANGE_SIZE, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    rows = Integer.parseInt(popupPanel.getRowNumberTextField().getText());
                    cols = Integer.parseInt(popupPanel.getColNumberTextField().getText());
                } else {
                    cancel = true;
                    break;
                }
            }
            if (!cancel) {
                gui.changeGridSize(rows, cols);
            }
        }
        if (cmd.equals(gui.getHelpMenuItem())) {
            JOptionPane.showMessageDialog(null, Strings.HELP_CONTENT);
        }
    }
}
