package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {
    private static final String URL = "jdbc:sqlite:tasks.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
