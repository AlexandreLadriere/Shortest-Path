package main.java.fr.alexandreladriere.shortestpath.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Implement the GUI
 */
public class Gui extends JPanel {
    private final JLabel titleLabel;
    private final JButton resetButton;
    private final JButton findPathButton;
    private final Controller controller;
    private int[][] matrix;
    private List<int[]> path;

    /**
     * Default constructor
     */
    public Gui() {
        this.setLayout(new BorderLayout());
        this.controller = new Controller(this);
        // North
        titleLabel = new JLabel("Select a starting point");
        this.add(titleLabel, BorderLayout.NORTH);

        // South
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        resetButton = new JButton("Reset");
        resetButton.addActionListener(controller);
        findPathButton = new JButton("Find shortest path");
        findPathButton.addActionListener(controller);
        southPanel.add(findPathButton, BorderLayout.EAST);
        southPanel.add(resetButton, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Get the Find-PathButton
     *
     * @return Find-Path button
     */
    public JButton getFindPathButton() {
        return findPathButton;
    }

    /**
     * Get the reset button
     *
     * @return Reset Button
     */
    public JButton getResetButton() {
        return resetButton;
    }
}
