/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class CustomTableModel extends DefaultTableModel {
    String[] cabecera;
    Vector<Object[]> datos = new Vector<>();

    public CustomTableModel(String[] cabecera) {
        this.cabecera = cabecera;
        //this.datos = datos;
    }

    public void removeAllArrow() {
        if (getRowCount() > 0) {
            for (int i = getRowCount() - 1; i >= 0; i--) {
                removeRow(i);
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return cabecera[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 1) return ImageIcon.class;
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public int getRowCount() {
        if (datos == null) return 0;
        return datos.size();
    }

    @Override
    public void removeRow(int row) {
        datos.remove(row);
    }

    @Override
    public void addRow(Object[] rowData) {
        this.datos.addElement(rowData);
    }

    @Override
    public int getColumnCount() {
        return cabecera.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datos.elementAt(rowIndex)[columnIndex];
    }

}
