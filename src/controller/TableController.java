/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package controller;

import ui.CustomTable;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableController implements MouseListener {
    private CustomTable table;
    static public String idSelected = "";
    static public int rowSelected = -1;

    public TableController(CustomTable table) {
        this.table = table;
        table.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable t = (JTable) e.getSource();

        int row = t.getSelectedRow();

        idSelected = String.valueOf(t.getValueAt(row, 0));
        rowSelected = row;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
