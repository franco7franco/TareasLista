package app.model;

/**
 * Representa las preferencias de configuración del usuario
 * para la aplicación de gestión de tareas.
 */
public class Preferencias {
    private String timezone;           // Ej: "America/La_Paz"
    private String dateFormat;         // Ej: "dd/MM/yyyy"
    private String timeFormat;         // Ej: "HH:mm"
    private String notificationChannel; // Ej: "email", "push", "sound"
    private String theme;              // Ej: "light" o "dark"
    private String favoriteFilters;    // Ej: "PENDING,HIGH"

    public Preferencias() {
        // Valores por defecto
        this.timezone = "America/La_Paz";
        this.dateFormat = "dd/MM/yyyy";
        this.timeFormat = "HH:mm";
        this.notificationChannel = "sound";
        this.theme = "dark";
        this.favoriteFilters = "";
    }

    // Getters y setters
    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }

    public String getDateFormat() { return dateFormat; }
    public void setDateFormat(String dateFormat) { this.dateFormat = dateFormat; }

    public String getTimeFormat() { return timeFormat; }
    public void setTimeFormat(String timeFormat) { this.timeFormat = timeFormat; }

    public String getNotificationChannel() { return notificationChannel; }
    public void setNotificationChannel(String notificationChannel) { this.notificationChannel = notificationChannel; }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }

    public String getFavoriteFilters() { return favoriteFilters; }
    public void setFavoriteFilters(String favoriteFilters) { this.favoriteFilters = favoriteFilters; }

    @Override
    public String toString() {
        return "Preferencias{" +
                "timezone='" + timezone + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", timeFormat='" + timeFormat + '\'' +
                ", notificationChannel='" + notificationChannel + '\'' +
                ", theme='" + theme + '\'' +
                ", favoriteFilters='" + favoriteFilters + '\'' +
                '}';
    }
}