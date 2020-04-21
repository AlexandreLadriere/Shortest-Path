package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Implement a Case in the Matrix grid layout
 */
public class Case extends JPanel {

    private final int x;
    private final int y;
    private int value;
    private final Gui gui;

    /**
     * Default constructor
     *
     * @param gui   Gui
     * @param x     X coordinate of the case in the Gui matrix grid
     * @param y     Y coordinate of the case in the Gui matrix grid
     * @param value Value of the Case
     */
    public Case(Gui gui, int x, int y, int value) {
        this.gui = gui;
        this.x = x;
        this.y = y;
        this.value = value;
        setBackgroundColor(Constants.DEFAULT_COLOR);
        this.setBorder(BorderFactory.createLineBorder(Constants.BORDER_COLOR));
        setPreferredSize(new Dimension(Constants.DIM_CASE, Constants.DIM_CASE));
    }

    /**
     * Set the background color
     *
     * @param color New background color
     */
    public void setBackgroundColor(Color color) {
        this.setBackground(color);
    }

    /**
     * Get the value of the case
     *
     * @return Value of the case
     */
    public int getValue() {
        return value;
    }

    /**
     * Set a value for the case
     *
     * @param value New value
     */
    public void setValue(int value) {
        this.value = value;
    }
}