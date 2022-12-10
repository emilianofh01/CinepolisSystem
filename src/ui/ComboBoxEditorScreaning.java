package ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.*;

public class ComboBoxEditorScreaning extends BasicComboBoxEditor {
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private Object selectedItem;


    public ComboBoxEditorScreaning() {
        label.setOpaque(false);
        //label.setFont(new Font("Arial", Font.BOLD, 14));
        //label.setForeground(Color.BLACK);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
        panel.add(label);
        //panel.setBackground(Color.GREEN);
    }

    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public Object getItem() {
        return "Hola";
    }

    @Override
    public void setItem(Object item) {
        this.selectedItem = item;
        label.setText(item.toString());
    }
}
