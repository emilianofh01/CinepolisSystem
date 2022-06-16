/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package view;

import javax.swing.*;

public abstract class AbstractCinepolisPanel extends JPanel {
    public final CustomFrame parentFrame;

    public AbstractCinepolisPanel(CustomFrame parentFrame) {
        this.parentFrame = parentFrame;
        init();
    }

    public abstract void init();

    public abstract void dispose();
}
