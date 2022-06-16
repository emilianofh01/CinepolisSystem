/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class MYSQLConnection {
    private static Connection conn = null;

    public static Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to database successfully established!");
        } catch(SQLException | ClassNotFoundException ex) {
            System.out.println("Connection to database failed!");
            ex.printStackTrace();
        }

        return conn;
    }
}
