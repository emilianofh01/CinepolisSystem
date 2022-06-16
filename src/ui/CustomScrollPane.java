/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.Border;

public class CustomScrollPane extends JScrollPane {
    public CustomScrollPane(JTable table) {
        super(table);
    }

    @Override
    public Border getBorder() {
        return BorderFactory.createEmptyBorder();
    }

    @Override
    public void setViewport(JViewport viewport) {
        viewport.setBackground(Color.white);
        super.setViewport(viewport);
    }

    @Override
    public Color getBackground() {
        return Color.WHITE;
    }

    @Override
    public void setVerticalScrollBar(JScrollBar verticalScrollBar) {
        verticalScrollBar.setUI(new CustomJScrollBarUI());
        verticalScrollBar.setBackground(Color.white);
        verticalScrollBar.setPreferredSize(new Dimension(10, 0));
        super.setVerticalScrollBar(verticalScrollBar);
    }

    @Override
    public void setColumnHeader(JViewport columnHeader) {
        columnHeader.setView(new JViewport() {
            @Override
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.height = 40;
                return size;
            }
        });
        super.setColumnHeader(columnHeader);
    }

}
