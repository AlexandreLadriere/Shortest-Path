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

    public BreadthFirstSearchTest(int[][] matrix, int[] expectedStartingPoint) {
        super();
        initMatrix(matrix);
        this.expectedStartingPoint = new int[2];
        this.expectedStartingPoint[0] = expectedStartingPoint[0];
        this.expectedStartingPoint[1] = expectedStartingPoint[1];
    }

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][]{
                {new int[][]{
                        {Constants.EMPTY, Constants.START, Constants.EMPTY, Constants.EMPTY},
                        {Constants.EMPTY, Constants.OBSTACLE, Constants.OBSTACLE, Constants.OBSTACLE},
                        {Constants.EMPTY, Constants.EMPTY, Constants.OBSTACLE, Constants.EMPTY},
                        {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.END}
                },
                        new int[]{0, 1}},
                {new int[][]{
                        {Constants.EMPTY, Constants.EMPTY, Constants.START, Constants.EMPTY},
                        {Constants.EMPTY, Constants.OBSTACLE, Constants.OBSTACLE, Constants.OBSTACLE},
                        {Constants.EMPTY, Constants.EMPTY, Constants.START, Constants.EMPTY},
                        {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.END}
                },
                        new int[]{0, 2}},
                {new int[][]{
                        {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.EMPTY},
                        {Constants.EMPTY, Constants.OBSTACLE, Constants.OBSTACLE, Constants.OBSTACLE},
                        {Constants.EMPTY, Constants.EMPTY, Constants.OBSTACLE, Constants.EMPTY},
                        {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.END}
                },
                        new int[]{0, 0}},
        });
    }

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
        // TODO Test with another matrix without a starting point, or with many starting points
    }
}
