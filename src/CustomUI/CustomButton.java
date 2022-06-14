package CustomUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    private Color defaultBgColor, highlightedColor, foreground;
    private int radius;
    private boolean onClick = false;
    private Font font;

    public CustomButton(String text, int radius, Color defaultBgColor, Color highlightedColor, Color foreground, Font font) {
        super(text);
        this.defaultBgColor     = defaultBgColor;
        this.highlightedColor   = highlightedColor;
        this.foreground         = foreground;
        this.radius             = radius;
        this.font               = font;

        setBorder(null);
        setForeground(foreground);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(font);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                onClick = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                onClick = true;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(onClick ? highlightedColor : defaultBgColor);

        g2.fillRoundRect(0,0,getWidth(), getHeight(), radius,radius);
        super.paintComponent(g);
    }

    public Color getDefaultBgColor() {
        return defaultBgColor;
    }

    public void setDefaultBgColor(Color defaultBgColor) {
        this.defaultBgColor = defaultBgColor;
    }

    public Color getHighlightedColor() {
        return highlightedColor;
    }

    public void setHighlightedColor(Color highlightedColor) {
        this.highlightedColor = highlightedColor;
    }

    @Override
    public Color getForeground() {
        return foreground;
    }

    @Override
    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isOnClick() {
        return onClick;
    }

    public void setOnClick(boolean onClick) {
        this.onClick = onClick;
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }
}

