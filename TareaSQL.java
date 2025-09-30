package app.dao;

import java.sql.*;

public class TareaSQL {
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:tasks.db");
    }
}
