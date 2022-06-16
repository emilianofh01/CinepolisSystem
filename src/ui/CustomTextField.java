/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.ConstructorProperties;

public class CustomTextField extends JPasswordField {
    private int roundValue;
    private Font font;
    private Color defaultColor;
    private String placeholder;

    private Dimension padding;
    boolean onFocus = false;

    @ConstructorProperties({"Valor de redondeo", "Fuente(Tipografia)", "Color de fondo", "Padding(Espaciado hacia adentro)", "¿Desea ocultar la informacion?"})
    public CustomTextField(int roundValue, Font font, Color defaultColor, Dimension padding, boolean needToHide) {
        this.roundValue = roundValue;
        this.font = font;
        this.defaultColor = defaultColor;
        this.padding = padding;
        this.setLayout(null);
        this.setBackground(null);

        if (!needToHide) this.setEchoChar((char) 0);
        this.setFont(font);
        this.setBackground(defaultColor);
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                onFocus = true;
                /*if(!onFocus) {
                    textField.setForeground(new Color(139,139,139));
                    textField.setText("Hola");
                } else {
                    textField.setForeground(Color.black);
                    textField.setText("");
                }*/
            }

            @Override
            public void focusLost(FocusEvent e) {
                onFocus = false;
                /*if(!onFocus) {
                    textField.setForeground(new Color(139,139,139));
                    textField.setText("Hola");
                } else {
                    textField.setForeground(Color.black);
                    textField.setText("");
                }*/
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (placeholder == null || placeholder.length() == 0 || String.valueOf(getPassword()).length() > 0) {
            return;
        }
        final Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);


        if (!onFocus || String.valueOf(getPassword()).isEmpty()) {
            g2.setColor(new Color(139, 139, 139));
            g2.drawString(placeholder, getInsets().left, (g.getFontMetrics()
                    .getMaxAscent() + getInsets().top + getHeight() / 2) - g.getFontMetrics().getHeight() / 2);
        }
    }

    public int getRoundValue() {
        return roundValue;
    }

    public void setRoundValue(int roundValue) {
        this.roundValue = roundValue;
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    /*public JPasswordField getTextField() {
        return textField;
    }

    public void setTextField(JPasswordField textField) {
        this.textField = textField;
    }*/

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public Dimension getPadding() {
        return padding;
    }

    public void setPadding(Dimension padding) {
        this.padding = padding;
    }

    public boolean isOnFocus() {
        return onFocus;
    }

    public void setOnFocus(boolean onFocus) {
        this.onFocus = onFocus;
    }
}
