package main.java.fr.alexandreladriere.shortestpath;

import main.java.fr.alexandreladriere.shortestpath.gui.Gui;
import main.java.fr.alexandreladriere.shortestpath.utils.Strings;

import javax.swing.*;

/**
 * Main class
 */
public class ShortestPath extends JFrame {
    /**
     * Default constructor
     */
    public ShortestPath() {
        super(Strings.TITLE);
        setResizable(true);
        Gui gui = new Gui(this);
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
