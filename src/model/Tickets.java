package model;

import db.MYSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class Tickets {
    private int id;
    private int idHorario;
    private String codigoAsiento;
    private Blob idVenta;
    private String blobString;

    public Tickets(int id, int idHorario, String codigoAsiento, Blob idVenta) {
        this.id = id;
        this.idHorario = idHorario;
        this.codigoAsiento = codigoAsiento;
        this.idVenta = idVenta;
    }

    public Tickets(int idHorario, String codigoAsiento, Blob idVenta) {
        this.idHorario = idHorario;
        this.codigoAsiento = codigoAsiento;
        this.idVenta = idVenta;
    }

    public static Blob generateUUID() {
        Blob uuid = null;
        try {
            String query = "SELECT UUID() AS 'UUID'";
            CallableStatement st = MYSQLConnection.conn.prepareCall(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                uuid = rs.getBlob("UUID");
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(uuid);
        return uuid;
    }

    public static void createReport(Blob uuid, int cantidadAsientos, double montoTotal) {
        try {
            String query = "INSERT INTO registroVenta(id, cantidadAsientos, montoTotal) VALUES (?,?,?)";
            CallableStatement st = MYSQLConnection.conn.prepareCall(query);
            //ResultSet rs = st.executeQuery();

            st.setBlob(1, uuid);
            st.setInt(2, cantidadAsientos);
            st.setDouble(3, montoTotal);
            st.execute();

            st.close();
            //rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static PaymentRecord showPaymentRecord(Blob idVenta) {
        PaymentRecord registroComprobante = null;
        try {
            String query = "SELECT * FROM registroVenta WHERE id = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setBlob(1, idVenta);

            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                registroComprobante = new PaymentRecord(
                        rs.getBlob("id"),
                        rs.getInt("cantidadAsientos"),
                        rs.getDouble("montoTotal")
                );
                registroComprobante.setIdString(rs.getString("id"));
            }

            st.close();
            //rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return registroComprobante;
    }

    public static void buyTickets(ArrayList<Tickets> tickets) {
        try {
            String query = "{CALL spInsertarBoletos(?,?,?)}";
            CallableStatement st = MYSQLConnection.conn.prepareCall(query);
            //ResultSet rs = st.executeQuery();

            for(Tickets ticket : tickets) {
                st.setInt(1, ticket.idHorario);
                st.setString(2, ticket.codigoAsiento);
                st.setBlob(3, ticket.idVenta);

                st.addBatch();
            }

            st.executeBatch();

            st.close();
            //rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getCodigoAsiento() {
        return codigoAsiento;
    }

    public void setCodigoAsiento(String codigoAsiento) {
        this.codigoAsiento = codigoAsiento;
    }

    public Blob getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Blob idVenta) {
        this.idVenta = idVenta;
    }

    public String getBlobString() {
        return blobString;
    }

    public void setBlobString(String blobString) {
        this.blobString = blobString;
    }
}
