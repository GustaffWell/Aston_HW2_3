package ru.gustaff.teacher_rerister.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String NAME = "user";
    private static final String PASSWORD = "pass";
    private static final String URL = "jdbc:postgresql://localhost:5433/hw";

    private DbConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }
}
