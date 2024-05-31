import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Venta extends JFrame {
    private Vehiculo vehiculo;
    private JLabel precioVentaLbl, ivaLbl, precioTotalLbl, precioConDescuentoLbl;

    public Venta(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;

        setTitle("Vender");
        setSize(228, 269);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        int old = 2024 - vehiculo.getAño();

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
        
        // Antigüedad
        addLabelyComponentes(new JLabel("Antigüedad:"), new JLabel(Integer.toString(old) + " años"), gbc, y++, panelPrincipal, fuente);
        
        // Descuento
        addLabelyComponentes(new JLabel("Descuento:"), new JLabel(obtenerDescuentoPorAntiguedad(old) * 100 + "%"), gbc, y++, panelPrincipal, fuente);

        // Precio de Venta
        precioVentaLbl = new JLabel(String.format("%.2f", vehiculo.getPrecioVenta()) + "$");
        addLabelyComponentes(new JLabel("Precio de Venta:"), precioVentaLbl, gbc, y++, panelPrincipal, fuente);

        // IVA
        ivaLbl = new JLabel();
        addLabelyComponentes(new JLabel("IVA (16%):"), ivaLbl, gbc, y++, panelPrincipal, fuente);

        // Precio Total de Venta
        precioTotalLbl = new JLabel();
        addLabelyComponentes(new JLabel("Precio Total de Venta:"), precioTotalLbl, gbc, y++, panelPrincipal, fuente);

        // Precio con Descuento
        precioConDescuentoLbl = new JLabel();
        addLabelyComponentes(new JLabel("Precio con Descuento:"), precioConDescuentoLbl, gbc, y++, panelPrincipal, fuente);

        // Añadir el panel principal al frame
        add(panelPrincipal);

        calcularPrecios(); // Calcula los precios al iniciar la ventana
    }

    private void addLabelyComponentes(JLabel label, JComponent component, GridBagConstraints gbc, int y, JPanel panel, Font fuente) {
        label.setFont(fuente); // Configurar la fuente del JLabel
        component.setFont(component instanceof JLabel ? fuente : component.getFont());
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private void calcularPrecios() {
        double precioVenta = vehiculo.getPrecioVenta();
        double iva = precioVenta * 0.16;
        double precioTotal = precioVenta + iva;
        int añoActual = 2024;
        int antiguedad = añoActual - vehiculo.getAño();
        double descuento = obtenerDescuentoPorAntiguedad(antiguedad);
        double precioConDescuento = precioTotal - (precioTotal * descuento);

        ivaLbl.setText(String.format("%.2f", iva) + "$");
        precioTotalLbl.setText(String.format("%.2f", precioTotal) + "$");
        precioConDescuentoLbl.setText(String.format("%.2f", precioConDescuento) + "$");
    }

    private double obtenerDescuentoPorAntiguedad(int antiguedad) {
        return antiguedad < 1 ? 0.0 : antiguedad <= 10 ? 0.05 : antiguedad <= 20 ? 0.10 : 0.20;
    }
}