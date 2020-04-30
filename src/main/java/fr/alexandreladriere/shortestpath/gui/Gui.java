package main.java.fr.alexandreladriere.shortestpath.gui;

import main.java.fr.alexandreladriere.shortestpath.utils.Colors;
import main.java.fr.alexandreladriere.shortestpath.utils.Constants;
import main.java.fr.alexandreladriere.shortestpath.utils.Strings;

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
    private final JButton generateMazeButton;
    private final JLabel pathInfo;
    private final Controller controller;
    private final JFrame parent;
    private final JPanel matrixGridPanel;
    private boolean clicked = false;
    private boolean rClicked = false;
    private boolean hasStartingPoint;
    private boolean hasEndPoint;
    private boolean hasPath;
    private JMenuItem helpMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem loadMenuItem;
    private Case[][] matrix;
    private JRadioButtonMenuItem bfsRadioMenuItem;
    private JRadioButtonMenuItem mazePrimRadioMenuItem;
    private JCheckBoxMenuItem useDiag;
    private JMenuBar menuBar;
    private JMenuItem changeMatrixSizeMenuItem;


    /**
     * Default constructor
     *
     * @param parent Parent frame
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
        northPanel.setBackground(Colors.BORDER_COLOR);
        startRadio = new JRadioButton(Strings.STARTING_POINT);
        startRadio.setBackground(Colors.BORDER_COLOR);
        startRadio.setForeground(Colors.TEXT_COLOR);
        startRadio.setSelected(true);
        endRadio = new JRadioButton(Strings.END_POINT);
        endRadio.setBackground(Colors.BORDER_COLOR);
        endRadio.setForeground(Colors.TEXT_COLOR);
        endRadio.setSelected(false);
        obstacleRadio = new JRadioButton(Strings.OBSTACLES);
        obstacleRadio.setBackground(Colors.BORDER_COLOR);
        obstacleRadio.setForeground(Colors.TEXT_COLOR);
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
        southPanel.setLayout(new BorderLayout());
        pathInfo = new JLabel(" ", SwingConstants.CENTER);
        pathInfo.setBackground(Colors.BORDER_COLOR);
        pathInfo.setForeground(Colors.TEXT_COLOR);
        JPanel subCenterPanel = new JPanel();
        subCenterPanel.setBackground(Colors.BORDER_COLOR);
        resetButton = new JButton(Strings.RESET);
        resetButton.addActionListener(controller);
        resetButton.setBorderPainted(false);
        resetButton.setBackground(Colors.END_COLOR);
        resetButton.setForeground(Colors.TEXT_COLOR);
        resetButton.setPreferredSize(new Dimension(140, 25));
        findPathButton = new JButton(Strings.FIND);
        findPathButton.addActionListener(controller);
        findPathButton.setBorderPainted(false);
        findPathButton.setBackground(Colors.START_COLOR);
        findPathButton.setForeground(Colors.TEXT_COLOR);
        findPathButton.setPreferredSize(new Dimension(140, 25));
        generateMazeButton = new JButton(Strings.GENERATE_MAZE);
        generateMazeButton.addActionListener(controller);
        generateMazeButton.setBorderPainted(false);
        generateMazeButton.setBackground(Colors.DEFAULT_COLOR);
        generateMazeButton.setForeground(Colors.TEXT_COLOR);
        generateMazeButton.setPreferredSize(new Dimension(140, 25));
        subCenterPanel.add(resetButton);
        subCenterPanel.add(generateMazeButton);
        subCenterPanel.add(findPathButton);
        southPanel.add(pathInfo, BorderLayout.NORTH);
        southPanel.add(subCenterPanel, BorderLayout.CENTER);
        southPanel.setBackground(Colors.BORDER_COLOR);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Initialize the menu bar
     */
    private void initMenu() {
        // Menu bar
        menuBar = new JMenuBar();
        // File Menu
        JMenu fileMenu = new JMenu(Strings.FILE);
        saveAsMenuItem = new JMenuItem(Strings.SAVE_AS);
        saveAsMenuItem.addActionListener(controller);
        loadMenuItem = new JMenuItem(Strings.LOAD);
        loadMenuItem.addActionListener(controller);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(loadMenuItem);
        // More menu
        JMenu moreMenu = new JMenu(Strings.MORE);
        helpMenuItem = new JMenuItem(Strings.HELP);
        helpMenuItem.addActionListener(controller);
        moreMenu.add(helpMenuItem);
        // Settings menu
        JMenu settingsMenu = new JMenu(Strings.SETTINGS);
        // Algorithms submenu
        JMenu algoSubMenu = new JMenu(Strings.ALGO);
        ButtonGroup bgMenu = new ButtonGroup();
        bfsRadioMenuItem = new JRadioButtonMenuItem(Strings.ALGO_BFS);
        bfsRadioMenuItem.setSelected(true);
        bgMenu.add(bfsRadioMenuItem);
        algoSubMenu.add(bfsRadioMenuItem);
        // Maze algorithms sub menu
        JMenu mazeAlgoSubMenu = new JMenu(Strings.MAZE_ALGO);
        ButtonGroup mazeBgMenu = new ButtonGroup();
        mazePrimRadioMenuItem = new JRadioButtonMenuItem(Strings.ALGO_RAND_PRIM);
        mazePrimRadioMenuItem.setSelected(true);
        mazeBgMenu.add(mazePrimRadioMenuItem);
        mazeAlgoSubMenu.add(mazePrimRadioMenuItem);
        // useDiag menu check box
        useDiag = new JCheckBoxMenuItem(Strings.USE_DIAG);
        useDiag.setSelected(false);
        // Change Matrix size menu item
        changeMatrixSizeMenuItem = new JMenuItem(Strings.CHANGE_SIZE);
        changeMatrixSizeMenuItem.addActionListener(controller);
        // Adding to menu
        settingsMenu.add(algoSubMenu);
        settingsMenu.addSeparator();
        settingsMenu.add(mazeAlgoSubMenu);
        settingsMenu.addSeparator();
        settingsMenu.add(useDiag);
        settingsMenu.addSeparator();
        settingsMenu.add(changeMatrixSizeMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);
        // menuBar.add(Box.createGlue());
        menuBar.add(moreMenu);
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
        matrixGridPanel.removeAll();
        matrixGridPanel.setLayout(new GridLayout(rows, cols));
        matrix = new Case[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new Case(this, i, j, Constants.EMPTY);
                matrix[i][j].addMouseListener(new MouseController(this, i, j));
                matrixGridPanel.add(matrix[i][j]);
            }
        }
        parent.pack();
    }

    /**
     * Load a new pre-defined matrix to the grid panel
     *
     * @param newMatrix New matrix that you want to load
     */
    public void loadMatrix(int[][] newMatrix) {
        resetBool();
        matrixGridPanel.removeAll();
        matrixGridPanel.setLayout(new GridLayout(newMatrix.length, newMatrix[0].length));
        matrix = new Case[newMatrix.length][newMatrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int value = newMatrix[i][j];
                matrix[i][j] = new Case(this, i, j, value);
                if (value == Constants.START) {
                    matrix[i][j].setBackgroundColor(Colors.START_COLOR);
                    hasStartingPoint = true;
                }
                if (value == Constants.END) {
                    matrix[i][j].setBackgroundColor(Colors.END_COLOR);
                    hasEndPoint = true;
                }
                if (value == Constants.OBSTACLE) {
                    matrix[i][j].setBackgroundColor(Colors.OBSTACLE_COLOR);
                }
                matrix[i][j].addMouseListener(new MouseController(this, i, j));
                matrixGridPanel.add(matrix[i][j]);
            }
        }
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
        rClicked = false;
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
     * Get the "right clicked" boolean
     *
     * @return "Right clicked" boolean
     */
    public boolean getRClicked() {
        return rClicked;
    }

    /**
     * Set the "right clicked" boolean
     *
     * @param rClicked New value for "rClicked" bool
     */
    public void setRClicked(boolean rClicked) {
        this.rClicked = rClicked;
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
     * Get the Randomized Prim's algorithm RadioMenuItem
     *
     * @return Randomized Prim's algorithm RadioMenuItem
     */
    public JRadioButtonMenuItem getMazePrimRadioMenuItem() {
        return mazePrimRadioMenuItem;
    }

    /**
     * Get the JMenuItem corresponding to the "change matrix size" setting
     *
     * @return JMenuItem corresponding to the "change matrix size" setting
     */
    public JMenuItem getChangeMatrixSizeMenuItem() {
        return changeMatrixSizeMenuItem;
    }

    /**
     * Get the Jlabel that displays path information
     *
     * @return Jlabel that displays path information
     */
    public JLabel getPathInfo() {
        return pathInfo;
    }

    /**
     * Get the "Help" menu item
     *
     * @return "Help" menu item object
     */
    public JMenuItem getHelpMenuItem() {
        return helpMenuItem;
    }

    /**
     * Get the "Save as" menu item
     *
     * @return "Save as" menu item
     */
    public JMenuItem getSaveAsMenuItem() {
        return saveAsMenuItem;
    }

    /**
     * Get the "Load" menu item
     *
     * @return "Load" menu item
     */
    public JMenuItem getLoadMenuItem() {
        return loadMenuItem;
    }

    /**
     * Get the "Generate maze" button
     *
     * @return The "Generate maze" button
     */
    public JButton getGenerateMazeButton() {
        return generateMazeButton;
    }
}
