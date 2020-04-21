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
    private final int value;
    private final Gui gui;

    public Case(Gui gui, int x, int y, int value) {
        this.gui = gui;
        this.x = x;
        this.y = y;
        this.value = value;
        this.setBackground(new Color(0, 0, 255));
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setPreferredSize(new Dimension(Constants.DIM_CASE, Constants.DIM_CASE));
    }
}
