package main.java.fr.alexandreladriere.maze.prims;

import main.java.fr.alexandreladriere.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implement the Prim's algorithm to generate a maze
 * see: https://stackoverflow.com/a/29758926/13247140
 */
public final class Prims {
    private static final Random rand = new Random();
    private static final List<Cell> frontiersList = new ArrayList<>();
    private static Cell[][] maze;

    /**
     * Generate a maze with the given dimensions
     *
     * @param xDim X Dimension of the maze that you want to create
     * @param yDim Y Dimension of the maze that you want to create
     * @return Maze with the given dimensions.
     */
    public static int[][] generateMaze(int xDim, int yDim) {
        maze = initMaze(xDim, yDim);
        initFrontierList();
        while (!frontiersList.isEmpty()) {
            // Pick a random frontier cell from the list of frontier cells
            int randFrontierIndex = rand.nextInt(frontiersList.size());
            Cell randFrontier = frontiersList.get(randFrontierIndex);
            // Get the list of its valid neighbors
            List<Cell> neighborsList = getNeighbors(randFrontier);
            // Pick a random neighbor
            if (!neighborsList.isEmpty()) {
                int randNeighborIndex = rand.nextInt(neighborsList.size());
                Cell randNeighbor = neighborsList.get(randNeighborIndex);
                // Connect the frontier cell with the neighbor by setting the cell in-between to state Empty
                connectCells(randFrontier, randNeighbor);
            }
            // Compute the frontier cells of the chosen frontier cell and add them to the frontier list.
            addFrontiers(randFrontier);
            // Remove the chosen frontier cell from the list of frontier cells.
            randFrontier.setValue(Constants.EMPTY);
            frontiersList.remove(randFrontier);
        }
        return cellGridToIntGrid(maze);
    }

    /**
     * Connect two cells in the maze
     *
     * @param first  First cell that you want to connect
     * @param second Second cell that you want to connect
     */
    private static void connectCells(Cell first, Cell second) {
        // West
        if ((first.getX() - second.getX() == 0) && (first.getY() - second.getY() == 2)) {
            maze[first.getX()][first.getY() - 1].setValue(Constants.EMPTY);
        }
        // East
        if ((first.getX() - second.getX() == 0) && (first.getY() - second.getY() == -2)) {
            maze[first.getX()][first.getY() + 1].setValue(Constants.EMPTY);
        }
        // North
        if ((first.getX() - second.getX() == 2) && (first.getY() - second.getY() == 0)) {
            maze[first.getX() - 1][first.getY()].setValue(Constants.EMPTY);
        }
        // South
        if ((first.getX() - second.getX() == -2) && (first.getY() - second.getY() == 0)) {
            maze[first.getX() + 1][first.getY()].setValue(Constants.EMPTY);
        }
    }

    /**
     * Get all valid neighbor to the given cell
     *
     * @param cell Cell for which you want to get all valid neighbors
     * @return List of valid neighbors
     */
    private static List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighborsList = new ArrayList<>();
        // NORTH
        if (isValidNeighbor(cell.getX() - 2, cell.getY())) {
            neighborsList.add(maze[cell.getX() - 2][cell.getY()]);
        }
        // WEST
        if (isValidNeighbor(cell.getX(), cell.getY() - 2)) {
            neighborsList.add(maze[cell.getX()][cell.getY() - 2]);
        }
        // SOUTH
        if (isValidNeighbor(cell.getX() + 2, cell.getY())) {
            neighborsList.add(maze[cell.getX() + 2][cell.getY()]);
        }
        // EAST
        if (isValidNeighbor(cell.getX(), cell.getY() + 2)) {
            neighborsList.add(maze[cell.getX()][cell.getY() + 2]);
        }
        return neighborsList;
    }

    /**
     * Initialize the frontiers list and find the starting point of maze generation
     */
    private static void initFrontierList() {
        int xStart = rand.nextInt(maze.length);
        int yStart = rand.nextInt(maze[0].length);
        maze[xStart][yStart].setValue(Constants.EMPTY);
        addFrontiers(maze[xStart][yStart]);
    }

    /**
     * Add to the frontiers list all valid frontiers of the given cell
     *
     * @param cell Cell for which you want to add frontiers in the frontiers list
     */
    private static void addFrontiers(Cell cell) {
        // NORTH
        if (isValidFrontier(cell.getX() - 2, cell.getY())) {
            if (!frontiersList.contains(maze[cell.getX() - 2][cell.getY()])) {
                frontiersList.add(maze[cell.getX() - 2][cell.getY()]);
            }
        }
        // WEST
        if (isValidFrontier(cell.getX(), cell.getY() - 2)) {
            if (!frontiersList.contains(maze[cell.getX()][cell.getY() - 2])) {
                frontiersList.add(maze[cell.getX()][cell.getY() - 2]);
            }
        }
        // SOUTH
        if (isValidFrontier(cell.getX() + 2, cell.getY())) {
            if (!frontiersList.contains(maze[cell.getX() + 2][cell.getY()])) {
                frontiersList.add(maze[cell.getX() + 2][cell.getY()]);
            }
        }
        // EAST
        if (isValidFrontier(cell.getX(), cell.getY() + 2)) {
            if (!frontiersList.contains(maze[cell.getX()][cell.getY() + 2])) {
                frontiersList.add(maze[cell.getX()][cell.getY() + 2]);
            }
        }
    }

    /**
     * Check if the given coordinates correspond to a valid neighbor in the grid
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return Boolean to indicate if it is a valid neighbor (true: yes; false: no)
     */
    private static boolean isValidNeighbor(int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
            return false;
        } else return maze[x][y].getValue() == Constants.EMPTY;
    }

    /**
     * Check if the given coordinates correspond to a valid frontier in the grid
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return Boolean to indicate if it is a valid frontier (true: yes; false: no)
     */
    private static boolean isValidFrontier(int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
            return false;
        } else return maze[x][y].getValue() == Constants.OBSTACLE;
    }

    /**
     * Initialize a grid full of obstacles ("walls")
     *
     * @param xDim X Dimension of the grid
     * @param yDim Y Dimension of the grid
     * @return Grid full of obstacles/walls
     */
    private static Cell[][] initMaze(int xDim, int yDim) {
        Cell[][] maze = new Cell[xDim][yDim];
        for (int i = 0; i < xDim; i++) {
            for (int j = 0; j < yDim; j++) {
                maze[i][j] = new Cell(i, j);
            }
        }
        return maze;
    }

    /**
     * Convert a Cell grid to an int grid
     *
     * @param cellGrid Cell grid that you want to convert
     * @return int grid representation of the given cell grid
     */
    private static int[][] cellGridToIntGrid(Cell[][] cellGrid) {
        int[][] intGrid = new int[cellGrid.length][cellGrid[0].length];
        for (int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid[0].length; j++) {
                intGrid[i][j] = cellGrid[i][j].getValue();
            }
        }
        return intGrid;
    }
}
