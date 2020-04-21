package test.java.fr.alexandreladriere.shortestpath.models.bfs;

import main.java.fr.alexandreladriere.shortestpath.models.bfs.BreadthFirstSearch;
import main.java.fr.alexandreladriere.shortestpath.utils.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

/**
 * Implements BreadthFirstSearch test class
 */
@RunWith(Parameterized.class)
public class BreadthFirstSearchTest {
    @Rule
    public ErrorCollector collector = new ErrorCollector();
    private int[][] matrix;
    private final int[] expectedStartingPoint;
    private final int expectedPathLength;
    private final int expectedPathLengthWithDiag;
    private List<int[]> expectedPath;
    private List<int[]> expectedPathWithDiag;

    /**
     * Default constructor
     *
     * @param matrix                Matrix that you want to run the test with
     * @param expectedStartingPoint Expected starting in the matrix that you want to run the test with
     * @param expectedPathLength    Expected shortest path length in the matrix
     */
    public BreadthFirstSearchTest(int[][] matrix, int[] expectedStartingPoint, List<int[]> expectedPath, List<int[]> expectedPathWithDiag, int expectedPathLength, int expectedPathLengthWithDiag) {
        super();
        initMatrix(matrix);
        initExpectedPath(expectedPath);
        initExpectedPathWithDiag(expectedPathWithDiag);
        this.expectedStartingPoint = new int[2];
        this.expectedStartingPoint[0] = expectedStartingPoint[0];
        this.expectedStartingPoint[1] = expectedStartingPoint[1];
        this.expectedPathLength = expectedPathLength;
        this.expectedPathLengthWithDiag = expectedPathLengthWithDiag;
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
                        {Constants.OBSTACLE, Constants.EMPTY, Constants.EMPTY, Constants.END}
                },
                        new int[]{0, 1},
                        new ArrayList<int[]>() {{
                            add(new int[]{0, 1});
                            add(new int[]{0, 0});
                            add(new int[]{1, 0});
                            add(new int[]{2, 0});
                            add(new int[]{2, 1});
                            add(new int[]{3, 1});
                            add(new int[]{3, 2});
                            add(new int[]{3, 3});
                        }},
                        new ArrayList<int[]>() {{
                            add(new int[]{0, 1});
                            add(new int[]{1, 0});
                            add(new int[]{2, 1});
                            add(new int[]{3, 2});
                            add(new int[]{3, 3});
                        }},
                        8,
                        5
                },
                // 2 starting points
                {new int[][]{
                        {Constants.EMPTY, Constants.EMPTY, Constants.START, Constants.EMPTY},
                        {Constants.EMPTY, Constants.OBSTACLE, Constants.OBSTACLE, Constants.OBSTACLE},
                        {Constants.EMPTY, Constants.EMPTY, Constants.START, Constants.EMPTY},
                        {Constants.OBSTACLE, Constants.EMPTY, Constants.OBSTACLE, Constants.END}
                },
                        new int[]{0, 2},
                        new ArrayList<int[]>() {{
                            add(new int[]{0, 2});
                            add(new int[]{0, 1});
                            add(new int[]{0, 0});
                            add(new int[]{1, 0});
                            add(new int[]{2, 0});
                            add(new int[]{2, 1});
                            add(new int[]{2, 2});
                            add(new int[]{2, 3});
                            add(new int[]{3, 3});
                        }},
                        new ArrayList<int[]>() {{
                            add(new int[]{0, 2});
                            add(new int[]{0, 1});
                            add(new int[]{1, 0});
                            add(new int[]{2, 1});
                            add(new int[]{2, 2});
                            add(new int[]{3, 3});
                        }},
                        9,
                        6
                },
                // No starting point
                {new int[][]{
                        {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.EMPTY},
                        {Constants.EMPTY, Constants.OBSTACLE, Constants.OBSTACLE, Constants.OBSTACLE},
                        {Constants.EMPTY, Constants.EMPTY, Constants.OBSTACLE, Constants.EMPTY},
                        {Constants.OBSTACLE, Constants.EMPTY, Constants.EMPTY, Constants.END}
                },
                        new int[]{0, 0},
                        new ArrayList<int[]>() {{
                            add(new int[]{0, 0});
                            add(new int[]{1, 0});
                            add(new int[]{2, 0});
                            add(new int[]{2, 1});
                            add(new int[]{3, 1});
                            add(new int[]{3, 2});
                            add(new int[]{3, 3});
                        }},
                        new ArrayList<int[]>() {{
                            add(new int[]{0, 0});
                            add(new int[]{1, 0});
                            add(new int[]{2, 1});
                            add(new int[]{3, 2});
                            add(new int[]{3, 3});
                        }},
                        7,
                        5
                },
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
     * Initialize the expected path coordinates list
     *
     * @param expectedPath The list of expected path coordinates that are correct
     */
    private void initExpectedPath(List<int[]> expectedPath) {
        this.expectedPath = new ArrayList<>();
        for (int[] ints : expectedPath) {
            this.expectedPath.add(new int[]{ints[0], ints[1]});
        }
    }

    /**
     * Initialize the expected path coordinates list
     *
     * @param expectedPathWithDiag The list of expected path coordinates that are correct
     */
    private void initExpectedPathWithDiag(List<int[]> expectedPathWithDiag) {
        this.expectedPathWithDiag = new ArrayList<>();
        for (int[] ints : expectedPathWithDiag) {
            this.expectedPathWithDiag.add(new int[]{ints[0], ints[1]});
        }
    }

    /**
     * Test the "findStartingPoint" function, with both options (useDiag and !useDiag)
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
        collector.checkThat(expectedPathLength, equalTo(BreadthFirstSearch.shortestPathLength(matrix, false)));
        collector.checkThat(expectedPathLengthWithDiag, equalTo(BreadthFirstSearch.shortestPathLength(matrix, true)));
    }

    /**
     * Test the "shortestPath" function, with both options (useDiag and !useDiag)
     */
    @Test
    public void shortestPathTest() {
        List<int[]> shortestPath = BreadthFirstSearch.shortestPath(matrix, false);
        List<int[]> shortestPathWithDiag = BreadthFirstSearch.shortestPath(matrix, true);
        for (int i = 0; i < shortestPath.size(); i++) {
            collector.checkThat(expectedPath.get(i)[0], equalTo(shortestPath.get(i)[0]));
            collector.checkThat(expectedPath.get(i)[1], equalTo(shortestPath.get(i)[1]));
        }

        for (int j = 0; j < shortestPathWithDiag.size(); j++) {
            /*
            System.out.println("{expectedPathWithDiag.get(j)[0], expectedPathWithDiag.get(j)[1]}={" + expectedPathWithDiag.get(j)[0] + ", " + expectedPathWithDiag.get(j)[1] + "}");
            System.out.println("{shortestPathWithDiag.get(j)[0], shortestPathWithDiag.get(j)[1]}={" + shortestPathWithDiag.get(j)[0] + ", " + shortestPathWithDiag.get(j)[1] + "}");
            System.out.println("-----");

             */
            collector.checkThat(expectedPathWithDiag.get(j)[0], equalTo(shortestPathWithDiag.get(j)[0]));
            collector.checkThat(expectedPathWithDiag.get(j)[1], equalTo(shortestPathWithDiag.get(j)[1]));
        }
    }
}
