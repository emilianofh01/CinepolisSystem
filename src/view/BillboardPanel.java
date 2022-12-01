/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package view;

import controller.BillboardPanelController;
import model.CustomTableModel;
import ui.CustomButton;
import ui.CustomScrollPane;
import ui.CustomTable;
import ui.RoundContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BillboardPanel extends AbstractCinepolisPanel {
    static public final String[] cabeceras = new String[]{"ID", "P", "Nombre de la pelicula", "Sala", "Inicia", "Termina"};
    public CustomTableModel tableModel;
    public CustomTable tabla;

    public CustomButton cerrarSesionBtn;
    public CustomButton eliminarBtn;
    public CustomButton modificarBtn;
    public CustomButton agregarBtn;

    public JMenuItem logOutmenuBar, updateTableMenuBar;

    public BillboardPanel(CustomFrame parentFrame) {
        super(parentFrame);
    }

    @Override
    public void init() {
        this.setBackground(CustomFrame.BGCOLOR);
        this.setLayout(new BorderLayout(0, 25));
        this.setBorder(BorderFactory.createEmptyBorder(25, 50, 50, 50));

        JMenu option = new JMenu("Opciones");

        logOutmenuBar = new JMenuItem("Cerrar sesión");
        updateTableMenuBar = new JMenuItem("Actualizar tabla");

        option.add(logOutmenuBar);
        option.add(updateTableMenuBar);

        parentFrame.menuBar.add(option);

        // NORTH
        JPanel northContainer = new JPanel();
        try {
            BufferedImage logo = ImageIO.read(new File("src/Assets/logoCinepolis.png"));
            JLabel l = new JLabel(new ImageIcon(logo));

            northContainer.add(l);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        northContainer.setBackground(CustomFrame.BGCOLOR);
        this.add(northContainer, BorderLayout.NORTH);

        // CENTER
        RoundContainer tableContainer = new RoundContainer(45);
        tableContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tableContainer.setLayout(new BorderLayout());

        tableModel = new CustomTableModel(cabeceras);

        tabla = new CustomTable(tableModel);

        tabla.getColumnModel().getColumn(0).setMaxWidth(40);
        tabla.getColumnModel().getColumn(1).setMaxWidth(100);
        tabla.getColumnModel().getColumn(2).setMinWidth(300);
        tabla.getColumnModel().getColumn(3).setMaxWidth(70);
        tabla.getColumnModel().getColumn(4).setMinWidth(40);


        CustomScrollPane scrolltable = new CustomScrollPane(tabla);
        tableContainer.add(scrolltable);

        this.add(tableContainer, BorderLayout.CENTER);

        // SOUTH
        Font textFieldFont = new Font("Montserrat", Font.BOLD, 16);
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(southPanel.getPreferredSize().width, 40));
        southPanel.setLayout(new GridLayout(1, 3, 5, 0));
        southPanel.setBackground(CustomFrame.BGCOLOR);
        cerrarSesionBtn = new CustomButton("Cerrar Sesion", 10, CustomFrame.SECOND_BG_COLOR, Color.LIGHT_GRAY, Color.BLACK, textFieldFont);
        eliminarBtn = new CustomButton("Eliminar", 10, CustomFrame.SECOND_BG_COLOR, Color.LIGHT_GRAY, Color.BLACK, textFieldFont);
        modificarBtn = new CustomButton("Modificar", 10, CustomFrame.SECOND_BG_COLOR, Color.LIGHT_GRAY, Color.BLACK, textFieldFont);
        agregarBtn = new CustomButton("Agregar", 10, CustomFrame.SECOND_BG_COLOR, Color.LIGHT_GRAY, Color.BLACK, textFieldFont);
        southPanel.add(cerrarSesionBtn);
        southPanel.add(eliminarBtn);
        southPanel.add(modificarBtn);
        southPanel.add(agregarBtn);
        this.add(southPanel, BorderLayout.SOUTH);

        BillboardPanelController.initiate(this);
    }

    @Override
    public void dispose() {

    }
}
