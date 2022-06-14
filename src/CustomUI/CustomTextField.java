package CustomUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.ConstructorProperties;

public class CustomTextField extends JPanel {
    private int roundValue;
    private Font font;
    private Color defaultColor;

    private JPasswordField textField;
    private Dimension padding;
    private boolean onFocus = false;

    @ConstructorProperties({"Valor de redondeo", "Fuente(Tipografia)", "Color de fondo", "Padding(Espaciado hacia adentro)", "¿Desea ocultar la informacion?"})
    public CustomTextField(int roundValue, Font font, Color defaultColor, Dimension padding, boolean needToHide) {
        this.roundValue = roundValue;
        this.font = font;
        this.defaultColor = defaultColor;
        this.padding = padding;
        this.setLayout(null);
        this.setBackground(null);

        textField = new JPasswordField();
        if(!needToHide) textField.setEchoChar((char)0);
        textField.setFont(font);
        textField.setBackground(defaultColor);
        textField.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                onFocus = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                onFocus = false;
            }
        });
        this.add(textField);


    }

    @Override
    protected void paintComponent(Graphics g) {
        int vPadding = padding.height;
        int hPadding = padding.width;

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;


        g2.setColor(defaultColor);
        g2.fillRoundRect(0,0, getWidth(), getHeight(), roundValue, roundValue);
        textField.setBounds(hPadding,vPadding, getWidth() - hPadding*2, getHeight() - vPadding*2);
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

    public JPasswordField getTextField() {
        return textField;
    }

    public void setTextField(JPasswordField textField) {
        this.textField = textField;
    }

    public Dimension getPadding() {
        return padding;
    }

    public void setPadding(Dimension padding) {
        this.padding = padding;
    }
}
