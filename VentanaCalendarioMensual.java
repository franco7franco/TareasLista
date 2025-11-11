import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class VentanaCalendarioMensual extends JFrame {
    public VentanaCalendarioMensual(Usuario usuario) {
        setTitle("Calendario Mensual - " + usuario.id);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        LocalDate hoy = LocalDate.now();
        YearMonth mesActual = YearMonth.of(hoy.getYear(), hoy.getMonth());
        int diasEnMes = mesActual.lengthOfMonth();
        int primerDiaSemana = mesActual.atDay(1).getDayOfWeek().getValue(); // 1 = lunes, 7 = domingo

        JPanel calendarioPanel = new JPanel(new GridLayout(0, 7, 5, 5));
        String[] diasSemana = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
        for (String dia : diasSemana) {
            JLabel label = new JLabel(dia, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            calendarioPanel.add(label);
        }

        for (int i = 1; i < primerDiaSemana; i++) {
            calendarioPanel.add(new JLabel(""));
        }

        List<Tarea> tareas = usuario.getTareas();

        for (int dia = 1; dia <= diasEnMes; dia++) {
            LocalDate fecha = mesActual.atDay(dia);
            JTextArea celda = new JTextArea();
            celda.setEditable(false);
            celda.setBackground(Color.WHITE);
            celda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            celda.setFont(new Font("Arial", Font.PLAIN, 12));
            celda.setText("Día " + dia);

            for (Tarea t : tareas) {
                if (t.getDia().equals(fecha)) {
                    celda.append("\n• " + t.titulo);
                }
            }

            calendarioPanel.add(celda);
        }

        JScrollPane scroll = new JScrollPane(calendarioPanel);
        add(scroll);
    }
}