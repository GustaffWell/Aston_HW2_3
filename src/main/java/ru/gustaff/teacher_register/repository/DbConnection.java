package ru.gustaff.teacher_register.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String NAME = "user";
    private static final String PASSWORD = "pass";
    private static final String URL = "jdbc:postgresql://172.17.0.2:5432/hw";

    private DbConnection() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        }
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }
}
