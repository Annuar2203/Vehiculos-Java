import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Venta extends JFrame {
    private Vehiculo vehiculo;
    private JLabel precioVentaLbl, ivaLbl, precioTotalLbl, precioConDescuentoLbl;

    public Venta(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        setTitle("Vender Vehículo");
        setSize(250, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        int old = 2024 - vehiculo.getAño();

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

        // Antiguedad
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Antiguedad:"), gbc);

        gbc.gridx = 1;
        add(new JLabel(Integer.toString(old)), gbc);
        y++;        


        // Descuento
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Descuento:"), gbc);

        gbc.gridx = 1;
        add(new JLabel(obtenerDescuentoPorAntiguedad(old)*100+"%"), gbc);
        y++;   
        
        // Precio de Venta
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Precio de Venta:"), gbc);

        gbc.gridx = 1;
        precioVentaLbl = new JLabel(String.format("%.2f", vehiculo.getPrecioVenta()));
        add(precioVentaLbl, gbc);
        y++;

        // IVA
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("IVA (16%):"), gbc);

        gbc.gridx = 1;
        ivaLbl = new JLabel();
        add(ivaLbl, gbc);
        y++;

        // Precio Total de Venta
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Precio Total de Venta:"), gbc);

        gbc.gridx = 1;
        precioTotalLbl = new JLabel();
        add(precioTotalLbl, gbc);
        y++;

        // Precio con Descuento
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Precio con Descuento:"), gbc);

        gbc.gridx = 1;
        precioConDescuentoLbl = new JLabel();
        add(precioConDescuentoLbl, gbc);
        y++;

        calcularPrecios(); // Calcula los precios al iniciar la ventana
    }

    private void calcularPrecios() {
        double precioVenta = vehiculo.getPrecioVenta();
        double iva = precioVenta * 0.16;
        double precioTotal = precioVenta + iva;
        int añoActual = 2024;
        int antiguedad = añoActual - vehiculo.getAño();
        double descuento = obtenerDescuentoPorAntiguedad(antiguedad);
        double precioConDescuento = precioTotal - (precioTotal * descuento);

        ivaLbl.setText(String.format("%.2f", iva));
        precioTotalLbl.setText(String.format("%.2f", precioTotal));
        precioConDescuentoLbl.setText(String.format("%.2f", precioConDescuento));
    }

    private double obtenerDescuentoPorAntiguedad(int antiguedad) {
        if (antiguedad < 1) {
            return 0.0; // Sin descuento
        } else if (antiguedad <= 10) {
            return 0.05; // 5% de descuento
        } else if (antiguedad <= 20) {
            return 0.10; // 10% de descuento
        } else {
            return 0.20; // 15% de descuento
        }
    }
}