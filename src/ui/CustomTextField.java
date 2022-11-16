/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package ui;

import view.CustomFrame;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
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
        this.setBackground(new Color(255, 255, 255, 255));
        this.setBackground(defaultColor);

        if (!needToHide) setEchoChar((char) 0);
        this.setFont(font);
        this.setBackground(defaultColor);
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setOnFocus(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                setOnFocus(false);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);

        setOpaque(false);
        g2.setColor(this.defaultColor);
        g2.fillRoundRect(0,0, getWidth()-1, getHeight()-1, getRoundValue(), getRoundValue());

        if ((!onFocus && String.valueOf(getPassword()).isEmpty()) || String.valueOf(getPassword()).isEmpty()) {
            g2.setColor(new Color(139, 139, 139));
            g2.drawString(placeholder, getInsets().left, (g.getFontMetrics()
                    .getMaxAscent() + getInsets().top + getHeight() / 2) - g.getFontMetrics().getHeight() / 2);
        }
        if(onFocus) {
            g2.setColor(CustomFrame.BGCOLOR);
            //g2.setStroke(new BasicStroke(1));
            for (int i = 1; i < 4; i++) {
                g2.drawRoundRect(i,i, getWidth()-(i*2), getHeight()-(i*2), getRoundValue() - (i*3),getRoundValue() - (i*3));
            }
            repaint();
        }
        super.paintComponent(g);
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
