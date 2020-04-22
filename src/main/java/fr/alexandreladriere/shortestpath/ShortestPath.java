package main.java.fr.alexandreladriere.shortestpath;

import main.java.fr.alexandreladriere.shortestpath.gui.Gui;

import javax.swing.*;

/**
 * Main class
 */
public class ShortestPath extends JFrame {
    /**
     * Default constructor
     */
    public ShortestPath() {
        super("Find shortest path - Alexandre Ladrière - 2020");
        setResizable(true);
        Gui gui = new Gui();
        this.setJMenuBar(gui.getMenuBar());
        setContentPane(gui);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ShortestPath();
    }
}
