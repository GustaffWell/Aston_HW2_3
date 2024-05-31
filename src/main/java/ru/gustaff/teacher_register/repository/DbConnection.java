package ru.gustaff.teacher_register.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String NAME = "user";
    private static final String PASSWORD = "pass";
    private static final String URL = "jdbc:postgresql://172.17.0.2:5432/hw";

    public static boolean isTestProfile = false;

    private DbConnection() {
    }

    public static Connection getConnection() {
        return isTestProfile ? getTestConnection() : getProdConnection();
    }

    private static Connection getProdConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static Connection getTestConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:tc:postgresql:14.5:///hw?TC_INITSCRIPT=db/initDB.sql",
                    "test", "test");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
