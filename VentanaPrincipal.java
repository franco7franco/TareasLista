import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("GEST-TASK - Menú Principal");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1, 10, 10));

        JButton crearBtn = new JButton("Crear cuenta");
        JButton abrirBtn = new JButton("Abrir cuenta");
        JButton salirBtn = new JButton("Salir");

        crearBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Ingrese nuevo ID:");
            if (id != null && !id.isEmpty()) {
                new Usuario(id);
                JOptionPane.showMessageDialog(this, "Cuenta creada.");
            }
        });

        abrirBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Ingrese ID:");
            if (id != null && !id.isEmpty()) {
                Usuario usuario = new Usuario(id);
                JOptionPane.showMessageDialog(this, "Cuenta abierta.");
                dispose(); // cerrar menú principal
                new VentanaMenuUsuario(usuario).setVisible(true);
            }
        });

        salirBtn.addActionListener(e -> System.exit(0));

        add(crearBtn);
        add(abrirBtn);
        add(salirBtn);
    }
}