package model;

import db.MYSQLConnection;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRecord {
    Blob id;
    String idString;
    int cantidadAsientos;
    double montoTotal;

    public PaymentRecord(Blob id, int cantidadAsientos, double montoTotal) {
        this.id = id;
        this.cantidadAsientos = cantidadAsientos;
        this.montoTotal = montoTotal;
    }

    public static int encontrarHorarioPorIdVenta(Blob idVenta) {
        int id = 0;
        try {
            String query = "SELECT idHorario FROM boletos WHERE idventa = ? LIMIT 1";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setBlob(1, idVenta);

            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                id = rs.getInt("idHorario");
            }

            st.close();
            //rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return id;
    }

    public Blob getId() {
        return id;
    }

    public void setId(Blob id) {
        this.id = id;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public void setCantidadAsientos(int cantidadAsientos) {
        this.cantidadAsientos = cantidadAsientos;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}
