/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package model;

import db.MYSQLConnection;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class Employee implements Serializable {
    private Blob id;
    private String nombre, apPaterno, apMaterno, rfc, email, usuario, apswrd;
    private Timestamp fechaNacimiento;
    private boolean admin;

    public Employee(Blob id,
                    String nombre,
                    String apPaterno,
                    String apMaterno,
                    Timestamp fechaNacimiento,
                    String rfc,
                    String email,
                    String usuario,
                    String apswrd,
                    Boolean admin
    ) {
        this.id = id;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.rfc = rfc;
        this.email = email;
        this.usuario = usuario;
        this.apswrd = apswrd;
        this.admin = admin;
    }

    public Employee(String username,
                    String password,
                    Boolean admin) {
        this.usuario = username;
        this.usuario = password;
        this.admin = admin;
    }

    public Blob getId() {
        return id;
    }

    public String getUsername() {
        return usuario;
    }

    public void setUsername(String username) {
        this.usuario = username;
    }

    private String getPassword() {
        return apswrd;
    }

    public boolean comparePassword(String password) throws NoSuchAlgorithmException {
        return sha2(password).equals(getPassword());
    }

    private String sha2(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(input.getBytes(StandardCharsets.UTF_8));

        return String.format("%064x", new BigInteger(1,digest.digest()));
    }

    public void setPassword(String password) {
        this.apswrd = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", username=" + usuario + "]";
    }

    public static ArrayList<Employee> employeeList() {
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            String query = "SELECT * FROM empleados";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getBlob("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"),
                        rs.getTimestamp("fechaNacimiento"),
                        rs.getString("rfc"),
                        rs.getString("email"),
                        rs.getString("usuario"),
                        rs.getString("apswrd"),
                        rs.getBoolean("isAdmin")
                ));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employees;
    }

    public static Employee getEmployee(String username) {
        Employee employee = null;

        try {

            String query = "SELECT * FROM empleados WHERE usuario = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                employee = new Employee(
                        rs.getBlob("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"),
                        rs.getTimestamp("fechaNacimiento"),
                        rs.getString("rfc"),
                        rs.getString("email"),
                        rs.getString("usuario"),
                        rs.getString("apswrd"),
                        rs.getBoolean("isAdmin")
                );
            }

            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }

    public static void insertPrepared(Employee e) {
        try {

            String query = "INSERT INTO empleados (username, password, is_admin) VALUES (?,?,?)";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);

            st.setString(1, e.getUsername());
            st.setString(2, e.getPassword());
            st.setBoolean(3, e.isAdmin());

            st.execute();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void update(Employee e) {
        try {
            String query = "UPDATE employee SET "
                    + "username = ?,"
                    + " password= ?,"
                    + " is_admin = ?"
                    + " WHERE id = ?";

            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setString(1, e.getUsername());
            st.setString(2, e.getPassword());
            st.setBoolean(3, e.isAdmin());
            st.setBlob(4, e.getId());

            st.execute();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            String query = "DELETE FROM empleados WHERE id = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setInt(1, id);

            st.executeUpdate(query);
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
