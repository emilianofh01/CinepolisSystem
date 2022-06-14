package model;

import db.MYSQLConnection;

import java.sql.*;

import java.util.ArrayList;

public class Movie {

    private int id;
    private String title;
    private String director;
    private String description;
    private int duration_min;
    private Blob cover;

    public Movie(int id, String title, String director, String description, int duration_min, Blob cover) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.duration_min = duration_min;
        this.cover = cover;
    }

    public Movie(String title, String director, String description, int duration_min, Blob cover) {
        this.title = title;
        this.director = director;
        this.description = description;
        this.duration_min = duration_min;
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

    public int getDuration_min() {
        return duration_min;
    }

    public void setDuration_min(int duration_min) {
        this.duration_min = duration_min;
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
                + ", duration_min=" + duration_min;
    }

    public static ArrayList<Movie> movieList() {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        Connection conn = MYSQLConnection.getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM movie";
            rs = st.executeQuery(query);

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

        Connection conn = MYSQLConnection.getConnection();
        PreparedStatement st = null;

        try {

            String query = "INSERT INTO movie (title, director, description, duration_min, cover) VALUES (?,?,?,?,?)";

            st = conn.prepareStatement(query);
            st.setString(1, m.getTitle());
            st.setString(2, m.getDirector());
            st.setString(3, m.getDescription());
            st.setInt(4, m.getDuration_min());
            st.setBlob(5, m.getCover());

            st.execute();

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

        PreparedStatement st = null;

        try (Connection conn = MYSQLConnection.getConnection()) {

            String query = "UPDATE movie SET "
                    + "title = ?,"
                    + "director = ?,"
                    + "description = ?,"
                    + "duration_min = ?,"
                    + "cover = ?";
            st = conn.prepareStatement(query);

            st.setString(1, m.getTitle());
            st.setString(2, m.getDirector());
            st.setString(3, m.getDescription());
            st.setInt(4, m.getDuration_min());
            st.setBlob(5, m.getCover());

            st.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void delete(int id) {

        Connection conn = MYSQLConnection.getConnection();
        Statement st = null;

        try {
            st = (Statement) conn.createStatement();
            String query = "DELETE FROM movie WHERE id = " + id;

            int deleted = st.executeUpdate(query);
            System.out.println("Deleted: " + deleted);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
