package main.java.fr.alexandreladriere.maze.prims;

import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

/**
 * Implement a Cell object for the maze
 *
 * @see Constants
 */
public class Cell {
    private final int x;
    private final int y;
    private int value;

    /**
     * Default constructor: initialize cell to wall
     *
     * @param x X coordinate of the cell in the maze
     * @param y Y coordinate of the cell in the maze
     */
    public Cell(int x, int y) {
        value = Constants.OBSTACLE;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the value of the cell
     *
     * @return Value of the cell
     * @see Constants
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the value of the cell
     *
     * @param value New value of the cell
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Get the X coordinate of the cell in the maze
     *
     * @return X coordinate of the cell in the maze
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Y coordinate of the cell in the maze
     *
     * @return Y coordinate of the cell in the maze
     */
    public int getY() {
        return y;
    }
}
