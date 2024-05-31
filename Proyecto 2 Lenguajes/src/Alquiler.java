import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Alquiler extends JFrame {
    private Vehiculo vehiculo;
    private JTextField diasAlquilerTxt;
    private JLabel precioPorDiaLbl, precioTotalLbl, precioConDescuentoLbl;

    public Alquiler(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;

        setTitle("Alquilar");
        setSize(244, 278);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Crear el panel principal y configurar su color de fondo
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(50, 145, 157));
        Font fuente = new Font("Century Schoolbook", Font.PLAIN, 12);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Marca
        addLabelyComponentes(new JLabel("Marca:"), new JLabel(vehiculo.getMarca()), gbc, y++, panelPrincipal, fuente);

        // Modelo
        addLabelyComponentes(new JLabel("Modelo:"), new JLabel(vehiculo.getModelo()), gbc, y++, panelPrincipal, fuente);
        
        // Tipo
        addLabelyComponentes(new JLabel("Tipo:"), new JLabel(vehiculo.getTipo()), gbc, y++, panelPrincipal, fuente);
        
        // Precio por día
        precioPorDiaLbl = new JLabel(String.format("%.2f", vehiculo.getPrecioAlquilerPorDia()) + "$");
        addLabelyComponentes(new JLabel("Precio de Alquiler:"), precioPorDiaLbl, gbc, y++, panelPrincipal, fuente);

        // Descuento
        addLabelyComponentes(new JLabel("Descuento:"), new JLabel(String.format("%.2f%%", vehiculo.getDescuento() * 100)), gbc, y++, panelPrincipal, fuente);

        // Días de Alquiler
        diasAlquilerTxt = new JTextField();
        addLabelyComponentes(new JLabel("Días de Alquiler:"), diasAlquilerTxt, gbc, y++, panelPrincipal, fuente);

        // Precio Total
        precioTotalLbl = new JLabel();
        addLabelyComponentes(new JLabel("Precio Total:"), precioTotalLbl, gbc, y++, panelPrincipal, fuente);

        // Precio con Descuento
        precioConDescuentoLbl = new JLabel();
        addLabelyComponentes(new JLabel("Precio Total con Descuento:"), precioConDescuentoLbl, gbc, y++, panelPrincipal, fuente);

        // Botón Calcular
        JButton calcularBtn = new JButton("Calcular");
        calcularBtn.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = y++;
        gbc.gridwidth = 2;
        panelPrincipal.add(calcularBtn, gbc);

        calcularBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPrecios();
            }
        });

        // Añadir el panel principal al frame
        add(panelPrincipal);
    }

    private void addLabelyComponentes(JLabel label, JComponent component, GridBagConstraints gbc, int y, JPanel panel, Font fuente) {
        label.setFont(fuente); // Configurar la fuente del JLabel
        component.setFont(fuente);

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private void calcularPrecios() {
        try {
            int dias = Integer.parseInt(diasAlquilerTxt.getText());
            double precioTotal = vehiculo.getPrecioAlquilerPorDia() * dias;
            double precioConDescuento = precioTotal - (precioTotal * vehiculo.getDescuento());

            precioTotalLbl.setText(String.format("%.2f", precioTotal) + "$");
            precioConDescuentoLbl.setText(String.format("%.2f", precioConDescuento) + "$");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido en días", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}