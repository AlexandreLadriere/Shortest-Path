package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.shortestpath.utils.Colors;
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
    private final JFrame parent;
    private JPanel matrixGridPanel;
    private boolean clicked = false;
    private boolean hasStartingPoint;
    private boolean hasEndPoint;
    private boolean hasPath;
    private JMenuItem helpMenu;
    private Case[][] matrix;
    private JRadioButtonMenuItem bfsRadioMenuItem;
    private JCheckBoxMenuItem useDiag;
    private JMenuBar menuBar;
    private JMenuItem changeMatrixSizeMenuItem;


    /**
     * Default constructor
     */
    public Gui(JFrame parent) {
        this.parent = parent;
        this.setLayout(new BorderLayout());
        this.controller = new Controller(this);
        hasEndPoint = false;
        hasStartingPoint = false;
        hasPath = false;
        initMenu();
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
     * Initialize the menu bar
     */
    private void initMenu() {
        // Menu bar
        menuBar = new JMenuBar();
        helpMenu = new JMenu("Help");
        helpMenu.addActionListener(controller);
        // Settings menu
        JMenu settingsMenu = new JMenu("Settings");
        // Algorithms submenu
        JMenu algoSubMenu = new JMenu("Algorithms");
        ButtonGroup bgMenu = new ButtonGroup();
        bfsRadioMenuItem = new JRadioButtonMenuItem("Breadth-first Search (BFS)");
        bfsRadioMenuItem.setSelected(true);
        bgMenu.add(bfsRadioMenuItem);
        algoSubMenu.add(bfsRadioMenuItem);
        // useDiag menu check box
        useDiag = new JCheckBoxMenuItem("Allow diagonal moves");
        useDiag.setSelected(false);
        // Change Matrix size menu item
        changeMatrixSizeMenuItem = new JMenuItem("Change the matrix size");
        changeMatrixSizeMenuItem.addActionListener(controller);
        // Adding to menu
        settingsMenu.add(algoSubMenu);
        settingsMenu.addSeparator();
        settingsMenu.add(useDiag);
        settingsMenu.addSeparator();
        settingsMenu.add(changeMatrixSizeMenuItem);
        menuBar.add(settingsMenu);
        // menuBar.add(Box.createGlue());
        menuBar.add(helpMenu);
    }

    /**
     * Convert the Case matrix into an int matrix
     *
     * @return Integer matrix representation of the gui Case matrix
     */
    public int[][] caseMatrixToIntMatrix() {
        int[][] matrixInt = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrixInt[i][j] = matrix[i][j].getValue();
            }
        }
        return matrixInt;
    }

    /**
     * Draw the path in the matrix grid
     *
     * @param path Path that you want to draw
     */
    public void drawPath(List<int[]> path) {
        for (int[] ints : path) {
            if (matrix[ints[0]][ints[1]].getValue() != Constants.START && matrix[ints[0]][ints[1]].getValue() != Constants.END) {
                matrix[ints[0]][ints[1]].setBackgroundColor(Colors.PATH_COLOR);
            }
        }
    }

    /**
     * Change the grid size of the gui
     *
     * @param rows New number of rows
     * @param cols New number of columns
     */
    public void changeGridSize(int rows, int cols) {
        resetBool();
        matrixGridPanel = new JPanel();
        matrixGridPanel.setLayout(new GridLayout(rows, cols));
        matrix = new Case[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new Case(this, i, j, Constants.EMPTY);
                matrix[i][j].addMouseListener(new MouseController(this, i, j));
                matrixGridPanel.add(matrix[i][j]);
            }
        }
        this.add(matrixGridPanel, BorderLayout.CENTER);
        parent.pack();
    }

    /**
     * Reset all useful booleans
     */
    private void resetBool() {
        hasStartingPoint = false;
        hasEndPoint = false;
        hasPath = false;
        clicked = false;
        startRadio.setSelected(true);
        endRadio.setSelected(false);
        obstacleRadio.setSelected(false);
    }

    /**
     * Reset the matrix
     */
    public void resetMatrix() {
        resetBool();
        for (Case[] cases : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                cases[j].setValue(Constants.EMPTY);
                cases[j].setBackgroundColor(Colors.DEFAULT_COLOR);
            }
        }
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

    /**
     * Get a boolean that indicates if the shortest path was already calculated or not
     *
     * @return boolean that indicates if the shortest path was already calculated or not
     */
    public boolean getHasPath() {
        return hasPath;
    }

    /**
     * Set the boolean value that indicates if a path was already calculated or not
     *
     * @param hasPath New boolean value that indicates if a path was already calculated or not (true: yes; false: no)
     */
    public void setHasPath(boolean hasPath) {
        this.hasPath = hasPath;
    }

    /**
     * Get the menu bar object
     *
     * @return JMenuBar
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Get the "useDiag" check box menu item
     *
     * @return "useDiag" check box menu item
     */
    public JCheckBoxMenuItem getUseDiag() {
        return useDiag;
    }

    /**
     * Get the BFS RadioMenuItem
     *
     * @return BFS RadioMenuItem
     */
    public JRadioButtonMenuItem getBfsRadioMenuItem() {
        return bfsRadioMenuItem;
    }

    /**
     * Get the JMenuItem corresponding to the "change matrix size" setting
     *
     * @return JMenuItem corresponding to the "change matrix size" setting
     */
    public JMenuItem getChangeMatrixSizeMenuItem() {
        return changeMatrixSizeMenuItem;
    }
}
