package controller;

import model.Employee;
import model.Movie;
import view.BillboardPanel;
import view.CustomFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class BillboardPanelController {
    public static void initiate(BillboardPanel panel) {
        BillboardPanelController.getMovies(panel);
        panel.backBtn.addActionListener(e -> returnToBack(panel));
        panel.updateTableMenuBar.addActionListener(e -> getMovies(panel));
        panel.logOutmenuBar.addActionListener(e -> logout(panel));
        panel.searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchMovies(panel, String.valueOf(panel.searchBar.getPassword()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchMovies(panel, String.valueOf(panel.searchBar.getPassword()));

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        if(panel.parentFrame.getLoggedEmployee() == null || !panel.parentFrame.getLoggedEmployee().isAdmin()) {
            panel.backBtn.setText("Actualizar");
            panel.backBtn.setPreferredSize(new Dimension(120, 40));
        }

    }

    private static void logout(BillboardPanel panel) {
        panel.parentFrame.setLoggedEmployee(null);
        panel.parentFrame.changeScreen(CustomFrame.Screen.LOG_IN);
    }

    private static void getMovies(BillboardPanel panel) {
        ArrayList<Movie> movies= Movie.movieList();

        panel.movieList.setMovieList(movies);
    }

    private static void returnToBack(BillboardPanel panel) {
        Employee empleado = panel.parentFrame.getLoggedEmployee();
        if(empleado != null && empleado.isAdmin()) {
            panel.parentFrame.changeScreen(CustomFrame.Screen.ADMINSELECTION);
        } else {
            panel.parentFrame.changeScreen(CustomFrame.Screen.BILLBOARD);
        }
    }

    private static void searchMovies(BillboardPanel panel, String text) {
        ArrayList<Movie> movies = Movie.getMovies(text);

        panel.movieList.setMovieList(movies);

        System.out.println(text);
    }
}
