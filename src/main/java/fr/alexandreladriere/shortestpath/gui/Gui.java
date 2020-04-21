package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Implement the GUI
 */
public class Gui extends JPanel {
    private final JRadioButton startRadio;
    private final JRadioButton endRadio;
    private final JRadioButton obstacleRadio;
    private final JButton resetButton;
    private final JButton findPathButton;
    private final Controller controller;
    private final JPanel matrixGridPanel;
    private final Case[][] matrix;
    private List<int[]> path;
    private boolean clicked = false;
    private boolean hasStartingPoint;
    private boolean hasEndPoint;

    /**
     * Default constructor
     */
    public Gui() {
        this.setLayout(new BorderLayout());
        this.controller = new Controller(this);
        hasEndPoint = false;
        hasStartingPoint = false;
        // North
        JPanel northPanel = new JPanel();
        startRadio = new JRadioButton("Starting point");
        startRadio.setSelected(true);
        endRadio = new JRadioButton("End point");
        endRadio.setSelected(false);
        obstacleRadio = new JRadioButton("Obstacles");
        obstacleRadio.setSelected(false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(startRadio);
        bg.add(endRadio);
        bg.add(obstacleRadio);
        northPanel.add(startRadio, BorderLayout.WEST);
        northPanel.add(endRadio, BorderLayout.CENTER);
        northPanel.add(obstacleRadio, BorderLayout.EAST);
        this.add(northPanel, BorderLayout.NORTH);

        // Center
        matrixGridPanel = new JPanel();
        matrixGridPanel.setLayout(new GridLayout(Constants.DEFAULT_MATRIX_ROW, Constants.DEFAULT_MATRIX_COL)); // default
        matrix = new Case[Constants.DEFAULT_MATRIX_ROW][Constants.DEFAULT_MATRIX_COL];
        for (int i = 0; i < Constants.DEFAULT_MATRIX_ROW; i++) {
            for (int j = 0; j < Constants.DEFAULT_MATRIX_COL; j++) {
                matrix[i][j] = new Case(this, i, j, Constants.EMPTY);
                matrix[i][j].addMouseListener(new MouseController(this, i, j));
                matrixGridPanel.add(matrix[i][j]);
            }
        }
        this.add(matrixGridPanel, BorderLayout.CENTER);

        // South
        JPanel southPanel = new JPanel();
        resetButton = new JButton("Reset");
        resetButton.addActionListener(controller);
        findPathButton = new JButton("Find shortest path");
        findPathButton.addActionListener(controller);
        southPanel.add(resetButton);
        southPanel.add(findPathButton);
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

    /**
     * Get the "endRadioButton"
     *
     * @return "endRadioButton" radio button
     */
    public JRadioButton getEndRadio() {
        return endRadio;
    }

    /**
     * Get the "obstacleRadioButton"
     *
     * @return "obstacleRadioButton" radio button
     */
    public JRadioButton getObstacleRadio() {
        return obstacleRadio;
    }

    /**
     * Get the "startRadioButton"
     *
     * @return "startRadioButton" Radio button
     */
    public JRadioButton getStartRadio() {
        return startRadio;
    }

    /**
     * Get the gui matrix
     *
     * @return Case matrix
     */
    public Case[][] getMatrix() {
        return matrix;
    }

    /**
     * Get the boolean value indicating if the matrix grid has a starting point or not
     *
     * @return boolean value indicating if the matrix grid has a starting point or not
     */
    public boolean getHasStartingPoint() {
        return hasStartingPoint;
    }

    /**
     * Set the new boolean value to indicate if the grid has a starting point or not
     *
     * @param hasStartingPoint New value to indicate if the grid has a starting point (true: yes; false: no)
     */
    public void setHasStartingPoint(boolean hasStartingPoint) {
        this.hasStartingPoint = hasStartingPoint;
    }

    /**
     * Get the boolean value indicating if the matrix grid has an end point or not
     *
     * @return Boolean value indicating if the matrix grid has an end point or not
     */
    public boolean getHasEndPoint() {
        return hasEndPoint;
    }

    /**
     * Set the new boolean value to indicate if the grid has an end point or not
     *
     * @param hasEndPoint New value to indicate if the grid has an end point (true: yes; false: no)
     */
    public void setHasEndPoint(boolean hasEndPoint) {
        this.hasEndPoint = hasEndPoint;
    }
}
