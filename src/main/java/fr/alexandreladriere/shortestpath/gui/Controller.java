package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.shortestpath.models.bfs.BreadthFirstSearch;

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
            List<int[]> path = BreadthFirstSearch.shortestPath(gui.caseMatrixToIntMatrix(), true);
            gui.setHasPath(true);
            gui.drawPath(path);
        }
        if (cmd.equals(gui.getResetButton())) {
            gui.resetMatrix();
        }
    }
}
