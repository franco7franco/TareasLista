import javax.swing.*;
import java.awt.*;

public class VentanaInicio extends JFrame {
    public VentanaInicio() {
        setTitle("GEST-TASK");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("GEST-TASK", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton ingresar = new JButton("Ingresar");
        JButton cerrar = new JButton("Cerrar");

        ingresar.addActionListener(e -> {
            new VentanaSeleccion().setVisible(true);
            dispose();
        });

        cerrar.addActionListener(e -> System.exit(0));

        botonesPanel.add(ingresar);
        botonesPanel.add(cerrar);
        add(botonesPanel, BorderLayout.CENTER);
    }
}