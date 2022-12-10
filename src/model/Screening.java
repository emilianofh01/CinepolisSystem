/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package model;

import db.MYSQLConnection;
import view.CustomFrame;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class Screening {
    int id;
    int idSala;
    int idPelicula;
    String tipoEmision;
    BigDecimal costo;
    Timestamp fechaHora;

    public Screening(
            int id,
            int idSala,
            int idPelicula,
            String tipoEmision,
            BigDecimal costo,
            Timestamp fechaHora
    ) {
        this.id = id;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
        this.tipoEmision = tipoEmision;
        this.costo = costo;
        this.fechaHora = fechaHora;
    }

    public Screening(int idPelicula, int idSala, Timestamp fechaHora) {
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.fechaHora = fechaHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return idPelicula;
    }

    public void setMovie_id(int movie_id) {
        this.idPelicula = movie_id;
    }

    public int getRoom() {
        return idSala;
    }

    public void setRoom(int sala) {
        this.idSala = sala;
    }

    public Timestamp getScreeningStart() {
        return fechaHora;
    }

    public void setScreeningStart(Timestamp screeningStart) {
        this.fechaHora = screeningStart;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTipoEmision() {
        return tipoEmision;
    }

    public void setTipoEmision(String tipoEmision) {
        this.tipoEmision = tipoEmision;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Screening [" +
                "id=" + id +
                ", movie_id=" + idPelicula +
                ", room=" + idSala +
                ", screening_start=" + fechaHora +
                ']';
    }

    public static ArrayList<Screening> screeningList() {
        ArrayList<Screening> screenings = new ArrayList<>();

        try {
            String query = "SELECT * FROM horarios WHERE fechaHora >= NOW()";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                screenings.add(new Screening(
                        rs.getInt("id"),
                        rs.getInt("idSala"),
                        rs.getInt("idPelicula"),
                        rs.getString("tipoEmision"),
                        rs.getBigDecimal("costo"),
                        rs.getTimestamp("fechaHora")
                ));
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return screenings;
    }

    public static int obtenerDisponibilidad(int idHorario) {
        int disponibilidad = 0;
        try {

            String query = "{CALL obtenerHorarioDisponibilidad(?)}";
            CallableStatement st = MYSQLConnection.conn.prepareCall(query);
            st.setInt(1, idHorario);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                disponibilidad = rs.getInt("faltanteEspacio");
            }

            st.execute();
            st.close();
        } catch (SQLException ex) {
            /*if(ex.getSQLState().equals("45000")) {
                System.out.println("Hola");
                return false;
            }*/
            ex.printStackTrace();
        }

        return disponibilidad;
    }

    public static ArrayList<Screening> screeningList(int idPelicula) {
        ArrayList<Screening> screenings = new ArrayList<>();

        try {
            String query = "SELECT * FROM horarios WHERE idPelicula = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setInt(1,idPelicula);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                screenings.add(new Screening(
                        rs.getInt("id"),
                        rs.getInt("idSala"),
                        rs.getInt("idPelicula"),
                        rs.getString("tipoEmision"),
                        rs.getBigDecimal("costo"),
                        rs.getTimestamp("fechaHora")
                ));
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return screenings;
    }

    public static boolean insertPrepared(Screening s) {
        try {

            String query = "{CALL spInsertarHorario(?, ?, ?)}";
            CallableStatement st = MYSQLConnection.conn.prepareCall(query);

            st.setInt(1, s.getRoom());
            st.setInt(2, s.getMovieId());
            st.setTimestamp(3, s.getScreeningStart());

            st.execute();
            st.close();
        } catch (SQLException ex) {
            if(ex.getSQLState().equals("45000")) {
                System.out.println("Hola");
                return false;
            }
            ex.printStackTrace();
        }
        return true;
    }

    public static void update(Screening s) {
        System.out.println(s.getMovieId() + " "+ s.getRoom()+ " "+ s.getScreeningStart()+ " ");
        try {
            String query = "{CALL spActualizarHorarios(?,?, ?, ?)}";
            CallableStatement st = MYSQLConnection.conn.prepareCall(query);

            st.setInt(1, s.getId());
            st.setInt(2, s.getMovieId());
            st.setString(3, String.valueOf(s.getRoom() + 1));
            st.setTimestamp(4, s.getScreeningStart());

            st.execute();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public static void delete(int id) {
        try {
            String query = "DELETE FROM horarios WHERE id = ?";
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
            String query = "SELECT * FROM horarios WHERE id = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                screening = new Screening(
                        rs.getInt("id"),
                        rs.getInt("idSala"),
                        rs.getInt("idPelicula"),
                        rs.getString("tipoEmision"),
                        rs.getBigDecimal("costo"),
                        rs.getTimestamp("fechaHora")
                );
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return screening;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
}