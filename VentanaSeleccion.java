import javax.swing.*;

public class VentanaSeleccion extends JFrame {
    public VentanaSeleccion() {
        setTitle("Seleccionar Usuario");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton volverBtn = new JButton("Volver al menú principal");
        volverBtn.addActionListener(e -> {
            dispose();
            new VentanaPrincipal().setVisible(true); // ← CORREGIDO
        });

        add(volverBtn);
    }
}