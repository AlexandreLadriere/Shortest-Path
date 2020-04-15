package test.java.fr.alexandreladriere.shortestpath.models;

import main.java.fr.alexandreladriere.shortestpath.models.BreadthFirstSearch;
import main.java.fr.alexandreladriere.shortestpath.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Implements BreadthFirstSearch test class
 */
@RunWith(Parameterized.class)
public class BreadthFirstSearchTest {
    private int[][] matrix;
    private int[] expectedStartingPoint;
    private int expectedPathLength;

    /**
     * Default constructor
     *
     * @param matrix                Matrix that you want to run the test with
     * @param expectedStartingPoint Expected starting in the matrix that you want to run the test with
     * @param expectedPathLength    Expected shortest path length in the matrix
     */
    public BreadthFirstSearchTest(int[][] matrix, int[] expectedStartingPoint, int expectedPathLength) {
        super();
        initMatrix(matrix);
        this.expectedStartingPoint = new int[2];
        this.expectedStartingPoint[0] = expectedStartingPoint[0];
        this.expectedStartingPoint[1] = expectedStartingPoint[1];
        this.expectedPathLength = expectedPathLength;
    }

    /**
     * Initialize the inputs of the test
     *
     * @return Collection of input
     * @see BreadthFirstSearch
     */
    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][]{
                // default
                {new int[][]{
                        {Constants.EMPTY, Constants.START, Constants.EMPTY, Constants.EMPTY},
                        {Constants.EMPTY, Constants.OBSTACLE, Constants.OBSTACLE, Constants.OBSTACLE},
                        {Constants.EMPTY, Constants.EMPTY, Constants.OBSTACLE, Constants.EMPTY},
                        {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.END}
                },
                        new int[]{0, 1},
                        7
                },
                // 2 starting points
                {new int[][]{
                        {Constants.EMPTY, Constants.EMPTY, Constants.START, Constants.EMPTY},
                        {Constants.EMPTY, Constants.OBSTACLE, Constants.OBSTACLE, Constants.OBSTACLE},
                        {Constants.EMPTY, Constants.EMPTY, Constants.START, Constants.EMPTY},
                        {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.END}
                },
                        new int[]{0, 2},
                        8
                },
                // No starting point
                {new int[][]{
                        {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.EMPTY},
                        {Constants.EMPTY, Constants.OBSTACLE, Constants.OBSTACLE, Constants.OBSTACLE},
                        {Constants.EMPTY, Constants.EMPTY, Constants.OBSTACLE, Constants.EMPTY},
                        {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.END}
                },
                        new int[]{0, 0},
                        6},
        });
    }

    /**
     * Initialize the matrix parameter
     *
     * @param matrix The matrix that you want to run the test with
     */
    private void initMatrix(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, matrix[0].length);
        }
    }

    /**
     * Test the "findStartingPoint" function
     */
    @Test
    public void findStartingPointTest() {
        int row_s = BreadthFirstSearch.findStartingPoint(matrix)[0];
        int col_s = BreadthFirstSearch.findStartingPoint(matrix)[1];
        assertEquals(expectedStartingPoint[0], row_s);
        assertEquals(expectedStartingPoint[1], col_s);
    }

    /**
     * Test the "shortestPathLength" function
     */
    @Test
    public void shortestPathLengthTest() {
        assertEquals(expectedPathLength, BreadthFirstSearch.shortestPathLength(matrix));
    }
}
