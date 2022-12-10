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
    public static Connection conn = null;

    public static Connection getConnection() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String database = "cinepolis";
        String hostname = "localhost";
        String port = "3306";
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database; //+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        if (conn == null) {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("Connection to database successfully established!");
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Connection to database failed!");
                ex.printStackTrace();
            }
        }

        return conn;
    }

    public static void closeConnection() {
        if (conn == null)
            return;
        try {
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
