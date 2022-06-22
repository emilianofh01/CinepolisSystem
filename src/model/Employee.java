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
import java.sql.*;
import java.util.ArrayList;

public class Employee implements Serializable {
    private int id;
    private String username;
    private String password;
    private boolean admin;

    public Employee(int id, String username, String password, Boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public Employee(String username, String password, Boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }

    public boolean comparePassword(String password) {
        return password.equals(this.getPassword());
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", username=" + username + "]";
    }

    public static ArrayList<Employee> employeeList() {
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            String query = "SELECT * FROM employee";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("is_admin")
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

            String query = "SELECT * FROM employee WHERE username = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("is_admin")
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

            String query = "INSERT INTO employee (username, password, is_admin) VALUES (?,?,?)";
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
            st.setInt(4, e.getId());

            st.execute();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            String query = "DELETE FROM employee WHERE id = ?";
            PreparedStatement st = MYSQLConnection.conn.prepareStatement(query);
            st.setInt(1, id);

            st.executeUpdate(query);
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
