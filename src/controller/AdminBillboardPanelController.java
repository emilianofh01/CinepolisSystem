/**
 *  Ingenieria en desarrollo de software
 *  Proyecto final - Programacion III
 *
 *  Emiliano Fernandez Hernandez
 *  Kenneth De Guadalupe Quintero Valles
 */

package controller;

import model.Movie;
import model.Screening;
import view.AdminBillboardPanel;
import view.CustomFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdminBillboardPanelController {
    public static void initiate(AdminBillboardPanel panel) {
        AdminBillboardPanelController.populateTable(panel);
        new TableController(panel.tabla);

        panel.eliminarBtn.addActionListener(q -> deleteDataRow(panel));
        panel.modificarBtn.addActionListener(q -> openEditFrame(panel));
        panel.agregarBtn.addActionListener(q -> addDataRow(panel));
        panel.cerrarSesionBtn.addActionListener(q -> logOut(panel));
        panel.logOutmenuBar.addActionListener(q -> logOut(panel));
        panel.updateTableMenuBar.addActionListener(q -> updateTable(panel));
        panel.backBtn.addActionListener(e -> panel.parentFrame.changeScreen(CustomFrame.Screen.ADMINSELECTION));
    }

    public static void updateTable(AdminBillboardPanel panel) {
        panel.tableModel.removeAllArrow();
        populateTable(panel);
        panel.tableModel.fireTableDataChanged();
        TableController.idSelected = "";
        TableController.rowSelected = -1;
    }

    public static void logOut(AdminBillboardPanel panel) {
        int result = JOptionPane.showConfirmDialog(panel, "¿Esta seguro que desea salir?" , "¡Cuidado!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if(result == JOptionPane.YES_NO_CANCEL_OPTION || result == JOptionPane.DEFAULT_OPTION) return;

        TableController.idSelected = "";
        TableController.rowSelected = -1;
        panel.parentFrame.setLoggedEmployee(null);
        panel.parentFrame.changeScreen(CustomFrame.Screen.LOG_IN);
    }

    public static void openEditFrame(AdminBillboardPanel panel) {
        if (TableController.rowSelected == -1 || TableController.idSelected.isBlank()) {
            JOptionPane.showMessageDialog(panel, "Debes seleccionar un registro antes de modificar");
            return;
        }
        panel.parentFrame.changeScreen(CustomFrame.Screen.SCREENINGFORM);
    }

    public static void deleteDataRow(AdminBillboardPanel panel) {

        if (TableController.rowSelected == -1 || TableController.idSelected.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Debes seleccionar un registro antes de eliminar");
            return;
        }
        int result = JOptionPane.showConfirmDialog(panel, "¿Esta seguro de eliminar este registro?" , "¡Cuidado!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if(result == JOptionPane.YES_NO_CANCEL_OPTION || result == JOptionPane.DEFAULT_OPTION) return;

        Screening.delete(Integer.parseInt(TableController.idSelected));
        panel.tableModel.removeRow(TableController.rowSelected);
        panel.tableModel.fireTableDataChanged();
        TableController.idSelected = "";
        TableController.rowSelected = -1;
    }

    public static void addDataRow(AdminBillboardPanel panel) {
        TableController.idSelected = "";
        TableController.rowSelected = -1;
        panel.parentFrame.changeScreen(CustomFrame.Screen.SCREENINGFORM);
    }

    public static void populateTable(AdminBillboardPanel panel) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ArrayList<Screening> list = Screening.screeningList();



        list.forEach((s) -> {
            Movie m = Movie.getMovie(s.getMovieId());

            URL trailerUrl = m.getTrailerURL();
            ImageIcon icon;
            try {
                BufferedImage image = ImageIO.read(trailerUrl);

                icon = new ImageIcon(image);
                icon = new ImageIcon(icon.getImage().getScaledInstance(100,125, Image.SCALE_SMOOTH));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            Calendar cal = Calendar.getInstance();
            try {
                Date d = format.parse(format.format(s.getScreeningStart()));
                cal.setTime(d);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            cal.add(Calendar.MINUTE, m.getDuracionMin());


            panel.tableModel.addRow(new Object[]{
                    s.getId(),
                    icon,
                    m.getTituloCartelera(),
                    (m.getDuracionMin() + " min"),
                    s.getRoom(),
                    format.format(s.getScreeningStart()),
                    format.format(cal.getTime())
            });
        });
    }
}
