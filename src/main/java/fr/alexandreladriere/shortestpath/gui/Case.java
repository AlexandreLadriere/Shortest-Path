package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Implement a Case in the Matrix grid layout
 */
public class Case extends JPanel implements MouseListener {

    private final int x;
    private final int y;
    private final int value;
    private final Gui gui;

    public Case(Gui gui, int x, int y, int value) {
        this.gui = gui;
        this.x = x;
        this.y = y;
        this.value = value;
        setPreferredSize(new Dimension(Constants.DIM_CASE, Constants.DIM_CASE));
        addMouseListener(this);
        this.setBackground(new Color(0, 0, 255));
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
