package main.java.fr.alexandreladriere.shortestpath.filehandler;

import main.java.fr.alexandreladriere.shortestpath.utils.Constants;
import main.java.fr.alexandreladriere.shortestpath.utils.Strings;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        return matrix;
    }

    /**
     * Save the given matrix to a text file according to a specific template
     *
     * @param matrix Matrix that you want to save
     * @param file   Path of the file that you want to create
     */
    public static void saveMatrixToFile(int[][] matrix, String file) {
        List<String> lines = new ArrayList<>();
        lines.add("start=1");
        lines.add("end=2");
        lines.add("obstacle=-1");
        lines.add("");
        lines.add("x=" + matrix.length);
        lines.add("y=" + matrix[0].length);
        lines.add("delimiter=;");
        lines.add("");
        String delimiter = ";";
        for (int[] ints : matrix) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < matrix[0].length; j++) {
                line.append(ints[j]);
                // Do not add delimiter at the end of the line
                if (j != matrix[0].length - 1) {
                    line.append(delimiter);
                }
            }
            lines.add(line.toString());
        }
        Path f = Paths.get(file);
        try {
            Files.write(f, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display a FileDialog to retrieve the absolute path of a file
     *
     * @return Absolute path of a file
     */
    public static String getAbsoluteFilePath() {
        FileDialog dialog = new FileDialog((Frame) null, Strings.SELECT_FILE);
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        return dialog.getDirectory() + dialog.getFile();
    }

    /**
     * Display a FileDialog to retrieve the absolute path of the file to save
     *
     * @return Absolute path of the file to save
     */
    public static String getSaveAsAbsoluteFilePath() {
        FileDialog dialog = new FileDialog((Frame) null, Strings.SAVE_AS);
        dialog.setMode(FileDialog.SAVE);
        dialog.setFile("matrix.txt");
        dialog.setVisible(true);
        return dialog.getDirectory() + dialog.getFile();
    }
}
