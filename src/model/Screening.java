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
    private int roomId;
    private Timestamp screeningStart;

    public Screening(int id, int movieId, int roomId, Timestamp screeningStart) {
        this.id = id;
        this.movieId = movieId;
        this.roomId = roomId;
        this.screeningStart = screeningStart;
    }

    public Screening(int movieId, int roomId, Timestamp screeningStart) {
        this.movieId = movieId;
        this.roomId = roomId;
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int room_id) {
        this.roomId = room_id;
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
                ", room_id=" + roomId +
                ", screening_start=" + screeningStart +
                ']';
    }

    public static ArrayList<Screening> screeningList() {
        ArrayList<Screening> screenings = new ArrayList<>();

        try (Connection conn = MYSQLConnection.getConnection()) {
            String query = "SELECT * FROM screening";
            Statement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                screenings.add(new Screening(
                        rs.getInt("id"),
                        rs.getInt("movie_id"),
                        rs.getInt("room_id"),
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
        try (Connection conn = MYSQLConnection.getConnection()) {

            String query = "INSERT INTO screening (movie_id, room_id, screening_start) VALUES (?,?,?)";

            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, s.getMovieId());
            st.setInt(2, s.getRoomId());
            st.setTimestamp(3, s.getScreeningStart());

            st.execute();

            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void update(Screening s) {
        try (Connection conn = MYSQLConnection.getConnection()){

            String query = "UPDATE screening SET "
                    + "movie_id = ?,"
                    + "room_id = ?,"
                    + "screening_start = ?"
                    + "WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(query);

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
        try (Connection conn = MYSQLConnection.getConnection()) {
            Statement st = conn.createStatement();
            String query = "DELETE FROM screening WHERE id = " + id;

            st.executeUpdate(query);
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}