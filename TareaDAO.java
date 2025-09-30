package app.dao;


import app.model.Tarea;
import app.model.Estado;
import app.model.Prioridad;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class TareaDAO {
    private Connection conn;

    public TareaDAO(Connection conn) {
        this.conn = conn;
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS tasks (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "title TEXT NOT NULL," +
                     "status TEXT," +
                     "dueDateTime TEXT," +
                     "priority TEXT," +
                     "tags TEXT," +
                     "reminder TEXT" +
                     ")";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    public void insert(Tarea task) throws SQLException {
        String sql = "INSERT INTO tasks(title,status,dueDateTime,priority,tags,reminder) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getStatus().name());
            ps.setString(3, task.getDueDateTime().toString());
            ps.setString(4, task.getPriority().name());
            ps.setString(5, task.getTags());
            ps.setString(6, task.getReminder() != null ? task.getReminder().toString() : null);
            ps.executeUpdate();
        }
    }

    public List<Tarea> getAll() throws SQLException {
        List<Tarea> list = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Tarea t = new Tarea();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getString("title"));
                t.setStatus(Estado.valueOf(rs.getString("status")));
                t.setPriority(Prioridad.valueOf(rs.getString("priority")));
                t.setTags(rs.getString("tags"));
                t.setDueDateTime(LocalDateTime.parse(rs.getString("dueDateTime")));
                String reminderStr = rs.getString("reminder");
                if (reminderStr != null) t.setReminder(LocalDateTime.parse(reminderStr));
                list.add(t);
            }
        }
        return list;
    }

    public void markCompleted(int id) throws SQLException {
        String sql = "UPDATE tasks SET status='COMPLETED' WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public int deleteCompletedOrOverdue() throws SQLException {
        String sql = "DELETE FROM tasks WHERE status='COMPLETED' OR status='OVERDUE'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            return ps.executeUpdate();
        }
    }

    public List<Tarea> filter(Estado status, Prioridad priority) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM tasks WHERE 1=1");
        if (status != null) sql.append(" AND status='").append(status.name()).append("'");
        if (priority != null) sql.append(" AND priority='").append(priority.name()).append("'");
        List<Tarea> list = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql.toString())) {
            while (rs.next()) {
                Tarea t = new Tarea();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getString("title"));
                t.setStatus(Estado.valueOf(rs.getString("status")));
                t.setPriority(Prioridad.valueOf(rs.getString("priority")));
                t.setTags(rs.getString("tags"));
                t.setDueDateTime(LocalDateTime.parse(rs.getString("dueDateTime")));
                String reminderStr = rs.getString("reminder");
                if (reminderStr != null) t.setReminder(LocalDateTime.parse(reminderStr));
                list.add(t);
            }
        }
        return list;
    }

    public Optional<Tarea> findById(int id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Tarea t = new Tarea();
                    t.setId(rs.getInt("id"));
                    t.setTitle(rs.getString("title"));
                    t.setStatus(Estado.valueOf(rs.getString("status")));
                    t.setPriority(Prioridad.valueOf(rs.getString("priority")));
                    t.setTags(rs.getString("tags"));
                    t.setDueDateTime(LocalDateTime.parse(rs.getString("dueDateTime")));
                    String reminderStr = rs.getString("reminder");
                    if (reminderStr != null) t.setReminder(LocalDateTime.parse(reminderStr));
                    return Optional.of(t);
                }
            }
        }
        return Optional.empty();
    }
}