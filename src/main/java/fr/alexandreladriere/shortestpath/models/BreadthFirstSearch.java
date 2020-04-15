package main.java.fr.alexandreladriere.shortestpath.models;

import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Implement the BFS algorithm (revisited a little bit)
 * See: https://en.wikipedia.org/wiki/Breadth-first_search
 */
public final class BreadthFirstSearch {
    /**
     * Get the starting point inside a matrix
     *
     * @param matrix The matrix in which you want to find the starting point
     * @return Coordinates of the starting point (i.e.: [row, col])
     */
    public static int[] findStartingPoint(int[][] matrix) {
        int row = 0;
        int col = 0;
        boolean startFlag = false;
        // Iterate through rows and cols to find the position of the starting point
        for (row = 0; row < matrix.length; row++) {
            for (col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == Constants.START) {
                    startFlag = true;
                }
                if (startFlag) {
                    break;
                }
            }
            if (startFlag) {
                break;
            }
        }
        return new int[]{row, col};
    }

    /**
     * Find the shortest path length between a starting point end an ending point in a matrix with obstacles
     *
     * @param matrix Matrix in which you want to find the shortest path length
     * @return The shortest path length between the starting point and the ending point
     */
    public static int shortestPathLength(int[][] matrix) {
        int[] start = findStartingPoint(matrix);
        int row = start[0];
        int col = start[1];
        return shortestPathLength(matrix, row, col);
    }

    /**
     * Find the shortest path length between a starting point end an ending point in a matrix with obstacles, with possibilities to move in diagonals
     *
     * @param matrix Matrix in which you want to find the shortest path length
     * @return The shortest path length between the starting point and the ending point
     */
    public static int shortestPathLengthWithDiag(int[][] matrix) {
        int[] start = findStartingPoint(matrix);
        int row = start[0];
        int col = start[1];
        return shortestPathLengthWithDiag(matrix, row, col);
    }

    /**
     * Find the shortest path length between a starting point end an ending point in a matrix with obstacles
     *
     * @param matrix   Matrix in which you want to find the shortest path length
     * @param startRow X coordinate of the starting point
     * @param startCol Y coordinate of the starting point
     * @return The shortest path length between the starting point and the ending point
     */
    private static int shortestPathLength(int[][] matrix, int startRow, int startCol) {
        int count = 0; // length of the shortest path
        Queue<int[]> nextToVisit = new LinkedList<>(); // Position list of next elements to visit
        nextToVisit.offer(new int[]{startRow, startCol});
        Set<int[]> visited = new HashSet<>(); // Position set of visited elements
        Queue<int[]> temp = new LinkedList<>();

        while (!nextToVisit.isEmpty()) {
            int[] position = nextToVisit.poll();
            int row = position[0];
            int col = position[1];

            // If we are at the end point
            if (matrix[row][col] == Constants.END) {
                return count;
            }
            // Upper element
            if (row > 0 && !visited.contains(new int[]{row - 1, col}) && matrix[row - 1][col] != Constants.OBSTACLE) {
                temp.offer(new int[]{row - 1, col});
            }
            // Lower element
            if (row < matrix.length - 1 && !visited.contains(new int[]{row + 1, col}) && matrix[row + 1][col] != Constants.OBSTACLE) {
                temp.offer(new int[]{row + 1, col});
            }
            // Right element
            if (col < matrix[0].length - 1 && !visited.contains(new int[]{row, col + 1}) && matrix[row][col + 1] != Constants.OBSTACLE) {
                temp.offer(new int[]{row, col + 1});
            }
            //Left element
            if (col > 0 && !visited.contains(new int[]{row, col - 1}) && matrix[row][col - 1] != Constants.OBSTACLE) {
                temp.offer(new int[]{row, col - 1});
            }
            if (nextToVisit.isEmpty() && !temp.isEmpty()) {
                nextToVisit = temp;
                temp = new LinkedList<>();
                count++;
            }
            visited.add(new int[]{row, col});
        }
        return count;
    }

    /**
     * Find the shortest path length between a starting point end an ending point in a matrix with obstacles, with possibilities to move in diagonals
     *
     * @param matrix   Matrix in which you want to find the shortest path length
     * @param startRow X coordinate of the starting point
     * @param startCol Y coordinate of the starting point
     * @return The shortest path length between the starting point and the ending point
     */
    private static int shortestPathLengthWithDiag(int[][] matrix, int startRow, int startCol) {
        int count = 0; // length of the shortest path
        Queue<int[]> nextToVisit = new LinkedList<>(); // Position list of next elements to visit
        nextToVisit.offer(new int[]{startRow, startCol});
        Set<int[]> visited = new HashSet<>(); // Position set of visited elements
        Queue<int[]> temp = new LinkedList<>();

        while (!nextToVisit.isEmpty()) {
            int[] position = nextToVisit.poll();
            int row = position[0];
            int col = position[1];

            // If we are at the end point
            if (matrix[row][col] == Constants.END) {
                return count;
            }
            // Upper element
            if (row > 0 && !visited.contains(new int[]{row - 1, col}) && matrix[row - 1][col] != Constants.OBSTACLE) {
                temp.offer(new int[]{row - 1, col});
            }
            // Upper left element
            if (row > 0 && col > 0 && !visited.contains(new int[]{row - 1, col - 1}) && matrix[row - 1][col - 1] != Constants.OBSTACLE) {
                temp.offer(new int[]{row - 1, col - 1});
            }
            // Upper right element
            if (row > 0 && col < matrix[0].length - 1 && !visited.contains(new int[]{row - 1, col + 1}) && matrix[row - 1][col + 1] != Constants.OBSTACLE) {
                temp.offer(new int[]{row - 1, col + 1});
            }
            // Lower element
            if (row < matrix.length - 1 && !visited.contains(new int[]{row + 1, col}) && matrix[row + 1][col] != Constants.OBSTACLE) {
                temp.offer(new int[]{row + 1, col});
            }
            // Lower left element
            if (row < matrix.length - 1 && col > 0 && !visited.contains(new int[]{row + 1, col - 1}) && matrix[row + 1][col - 1] != Constants.OBSTACLE) {
                temp.offer(new int[]{row + 1, col - 1});
            }
            // Lower right element
            if (row < matrix.length - 1 && col < matrix[0].length - 1 && !visited.contains(new int[]{row + 1, col + 1}) && matrix[row + 1][col + 1] != Constants.OBSTACLE) {
                temp.offer(new int[]{row + 1, col + 1});
            }
            // Right element
            if (col < matrix[0].length - 1 && !visited.contains(new int[]{row, col + 1}) && matrix[row][col + 1] != Constants.OBSTACLE) {
                temp.offer(new int[]{row, col + 1});
            }
            //Left element
            if (col > 0 && !visited.contains(new int[]{row, col - 1}) && matrix[row][col - 1] != Constants.OBSTACLE) {
                temp.offer(new int[]{row, col - 1});
            }
            if (nextToVisit.isEmpty() && !temp.isEmpty()) {
                nextToVisit = temp;
                temp = new LinkedList<>();
                count++;
            }
            visited.add(new int[]{row, col});
        }
        return count;
    }
}
