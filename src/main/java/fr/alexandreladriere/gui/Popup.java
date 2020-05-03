package main.java.fr.alexandreladriere.gui;

import main.java.fr.alexandreladriere.utils.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * Implement a custom popup panel
 */
public class Popup extends JPanel {
    private final JTextField rowNumberTextField;
    private final JTextField colNumberTextField;

    /**
     * Default constructor
     *
     * @param col Column number that you want to display in the text field (i.e. current col number)
     * @param row Row number that you want to display in the text field (i.e. current row number)
     */
    public Popup(int row, int col) {

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // North panel
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        JLabel rowNumber = new JLabel(Strings.ROW_NUMBER);
        rowNumberTextField = new JTextField(String.valueOf(row), 5);
        northPanel.add(rowNumber, BorderLayout.WEST);
        northPanel.add(rowNumberTextField, BorderLayout.EAST);

        // South panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        JLabel colNumber = new JLabel(Strings.COL_NUMBER);
        colNumberTextField = new JTextField(String.valueOf(col), 5);
        southPanel.add(colNumber, BorderLayout.WEST);
        southPanel.add(colNumberTextField, BorderLayout.EAST);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }

    /**
     * Get the text field corresponding to the number of columns
     *
     * @return text field corresponding to the number of columns
     */
    public JTextField getColNumberTextField() {
        return colNumberTextField;
    }

    /**
     * Get the text field corresponding to the number of rows
     *
     * @return text field corresponding to the number of rows
     */
    public JTextField getRowNumberTextField() {
        return rowNumberTextField;
    }
}
