package main.java.fr.alexandreladriere.maze.dfs;

import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Implement the Depth-First Search (DFS) algorithm to generate a maze
 * see: https://en.wikipedia.org/wiki/Maze_generation_algorithm#Depth-first_search
 */
public final class DepthFirstSearch {
    private static final Random rand = new Random();
    private static int[][] maze;

    /**
     * Generate a maze with the given dimensions and with the DFS algorithm
     *
     * @param xDim X Dimension of the maze that you want to create
     * @param yDim Y Dimension of the maze that you want to create
     * @return Maze with the given dimensions.
     */
    public static int[][] generateMaze(int xDim, int yDim) {
        maze = new int[xDim][yDim];
        initMaze();
        int[] start = setStartingPoint();
        recursion(start[0], start[1]);
        return maze;
    }

    private static void recursion(int x, int y) {
        int[] randDirections = generateRandDirections();
        for (int randDirection : randDirections) {
            switch (randDirection) {
                case 1: // UP
                    // checks if up cell is in the grid or not
                    if (x - 2 <= 0) {
                        continue;
                    }
                    if (maze[x - 2][y] != Constants.EMPTY) {
                        maze[x - 2][y] = Constants.EMPTY;
                        maze[x - 1][y] = Constants.EMPTY;
                        recursion(x - 2, y);
                    }
                    break;
                case 2: // RIGHT
                    // check if right cell is in the grid or not
                    if (y + 2 >= maze[0].length - 1) {
                        continue;
                    }
                    if (maze[x][y + 2] != Constants.EMPTY) {
                        maze[x][y + 2] = Constants.EMPTY;
                        maze[x][y + 1] = Constants.EMPTY;
                        recursion(x, y + 2);
                    }
                    break;
                case 3: // DOWN
                    // check if the down cell is in the grid or not
                    if (x + 2 >= maze.length - 1) {
                        continue;
                    }
                    if (maze[x + 2][y] != Constants.EMPTY) {
                        maze[x + 2][y] = Constants.EMPTY;
                        maze[x + 1][y] = Constants.EMPTY;
                        recursion(x + 2, y);
                    }
                    break;
                case 4: // LEFT
                    // check if left cell is in the grid or not
                    if (y - 2 <= 0) {
                        continue;
                    }
                    if (maze[x][y - 2] != Constants.EMPTY) {
                        maze[x][y - 2] = Constants.EMPTY;
                        maze[x][y - 1] = Constants.EMPTY;
                        recursion(x, y - 2);
                    }
                    break;
            }
        }
    }

    /**
     * Set the starting point for the generation of the maze
     */
    private static int[] setStartingPoint() {
        int xStart = rand.nextInt(maze.length);
        while (xStart % 2 == 0) {
            xStart = rand.nextInt(maze.length);
        }
        int yStart = rand.nextInt(maze[0].length);
        while (yStart % 2 == 0) {
            yStart = rand.nextInt(maze[0].length);
        }
        maze[xStart][yStart] = Constants.EMPTY;
        return (new int[]{xStart, yStart});
    }

    /**
     * Initialize the maze by filling it with walls
     */
    private static void initMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = Constants.OBSTACLE;
            }
        }
    }

    /**
     * Generate an array of 4 integers from 1 to 4 in randomly ordered
     *
     * @return Array of 4 integers from 1 to 4 in randomly ordered
     */
    private static int[] generateRandDirections() {
        List<Integer> rand = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {
            rand.add(i + 1);
        }
        Collections.shuffle(rand);
        // Convert List<Integer> to int[] (valid from java 8)
        // see: https://stackoverflow.com/a/23945015/13247140
        return rand.stream().mapToInt(i -> i).toArray();
    }
}
