package ru.filimonov.steps.java.fox.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String PASS_KEY = "db.password";
    private static final String USER_KEY = "db.user";
    private static final String URL_KEY = "db.url";

    private ConnectionManager() {
    }

    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_KEY),
                    PropertiesUtil.get(PASS_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
