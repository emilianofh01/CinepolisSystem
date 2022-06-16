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

public class RoundContainer extends JPanel {
    private int roundValue;
    private Insets padding;
    private Color bgColor;

    public RoundContainer() {
        this.setBackground(null);
        this.bgColor = Color.white;
        roundValue = 0;
    }

    public RoundContainer(int roundValue) {
        this.setBackground(null);
        this.bgColor = Color.white;
        this.roundValue = roundValue;
    }

    public RoundContainer(int roundValue, Insets padding) {
        this.setBackground(null);
        this.roundValue = roundValue;
        this.bgColor = Color.white;
        this.setBorder(BorderFactory.createEmptyBorder(padding.top, padding.left, padding.bottom, padding.right));
    }

    public RoundContainer(int roundValue, Insets padding, Color bgColor) {
        this.setBackground(null);
        this.roundValue = roundValue;
        this.bgColor = bgColor;
        this.setBorder(BorderFactory.createEmptyBorder(padding.top, padding.left, padding.bottom, padding.right));
    }

    static public RoundContainer createRoundContainer(int roundValue, int top, int left, int bottom, int right) {
        return new RoundContainer(roundValue, new Insets(top, left, bottom, right));
    }

    static public RoundContainer createRoundContainer(int roundValue, int top, int left, int bottom, int right, Color bgColor) {
        return new RoundContainer(roundValue, new Insets(top, left, bottom, right), bgColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), roundValue, roundValue);
    }

    public int getRoundValue() {
        return roundValue;
    }

    public void setRoundValue(int roundValue) {
        this.roundValue = roundValue;
    }

    public Insets getPadding() {
        return padding;
    }

    public void setPadding(Insets padding) {
        this.padding = padding;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
}
