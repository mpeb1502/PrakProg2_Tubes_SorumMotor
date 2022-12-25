package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {

    // Setting of Database
    private final static String DB_URL = "jdbc:mysql://localhost:3306/pp2_sorum_tubes";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "";
    // End off Setting Database

    private static MySqlConnection instance;

    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    // Get Koneksi Database
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}