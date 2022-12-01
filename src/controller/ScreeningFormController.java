/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package controller;

import model.Movie;
import model.Room;
import model.Screening;
import view.CustomFrame;
import view.ScreeningForm;

import javax.swing.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ScreeningFormController {

    public static void initiate(ScreeningForm panel) {
        boolean isRowSelected = !TableController.idSelected.isBlank() || TableController.rowSelected != -1;
        addItemsToComboBox(panel);
        panel.btnCancelar.addActionListener(q -> closeForm(panel));
        if (isRowSelected) {
            insertDataToForm(panel);
            panel.confirmarBtn.addActionListener(q -> editData(panel));
            return;
        }
        panel.confirmarBtn.addActionListener(q -> insertDataToDB(panel));
    }

    public static void insertDataToDB(ScreeningForm panel) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int id = panel.screeningMovieId.getSelectedIndex() + 1;
        int room = panel.screeningRoom.getSelectedIndex() + 1;
        Timestamp time = Timestamp.valueOf(format.format(panel.screeningStart.getValue()));
        Screening s = new Screening(id, room, time);
        if (!Screening.insertPrepared(s)) {
            JOptionPane.showMessageDialog(panel, "La funcion se intenta poner en un rango de horario no valido, intentelo con otro horario");
            return;
        }
        closeForm(panel);
    }

    public static void insertDataToForm(ScreeningForm panel) {
        Screening s = Screening.getScreening(Integer.parseInt(TableController.idSelected));
        panel.screeningMovieId.setSelectedIndex(s.getMovieId() - 1);
        panel.screeningRoom.setSelectedIndex(s.getRoom() - 1);
        panel.screeningStart.setValue(s.getScreeningStart());
    }

    public static void addItemsToComboBox(ScreeningForm panel) {
        ArrayList<Movie> moviesList = Movie.movieList();
        ArrayList<Room> roomList = Room.obtenerSalas();

        moviesList.forEach(movie -> panel.screeningMovieId.addItem(movie.getTituloCartelera()));
        roomList.forEach(room -> panel.screeningRoom.addItem(String.valueOf(room.getId()) + " - " + room.getTipo()));
    }

    public static void editData(ScreeningForm panel) {
        Screening s = Screening.getScreening(Integer.parseInt(TableController.idSelected));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        s.setMovie_id(panel.screeningMovieId.getSelectedIndex() + 1);
        s.setRoom(panel.screeningRoom.getSelectedIndex());
        s.setScreeningStart(Timestamp.valueOf(format.format(panel.screeningStart.getValue())));

        Screening.update(s);
        panel.parentFrame.changeScreen(CustomFrame.Screen.BILLBOARD);
        TableController.idSelected = "";
        TableController.rowSelected = -1;
    }

    public static void closeForm(ScreeningForm panel) {
        TableController.idSelected = "";
        TableController.rowSelected = -1;
        panel.parentFrame.changeScreen(CustomFrame.Screen.BILLBOARD);
    }
}
