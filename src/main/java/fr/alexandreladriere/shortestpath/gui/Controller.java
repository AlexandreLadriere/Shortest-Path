package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.shortestpath.models.bfs.BreadthFirstSearch;

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
        if (cmd.equals(gui.getFindPathButton()) && gui.getHasStartingPoint() && gui.getHasEndPoint()) {
            if (gui.getBfsRadioMenuItem().isSelected()) {
                List<int[]> path = BreadthFirstSearch.shortestPath(gui.caseMatrixToIntMatrix(), gui.getUseDiag().getState());
                gui.setHasPath(true);
                gui.drawPath(path);
            }
        }
        if (cmd.equals(gui.getResetButton())) {
            gui.resetMatrix();
        }
        if (cmd.equals(gui.getChangeMatrixSizeMenuItem())) {
            Popup popupPanel = new Popup(gui.getMatrix().length, gui.getMatrix()[0].length);
            int rows = 0;
            int cols = 0;
            boolean cancel = false;
            while (rows < 2 || cols < 2 || rows * cols == 0) {
                if (JOptionPane.showConfirmDialog(null, popupPanel, "Change matrix size", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
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
    }
}
