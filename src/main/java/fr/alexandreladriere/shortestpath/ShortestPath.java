package main.java.fr.alexandreladriere.shortestpath;

import main.java.fr.alexandreladriere.shortestpath.models.bfs.BreadthFirstSearch;
import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

import java.util.List;

/**
 * Main class
 */
public class ShortestPath {
    public static void main(String[] args) {
        /*

        int[][] matrix = new int[][]{
                {1, 0, 0},
                {0, 0, -1},
                {0, 0, 2}};
        System.out.println(BreadthFirstSearch.shortestPathLength(matrix, true));
        List<int[]> path = BreadthFirstSearch.shortestPath(matrix, true);
        System.out.println(path.size());
        for (int i = 0; i < path.size(); i++) {
            System.out.println("(" + path.get(i)[0] + ", " + path.get(i)[1] + ")");
        }

         */
        int[][] matrixTest = new int[][]{
                {Constants.EMPTY, Constants.START, Constants.EMPTY, Constants.EMPTY},
                {Constants.EMPTY, Constants.OBSTACLE, Constants.OBSTACLE, Constants.OBSTACLE},
                {Constants.EMPTY, Constants.EMPTY, Constants.OBSTACLE, Constants.EMPTY},
                {Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, Constants.END}
        };
        System.out.println("shortestPathLength=" + BreadthFirstSearch.shortestPathLength(matrixTest, true));
        List<int[]> pathTest = BreadthFirstSearch.shortestPath(matrixTest, true);
        System.out.println("pathTestLength=" + pathTest.size());
        for (int[] ints : pathTest) {
            System.out.println("(" + ints[0] + ", " + ints[1] + ")");
        }
    }
}
