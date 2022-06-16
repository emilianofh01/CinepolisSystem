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

public class Movie {

    private int id;
    private String title;
    private String director;
    private String description;
    private int durationMin;
    private Blob cover;

    public Movie(int id, String title, String director, String description, int durationMin, Blob cover) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.durationMin = durationMin;
        this.cover = cover;
    }

    public Movie(String title, String director, String description, int durationMin, Blob cover) {
        this.title = title;
        this.director = director;
        this.description = description;
        this.durationMin = durationMin;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public Blob getCover() {
        return cover;
    }

    public void setCover(Blob cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Screening [id=" + id
                + ", title=" + title
                + ", director=" + director
                + ", description=" + description
                + ", duration_min=" + durationMin;
    }

    public static ArrayList<Movie> movieList() {
        ArrayList<Movie> movies = new ArrayList<>();

        try (Connection conn = MYSQLConnection.getConnection()) {
            String query = "SELECT * FROM movie";
            Statement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getString("description"),
                        rs.getInt("duration_min"),
                        rs.getBlob("cover")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
                st.close();
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return movies;
    }

    public static void insertPrepared(Movie m) {
        try (Connection conn = MYSQLConnection.getConnection()) {

            String query = "INSERT INTO movie (title, director, description, duration_min, cover) VALUES (?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, m.getTitle());
            st.setString(2, m.getDirector());
            st.setString(3, m.getDescription());
            st.setInt(4, m.getDurationMin());
            st.setBlob(5, m.getCover());

            st.execute();

            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void update(Movie m) {
        try (Connection conn = MYSQLConnection.getConnection()) {
            String query = "UPDATE movie SET "
                    + "title = ?,"
                    + "director = ?,"
                    + "description = ?,"
                    + "duration_min = ?,"
                    + "cover = ?"
                    + "WHERE id = ?";

            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, m.getTitle());
            st.setString(2, m.getDirector());
            st.setString(3, m.getDescription());
            st.setInt(4, m.getDurationMin());
            st.setBlob(5, m.getCover());
            st.setInt(6, m.getId());

            st.execute();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void delete(int id) {
        try (Connection conn = MYSQLConnection.getConnection()) {
            Statement st = conn.createStatement();
            String query = "DELETE FROM movie WHERE id = " + id;

            st.executeUpdate(query);
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
