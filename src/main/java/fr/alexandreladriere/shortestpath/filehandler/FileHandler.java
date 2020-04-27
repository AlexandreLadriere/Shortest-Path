package main.java.fr.alexandreladriere.shortestpath.filehandler;

import main.java.fr.alexandreladriere.shortestpath.utils.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implement the module that will load/save a matrix from a specific txt file
 */
public final class FileHandler {
    /**
     * Retrieve the matrix from the given file
     * <p>
     * The file must have a specific format. Example of file content:
     * <p>
     * start=1
     * end=2
     * obstacle=-1
     * <p>
     * x=4
     * y=5
     * delimiter=;
     * <p>
     * 1;0;0;-1;-1
     * 0;-1;-1;0;0
     * 0;0;-1;-1;0
     * -1;0;0;0;2
     *
     * @param file File path
     * @return Matrix which is in the text file
     */
    public static int[][] getMatrixFromFile(String file) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                fileContent.append(line);
                fileContent.append("\n");
            }
            String fileContentStr = fileContent.toString();
            return buildMatrix(fileContentStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[0][0];
    }

    /**
     * Build a matrix from the given String representation of the file content
     *
     * @param fileContent String representation of the matrix file content
     * @return Matrix representation of the file content according to project constants
     */
    private static int[][] buildMatrix(String fileContent) {
        String[] lines = fileContent.split("\n");
        int start = Integer.parseInt(lines[0].split("=")[1]); // get the start value used
        int end = Integer.parseInt(lines[1].split("=")[1]); // get the end value used
        int obstacle = Integer.parseInt(lines[2].split("=")[1]); // get the obstacle value used
        // skip empty line
        int xDim = Integer.parseInt(lines[4].split("=")[1]); // get the X Dim of the matrix
        int yDim = Integer.parseInt(lines[5].split("=")[1]); // get the Y Dim of the matrix
        // skip empty line
        String delimiter = lines[6].split("=")[1]; // get the value for delimiter used inside the matrix

        int[][] matrix = new int[xDim][yDim];
        for (int i = 8; i < lines.length; i++) {
            String[] rowValues = lines[i].split(delimiter); // Get all the string values of a row
            for (int j = 0; j < rowValues.length; j++) {
                int val = Integer.parseInt(rowValues[j]);
                if (val == start) {
                    matrix[i - 8][j] = Constants.START;
                } else if (val == end) {
                    matrix[i - 8][j] = Constants.END;
                } else if (val == obstacle) {
                    matrix[i - 8][j] = Constants.OBSTACLE;
                } else {
                    matrix[i - 8][j] = Constants.EMPTY;
                }
            }
        }
        for (int[] ints : matrix) {
            for (int n = 0; n < matrix[0].length; n++) {
                System.out.println(ints[n]);
            }
        }
        return matrix;
    }

}
