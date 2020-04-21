package main.java.fr.alexandreladriere.shortestpath.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implement the controller
 */
public class Controller implements ActionListener {
    private final Gui gui;

    /**
     * Default constructor
     *
     * @param gui
     */
    public Controller(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object cmd = actionEvent.getSource();
        if (cmd.equals(gui.getFindPathButton())) {

        }
        if (cmd.equals(gui.getResetButton())) {

        }
    }
}
