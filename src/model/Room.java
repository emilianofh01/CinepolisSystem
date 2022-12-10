package model;

import db.MYSQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Room {
    int id;
    int numAsiento;
    String tipo;

    Room(int id, int numAsiento, String tipo) {
        this.id = id;
        this.numAsiento = numAsiento;
        this.tipo = tipo;
    }

    public static ArrayList<Room> obtenerSalas() {
        ArrayList<Room> data = new ArrayList<>();

        try {
            String query = "SELECT * FROM salas";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                data.add(new Room(
                        rs.getInt("id"),
                        rs.getInt("numAsiento"),
                        rs.getString("tipo")
                ));
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return data;
    }

    public static Room obtenerSala(int idsala) {
        Room sala = null;

        try {
            String query = "SELECT * FROM salas WHERE id = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setInt(1, idsala);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                sala = new Room(
                        rs.getInt("id"),
                        rs.getInt("numAsiento"),
                        rs.getString("tipo")
                );
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sala;
    }

    public static String getTipoSala(int idSala) {
        String tipo = null;
        try {
            String query = "SELECT tipo FROM salas WHERE id = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setInt(1, idSala);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                tipo = rs.getString("tipo");
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(int numAsiento) {
        this.numAsiento = numAsiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
