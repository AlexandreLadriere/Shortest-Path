package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.shortestpath.utils.Colors;
import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

import javax.swing.*;
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
        boolean rClicked = SwingUtilities.isRightMouseButton(mouseEvent);
        // If right click set the Case to Empty
        if (rClicked && !gui.getHasPath()) {
            gui.getMatrix()[x][y].setBackgroundColor(Colors.DEFAULT_COLOR);
            // if we remove the starting point
            if (gui.getMatrix()[x][y].getValue() == Constants.START) {
                gui.setHasStartingPoint(false);
            }
            // if we remove the end point
            if (gui.getMatrix()[x][y].getValue() == Constants.END) {
                gui.setHasEndPoint(false);
            }
            gui.getMatrix()[x][y].setValue(Constants.EMPTY);
            gui.setRClicked(true);
        }
        if (!rClicked) {
            // set the starting point
            if (gui.getStartRadio().isSelected() && !gui.getHasStartingPoint()) {
                gui.getMatrix()[x][y].setBackgroundColor(Colors.START_COLOR);
                gui.getMatrix()[x][y].setValue(Constants.START);
                gui.setHasStartingPoint(true);
            }
            // set the end point
            if (gui.getEndRadio().isSelected() && !gui.getHasEndPoint()) {
                gui.getMatrix()[x][y].setBackgroundColor(Colors.END_COLOR);
                gui.getMatrix()[x][y].setValue(Constants.END);
                gui.setHasEndPoint(true);
            }
            // set the obstacles
            if (gui.getObstacleRadio().isSelected() && !gui.getHasPath()) {
                gui.getMatrix()[x][y].setBackgroundColor(Colors.OBSTACLE_COLOR);
                gui.getMatrix()[x][y].setValue(Constants.OBSTACLE);
            }
            gui.setClicked(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (gui.getRClicked()) {
            gui.setRClicked(false);
        }
        if (gui.getClicked()) {
            gui.setClicked(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (gui.getClicked() && gui.getObstacleRadio().isSelected() && !gui.getHasPath()) {
            // if we remove the starting point
            if (gui.getMatrix()[x][y].getValue() == Constants.START) {
                gui.setHasStartingPoint(false);
            }
            // if we remove the end point
            if (gui.getMatrix()[x][y].getValue() == Constants.END) {
                gui.setHasEndPoint(false);
            }
            gui.getMatrix()[x][y].setBackgroundColor(Colors.OBSTACLE_COLOR);
            gui.getMatrix()[x][y].setValue(Constants.OBSTACLE);
        }
        if (gui.getRClicked() && !gui.getHasPath()) {
            gui.getMatrix()[x][y].setBackgroundColor(Colors.DEFAULT_COLOR);
            gui.getMatrix()[x][y].setValue(Constants.EMPTY);
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
