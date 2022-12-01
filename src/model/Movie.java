/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package model;

import db.MYSQLConnection;

import java.net.URL;
import java.sql.*;

import java.util.ArrayList;

public class Movie {

    private int id;
    private String tituloCartelera;
    private String tituloOriginal;
    private int clasificacionId;
    private int duracionMin;
    private int generoId;
    private String descripcion;
    private String[] actores;
    private int director;
    private URL trailerURL;

    public Movie(int id,
                 String tituloCartelera,
                 String tituloOriginal,
                 int clasificacionId,
                 int duracionMin,
                 int generoId,
                 String descripcion,
                 int director,
                 //String[] actores,
                 URL trailerURL
    ) {
        this.id = id;
        this.tituloCartelera    = tituloCartelera;
        this.tituloOriginal     = tituloOriginal;
        this.clasificacionId    = clasificacionId;
        this.duracionMin        = duracionMin;
        this.generoId           = generoId;
        this.descripcion        = descripcion;
        this.director           = director;
        //this.actores          = actores;
        this.trailerURL         = trailerURL;
    }

    public Movie(String tituloCartelera, String descripcion, int duracionMin, URL trailerURL) {
        this.tituloCartelera = tituloCartelera;
        this.descripcion = descripcion;
        this.duracionMin = duracionMin;
        this.trailerURL = trailerURL;
    }

    public static ArrayList<Movie> movieList() {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String query = "SELECT * FROM peliculas";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("titulo_cartelera"),
                        rs.getString("titulo_original"),
                        rs.getInt("clasificacionId"),
                        rs.getInt("duracionMin"),
                        rs.getInt("generoId"),
                        rs.getString("sinopsis"),
                        rs.getInt("director"),
                        rs.getURL("trailerURL")
                ));
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return movies;
    }

    public static void insertPrepared(Movie m) {
        /*try {
            String query = "INSERT INTO movie (title, description, duration_min, cover) VALUES (?,?,?,?)";

            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setString(1, m.getTitle());
            st.setString(2, m.getDescription());
            st.setInt(3, m.getDurationMin());
            st.setBlob(4, m.getCover());

            st.execute();

            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
    }

    public static void update(Movie m) {
        /* {
            String query = "UPDATE movie SET "
                    + "title = ?,"
                    + "description = ?,"
                    + "duration_min = ?,"
                    + "cover = ?"
                    + "WHERE id = ?";

            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);

            st.setString(1, m.getTitle());
            st.setString(2, m.getDescription());
            st.setInt(3, m.getDurationMin());
            st.setBlob(4, m.getCover());
            st.setInt(5, m.getId());

            st.execute();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
    }

    public static void delete(int id) {
        /*try {
            Statement st = MYSQLConnection.conn.createStatement();
            String query = "DELETE FROM movie WHERE id = " + id;

            st.executeUpdate(query);
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
    }



    public static Movie getMovie(int id) {
        Movie movie = null;

        try {
            String query = "SELECT * FROM peliculas WHERE id = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                movie = new Movie(
                        rs.getInt("id"),
                        rs.getString("titulo_cartelera"),
                        rs.getString("titulo_original"),
                        rs.getInt("clasificacionId"),
                        rs.getInt("duracionMin"),
                        rs.getInt("generoId"),
                        rs.getString("sinopsis"),
                        rs.getInt("director"),
                        rs.getURL("trailerURL")
                );
            }

            st.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println(movie.trailerURL);
        return movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTituloCartelera() {
        return tituloCartelera;
    }

    public void setTituloCartelera(String tituloCartelera) {
        this.tituloCartelera = tituloCartelera;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public int getClasificacionId() {
        return clasificacionId;
    }

    public void setClasificacionId(int clasificacionId) {
        this.clasificacionId = clasificacionId;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String[] getActores() {
        return actores;
    }

    public void setActores(String[] actores) {
        this.actores = actores;
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    public URL getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(URL trailerURL) {
        this.trailerURL = trailerURL;
    }
}

