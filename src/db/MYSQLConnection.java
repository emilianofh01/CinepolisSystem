package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class MYSQLConnection {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String database = "cinepolis-db";
    private static String hostname = "localhost";
    private static String port = "3306";
    private static String username = "root";
    private static String password = "secret";
    private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

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
