/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class CustomTable extends JTable {

    public CustomTable(TableModel model) {
        super(model);
    }

//	@Override
//	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//		Component component = super.prepareRenderer(renderer, row, column);
//		int rendererWidth = component.getPreferredSize().width;
//		TableColumn tableColumn = getColumnModel().getColumn(column);
//		tableColumn.setPreferredWidth(
//				Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
//		return component;
//	}

    @Override
    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
        DefaultTableCellRenderer cellRenderer = (DefaultTableCellRenderer) super.getDefaultRenderer(columnClass);
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        //cellRenderer.setBackground(Color.red);

        return cellRenderer;
    }

    @Override
    public JTableHeader getTableHeader() {
        JTableHeader header = super.getTableHeader();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Font getFont() {
                return new Font("Montserrat", Font.BOLD, 14);
            }
        };

        Dimension size = header.getSize();

        size.height = 35;

        renderer.setBorder(null);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer.setVerticalAlignment(JLabel.TOP);
        header.setPreferredSize(size);

        header.setDefaultRenderer(renderer);


        renderer.setForeground(new Color(47, 47, 47));
        renderer.setBorder(new LineBorder(Color.white));

        return header;
    }

    @Override
    public boolean getShowHorizontalLines() {
        return false;
    }

    @Override
    public boolean getShowVerticalLines() {
        return false;
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(new Color(94, 94, 94));
    }

    @Override
    public Font getFont() {
        return new Font("Montserrat", Font.BOLD, 13);
    }

    @Override
    public int getRowHeight() {
        return 125;
    }

}
