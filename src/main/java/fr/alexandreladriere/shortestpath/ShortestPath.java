package main.java.fr.alexandreladriere.shortestpath;

import main.java.fr.alexandreladriere.shortestpath.models.bfs.BreadthFirstSearch;

/**
 * Main class
 */
public class ShortestPath {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 0, 0}, {0, 0, -1}, {0, 0, 2}};
        System.out.println(BreadthFirstSearch.shortestPathLengthWithDiag(matrix));
    }
}
