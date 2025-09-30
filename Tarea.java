package app.model;

import java.time.LocalDateTime;

/**
 * Representa una tarea en el sistema.
 */
public class Tarea {
    private int id;
    private String title;
    private Estado status;
    private LocalDateTime dueDateTime;
    private Prioridad priority;
    private String tags;
    private LocalDateTime reminder;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Estado getStatus() { return status; }
    public void setStatus(Estado status) { this.status = status; }

    public LocalDateTime getDueDateTime() { return dueDateTime; }
    public void setDueDateTime(LocalDateTime dueDateTime) { this.dueDateTime = dueDateTime; }

    public Prioridad getPriority() { return priority; }
    public void setPriority(Prioridad priority) { this.priority = priority; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public LocalDateTime getReminder() { return reminder; }
    public void setReminder(LocalDateTime reminder) { this.reminder = reminder; }
}