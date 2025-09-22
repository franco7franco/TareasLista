import service.TaskService;
import model.Task;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TareaGithub {
    public static void main(String[] args) {
        Servicios service = new TaskService();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== GESTOR DE TAREAS ===");
            System.out.println("1. Crear tarea");
            System.out.println("2. Filtrar por estado");
            System.out.println("3. Eliminar por estado");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Estado (pendiente/completada/retrasada): ");
                    String estado = sc.nextLine();
                    System.out.print("Prioridad (alta/media/baja): ");
                    String prioridad = sc.nextLine();
                    System.out.print("Categoría: ");
                    String categoria = sc.nextLine();
                    System.out.print("Recordatorio: ");
                    String recordatorio = sc.nextLine();
                    System.out.print("Zona horaria: ");
                    String zona = sc.nextLine();
                    System.out.print("Preferencias de notificación: ");
                    String notif = sc.nextLine();
                    System.out.print("Tema o vista: ");
                    String tema = sc.nextLine();

                    service.crearTarea(titulo, estado, LocalDateTime.now(), prioridad, categoria, recordatorio, zona, notif, tema);
                }
                case 2 -> {
                    System.out.print("Estado a filtrar: ");
                    String estado = sc.nextLine();
                    List<Tarea> tareas = service.filtrarPorEstado(estado);
                    tareas.forEach(t -> System.out.println(t.getId() + " - " + t.getTitulo() + " [" + t.getEstado() + "]"));
                }
                case 3 -> {
                    System.out.print("Estado a eliminar: ");
                    String estado = sc.nextLine();
                    service.eliminarPorEstado(estado);
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);

        sc.close();
    }
}
