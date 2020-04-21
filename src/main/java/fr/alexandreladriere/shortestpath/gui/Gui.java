package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

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
    private final JPanel matrixGridPanel;
    private final Case[][] matrix;
    private List<int[]> path;
    private boolean clicked = false;

    /**
     * Default constructor
     */
    public Gui() {
        this.setLayout(new BorderLayout());
        this.controller = new Controller(this);
        // North
        titleLabel = new JLabel("Select a starting point");
        this.add(titleLabel, BorderLayout.NORTH);

        // Center
        matrixGridPanel = new JPanel();
        matrixGridPanel.setLayout(new GridLayout(Constants.DEFAULT_MATRIX_ROW, Constants.DEFAULT_MATRIX_COL)); // default
        matrix = new Case[Constants.DEFAULT_MATRIX_ROW][Constants.DEFAULT_MATRIX_COL];
        for (int i = 0; i < Constants.DEFAULT_MATRIX_ROW; i++) {
            for (int j = 0; j < Constants.DEFAULT_MATRIX_COL; j++) {
                matrix[i][j] = new Case(this, i, j, Constants.EMPTY);
                matrix[i][j].addMouseListener(new MouseController(this));
                matrixGridPanel.add(matrix[i][j]);
            }
        }
        this.add(matrixGridPanel, BorderLayout.CENTER);

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

    /**
     * Get the "clicked" boolean
     *
     * @return "Clicked" boolean
     */
    public boolean getClicked() {
        return clicked;
    }

    /**
     * Set the "clicked" boolean
     *
     * @param clicked New value for "clicked" bool
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
