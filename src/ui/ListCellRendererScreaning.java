package ui;

import model.Room;
import model.Screening;

import javax.swing.*;
import java.awt.*;

public class ListCellRendererScreaning extends JLabel implements ListCellRenderer {
    public ListCellRendererScreaning() {
        setOpaque(true);
        //setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        //setBackground(Color.BLUE);
        //setForeground(Color.YELLOW);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(isSelected) this.setBackground(new Color(0,0,0, 50));
        if(!isSelected) this.setBackground(Color.white);
        if(value instanceof Screening svg) {
            setText(String.format("[Fecha: %s] [%s]", svg.getScreeningStart(), Room.getTipoSala(svg.getIdSala())));
        }
        if(value instanceof String svg) {
            setText(svg);
        }
        //setText(movie.getScreeningStart().toString());
        return this;
    }
}
