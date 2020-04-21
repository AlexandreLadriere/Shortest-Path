package main.java.fr.alexandreladriere.shortestpath.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {
    private final Gui gui;

    public MouseController(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println("test2");
        gui.setClicked(true);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        gui.setClicked(false);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (gui.getClicked()) {
            System.out.println("*****");
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
