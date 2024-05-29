import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Alquiler extends JFrame {
    private Vehiculo vehiculo;
    private JTextField diasAlquilerTxt;
    private JLabel precioPorDiaLbl, precioTotalLbl, precioConDescuentoLbl;

    public Alquiler(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        setTitle("Alquilar Vehículo");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Marca
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Marca:"), gbc);
        
        gbc.gridx = 1;
        add(new JLabel(vehiculo.getMarca()), gbc);
        y++;

        // Modelo
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Modelo:"), gbc);
        
        gbc.gridx = 1;
        add(new JLabel(vehiculo.getModelo()), gbc);
        y++;
        
        // Tipo
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Tipo:"), gbc);
        
        gbc.gridx = 1;
        add(new JLabel(vehiculo.getTipo()), gbc);
        y++;
        

        // Precio por día
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Precio de Alquiler:"), gbc);
        
        gbc.gridx = 1;
        precioPorDiaLbl = new JLabel(String.format("%.2f", vehiculo.getPrecioAlquilerPorDia()));
        add(precioPorDiaLbl, gbc);
        y++;

        // Descuento
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Descuento:"), gbc);
        
        gbc.gridx = 1;
        add(new JLabel(String.format("%.2f%%", vehiculo.getDescuento() * 100)), gbc);
        y++;

        // Días de Alquiler
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Días de Alquiler:"), gbc);
        
        gbc.gridx = 1;
        diasAlquilerTxt = new JTextField();
        add(diasAlquilerTxt, gbc);
        y++;

        // Precio Total
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Precio Total:"), gbc);
        
        gbc.gridx = 1;
        precioTotalLbl = new JLabel();
        add(precioTotalLbl, gbc);
        y++;

        // Precio con Descuento
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Precio Total con Descuento:"), gbc);
        
        gbc.gridx = 1;
        precioConDescuentoLbl = new JLabel();
        add(precioConDescuentoLbl, gbc);
        y++;

        // Botón Calcular
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        JButton calcularBtn = new JButton("Calcular");
        calcularBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPrecios();
            }
        });
        add(calcularBtn, gbc);
    }

    private void calcularPrecios() {
        try {
            int dias = Integer.parseInt(diasAlquilerTxt.getText());
            double precioTotal = vehiculo.getPrecioAlquilerPorDia() * dias;
            double precioConDescuento = precioTotal - (precioTotal * vehiculo.getDescuento());

            precioTotalLbl.setText(String.format("%.2f", precioTotal));
            precioConDescuentoLbl.setText(String.format("%.2f", precioConDescuento));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido de días.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}