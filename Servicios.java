package app.service;

import app.dao.TareaDAO;
import app.model.Tarea;
import app.model.Prioridad;
import app.model.Estado;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Servicios {
    private final TareaDAO dao;

    public Servicios(TareaDAO dao) {
        this.dao = dao;
    }

    public void crearTarea(String titulo, Prioridad prioridad, String etiquetas, LocalDateTime vencimiento) throws SQLException {
        Tarea t = new Tarea();
        t.setTitle(titulo);
        t.setPriority(prioridad);
        t.setTags(etiquetas);
        t.setDueDateTime(vencimiento);
        t.setStatus(Estado.PENDING);
        dao.insert(t);
    }

    public List<Tarea> listarTodas() throws SQLException {
        return dao.getAll();
    }

    public List<Tarea> filtrar(Estado estado, Prioridad prioridad) throws SQLException {
        return dao.filter(estado, prioridad);
    }

    public void marcarComoCompletada(int id) throws SQLException {
        dao.markCompleted(id);
    }

    public int eliminarCompletadasOCaducadas() throws SQLException {
        return dao.deleteCompletedOrOverdue();
    }

    public Optional<Tarea> buscarPorId(int id) throws SQLException {
        return dao.findById(id);
    }
}