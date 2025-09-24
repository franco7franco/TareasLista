package dao;
import model.Task;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TareDB {

    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS tasks (
          id INTEGER PRIMARY KEY AUTOINCREMENT,           -- Identificador único de la tarea
          titulo TEXT NOT NULL,                           -- Título de la tarea (obligatorio)
          estado TEXT,                                    -- Estado de la tarea (por ejemplo: pendiente, completada)
          fechaHora TEXT,                                 -- Fecha y hora asociada a la tarea
          prioridad TEXT,                                 -- Nivel de prioridad (alta, media, baja)
          categoria TEXT,                                 -- Categoría de la tarea (trabajo, personal, etc.)
          recordatorio TEXT,                              -- Información sobre recordatorios
          zonaHoraria TEXT,                               -- Zona horaria de la tarea
          preferenciasNotificacion TEXT,                  -- Preferencias de notificación
          temaVista TEXT                                  -- Tema visual o estilo de presentación
            )
        """;
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Task task) {
        String sql = "INSERT INTO tasks(titulo, estado, fechaHora, prioridad, categoria, recordatorio, zonaHoraria, preferenciasNotificacion, temaVista) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getTitulo());
            pstmt.setString(2, task.getEstado());
            pstmt.setString(3, task.getFechaHora().toString());
            pstmt.setString(4, task.getPrioridad());
            pstmt.setString(5, task.getCategoria());
            pstmt.setString(6, task.getRecordatorio());
            pstmt.setString(7, task.getZonaHoraria());
            pstmt.setString(8, task.getPreferenciasNotificacion());
            pstmt.setString(9, task.getTemaVista());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getByEstado(String estado) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE estado = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, estado);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setTitulo(rs.getString("titulo"));
                t.setEstado(rs.getString("estado"));
                t.setFechaHora(LocalDateTime.parse(rs.getString("fechaHora")));
                t.setPrioridad(rs.getString("prioridad"));
                t.setCategoria(rs.getString("categoria"));
                t.setRecordatorio(rs.getString("recordatorio"));
                t.setZonaHoraria(rs.getString("zonaHoraria"));
                t.setPreferenciasNotificacion(rs.getString("preferenciasNotificacion"));
                t.setTemaVista(rs.getString("temaVista"));
                tasks.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void deleteByEstado(String estado) {
        String sql = "DELETE FROM tasks WHERE estado = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, estado);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

