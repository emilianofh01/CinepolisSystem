/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package model;

import db.MYSQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Screening {

    private int id;
    private int movieId;
    private String room;
    private Timestamp screeningStart;

    public Screening(int id, int movieId, String room, Timestamp screeningStart) {
        this.id = id;
        this.movieId = movieId;
        this.room = room;
        this.screeningStart = screeningStart;
    }

    public Screening(int movieId, String room, Timestamp screeningStart) {
        this.movieId = movieId;
        this.room = room;
        this.screeningStart = screeningStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovie_id(int movie_id) {
        this.movieId = movie_id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Timestamp getScreeningStart() {
        return screeningStart;
    }

    public void setScreeningStart(Timestamp screeningStart) {
        this.screeningStart = screeningStart;
    }

    @Override
    public String toString() {
        return "Screening [" +
                "id=" + id +
                ", movie_id=" + movieId +
                ", room=" + room +
                ", screening_start=" + screeningStart +
                ']';
    }

    public static ArrayList<Screening> screeningList() {
        ArrayList<Screening> screenings = new ArrayList<>();

        try {
            String query = "SELECT * FROM screening";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                screenings.add(new Screening(
                        rs.getInt("id"),
                        rs.getInt("movie_id"),
                        rs.getString("room"),
                        rs.getTimestamp("screening_start")
                ));
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return screenings;
    }

    public static void insertPrepared(Screening s) {
        try {

            String query = "INSERT INTO screening (movie_id, room, screening_start) VALUES (?,?,?)";

            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setInt(1, s.getMovieId());
            st.setString(2, s.getRoom());
            st.setTimestamp(3, s.getScreeningStart());

            st.execute();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void update(Screening s) {
        try {
            String query = "UPDATE screening SET "
                    + "movie_id = ?,"
                    + "room = ?,"
                    + "screening_start = ?"
                    + "WHERE id = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);

            st.setInt(1, s.getMovieId());
            st.setString(2, s.getRoom());
            st.setTimestamp(3, s.getScreeningStart());
            st.setInt(4, s.getId());

            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            String query = "DELETE FROM screening WHERE id = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setInt(1, id);

            st.executeUpdate();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Screening getScreening(int id) {
        Screening screening = null;

        try {
            String query = "SELECT * FROM screening WHERE id = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                screening = new Screening(
                        rs.getInt("id"),
                        rs.getInt("movie_id"),
                        rs.getString("room"),
                        rs.getTimestamp("screening_start")
                );
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return screening;
    }
}