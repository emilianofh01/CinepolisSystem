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
        ArrayList<Employee> employees = new ArrayList<Employee>();

        Connection conn = MYSQLConnection.getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM employee";
            rs = st.executeQuery(query);

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("admin")
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

        System.out.println(employees);
        return employees;
    }

    public static Employee getEmployee(String username) {

        Employee employee = null;

        Connection conn = MYSQLConnection.getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {

            st = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String consulta = "SELECT * FROM employee WHERE username = '" + username + "';";
            rs = st.executeQuery(consulta);

            if (rs.next()) {
                employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("admin")
                );
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                conn.close();
                st.close();
                rs.close();
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
        }

        return employee;
    }

    public static void insertPrepared(Employee e) {

        Connection con = MYSQLConnection.getConnection();
        PreparedStatement st = null;

        try {

            String query = "INSERT INTO employee (username, password, admin) VALUES (?,?,?)";

            st = con.prepareStatement(query);
            st.setString(1, e.getUsername());
            st.setString(2, e.getPassword());
            st.setBoolean(3, e.isAdmin());

            st.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void update(Employee e) {

        PreparedStatement st = null;

        try (Connection conn = MYSQLConnection.getConnection()) {

            String query = "UPDATE employee SET "
                    + "username = ?,"
                    + " password= ?,"
                    + " admin = ?"
                    + " WHERE id = ?";
            st = conn.prepareStatement(query);

            st.setString(1, e.getUsername());
            st.setString(2, e.getPassword());
            st.setBoolean(3, e.isAdmin());
            st.setInt(4, e.getId());

            st.execute();

        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                st.close();
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void delete(int id) {

        Connection conn = MYSQLConnection.getConnection();
        Statement st = null;

        try {
            st = (Statement) conn.createStatement();
            String query = "DELETE FROM employee WHERE id = " + id;

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
