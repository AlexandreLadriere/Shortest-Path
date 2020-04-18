package main.java.fr.alexandreladriere.shortestpath;

import main.java.fr.alexandreladriere.shortestpath.models.bfs.BreadthFirstSearch;

import java.util.List;

/**
 * Main class
 */
public class ShortestPath {
    public static void main(String[] args) {
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
    }
}
