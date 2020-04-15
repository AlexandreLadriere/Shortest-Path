package test.java.fr.alexandreladriere.shortestpath.models;

import main.java.fr.alexandreladriere.shortestpath.models.BreadthFirstSearch;
import main.java.fr.alexandreladriere.shortestpath.utils.Constants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BreadthFirstSearchTest {
    private int[][] matrix;

    @Before
    public void initMatrix() {
        matrix = new int[][]{
                {Constants.EMPTY, Constants.START, Constants.EMPTY, Constants.EMPTY},
                {Constants.EMPTY, Constants.OBSTACLE, Constants.OBSTACLE, Constants.OBSTACLE},
                {Constants.EMPTY, Constants.EMPTY, Constants.OBSTACLE, Constants.EMPTY},
                {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.END}
        };
    }

    @Test
    public void findStartingPointTest() {
        int row_s = BreadthFirstSearch.findStartingPoint(matrix)[0];
        int col_s = BreadthFirstSearch.findStartingPoint(matrix)[1];
        assertEquals(0, row_s);
        assertEquals(1, col_s);
    }
}
