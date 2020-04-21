package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Implement the mouse controller for the matrix grid layout
 */
public class MouseController implements MouseListener {
    private final Gui gui;
    private final int x;
    private final int y;

    /**
     * Default constructor
     *
     * @param gui Gui
     * @param x   X coordinate of the associated Case in the Gui matrix grid
     * @param y   Y coordinate of the associated Case in the Gui matrix grid
     */
    public MouseController(Gui gui, int x, int y) {
        this.gui = gui;
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (gui.getStartRadio().isSelected() && !gui.getHasStartingPoint()) {
            gui.getMatrix()[x][y].setBackgroundColor(Constants.START_COLOR);
            gui.getMatrix()[x][y].setValue(Constants.START);
            gui.setHasStartingPoint(true);
        }
        if (gui.getEndRadio().isSelected() && !gui.getHasEndPoint()) {
            gui.getMatrix()[x][y].setBackgroundColor(Constants.END_COLOR);
            gui.getMatrix()[x][y].setValue(Constants.END);
            gui.setHasEndPoint(true);
        }
        if (gui.getObstacleRadio().isSelected()) {
            gui.getMatrix()[x][y].setBackgroundColor(Constants.OBSTACLE_COLOR);
            gui.getMatrix()[x][y].setValue(Constants.OBSTACLE);
        }
        gui.setClicked(true);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        gui.setClicked(false);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (gui.getClicked() && gui.getObstacleRadio().isSelected()) {
            gui.getMatrix()[x][y].setBackgroundColor(Constants.OBSTACLE_COLOR);
            gui.getMatrix()[x][y].setValue(Constants.OBSTACLE);
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
