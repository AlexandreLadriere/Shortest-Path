package main.java.fr.alexandreladriere.shortestpath.utils;

/**
 * Implement all string constants
 */
public final class Strings {
    public static final String TITLE = "Find shortest path - Alexandre Ladrière - 2020";
    public static final String STARTING_POINT = "Starting point";
    public static final String END_POINT = "End point";
    public static final String OBSTACLES = "Obstacles";
    public static final String RESET = "Reset";
    public static final String FIND = "Find shortest path";
    public static final String GENERATE_MAZE = "Generate maze";
    public static final String HELP = "Help";
    public static final String SETTINGS = "Settings";
    public static final String USE_DIAG = "Allow diagonal moves";
    public static final String ALGO = "Shortest Path Algorithms";
    public static final String MAZE_ALGO = "Maze Algorithms";
    public static final String ALGO_BFS = "Breadth-first Search (BFS)";
    public static final String ALGO_DFS = "Depth-first Search (DFS)";
    public static final String ALGO_RAND_PRIM = "Randomized Prim's Algorithm";
    public static final String CHANGE_SIZE = "Change matrix size";
    public static final String ROW_NUMBER = "Number of rows ";
    public static final String COL_NUMBER = "Number of columns ";
    public static final String MORE = "More...";
    public static final String SAVE_AS = "Save As...";
    public static final String LOAD = "Load...";
    public static final String FILE = "File";
    public static final String SELECT_FILE = "Select File to Open";
    public static final String TXT_EXT = ".txt";
    public static final String NO_TXT_MESSAGE = "The selected file must have the .txt extension. ";
    public static final String SAVE_ERR = "Your file can not be saved.";
    public static final String LOAD_ERR = "Your file can not be loaded.";
    public static final String NO_TXT_TITLE = "Bad extension";
    public static final String HELP_CONTENT = "Created by Alexandre Ladrière in 2020 - MIT Licence\n" +
            "\n" +
            "This project aims to implement some algorithms for finding the shortest path between a starting point and an end point in a matrix with obstacles, and to illustrate the solutions through a graphical interface.\n" +
            "\n" +
            "How to use it ?\n" +
            "1- Select the algorithm that you want to use (Settings > Algorithms)\n" +
            "2- Select if you want to allow diagonal moves (Settings > \"Allow diagonal moves\"\n" +
            "3- Select the grid size that you want (Settings > \"Change matrix size\"\n" +
            "4- Click on \"Starting point\" and select where you want to place your starting point\n" +
            "5- Click on \"End point\" and select where you want to place your end point\n" +
            "6- Click on \"Obstacles\" and click wherever you want to place obstacles (you can hold LEFT CLICK)\n" +
            "7- Click on \"Find shortest path\"\n" +
            "\n" +
            "Note: You can remove the starting point, the end point or obstacles by right clicking on it (you can also hold RIGHT CLICK).";
}
