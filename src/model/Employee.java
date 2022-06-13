package model;

import db.MYSQLConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Employee implements Serializable {
    private int id;
    private String username;
    private String password;

    public Employee(int id, String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

            while(rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
                st.close();
                rs.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println(employees);
        return employees;
    }

}
