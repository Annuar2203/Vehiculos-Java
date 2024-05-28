
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DetalleRegistro extends JFrame {

    private Registro registro;
    private Interfaz interfaz;

    private JComboBox<String> comboboxMarca;
    private JComboBox<String> comboboxModelo;    
    
    private JTextField nombretxt, apellidotxt, cedulatxt, telefonotxt,
            direcciontxt, añotxt, marcatxt, modelotxt, placatxt;
    private JLabel lblImagen, lblRutaImagen;
    private JButton btnSeleccionarColor, btnSeleccionarImagen;
    private Color colorSeleccionado;
    private String rutaImagenSeleccionada;

    public DetalleRegistro(Registro registro, Interfaz interfaz) {
        
        this.registro = registro;
        this.interfaz = interfaz;
        this.colorSeleccionado = registro.getColor();
        this.rutaImagenSeleccionada = registro.getImagen();
        setTitle("Detalles del registro");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.setResizable(false);

        int y = 0;

        // ID
        addLabelAndComponent(new JLabel("ID: "), new JLabel(String.valueOf(registro.getId())), gbc, y++);

        // Nombre
        nombretxt = new JTextField(registro.getNombre());
        addLabelAndComponent(new JLabel("Nombre: "), nombretxt, gbc, y++);

        // Apellido
        apellidotxt = new JTextField(registro.getApellido());
        addLabelAndComponent(new JLabel("Apellido: "), apellidotxt, gbc, y++);

        // Cédula
        cedulatxt = new JTextField(String.valueOf(registro.getCedula()));
        addLabelAndComponent(new JLabel("Cédula: "), cedulatxt, gbc, y++);

        // Teléfono
        telefonotxt = new JTextField(registro.getTelefono());
        addLabelAndComponent(new JLabel("Teléfono: "), telefonotxt, gbc, y++);

        // Placa
        placatxt = new JTextField(registro.getPlaca());
        addLabelAndComponent(new JLabel("Placa: "), placatxt, gbc, y++);        
        
        // Dirección
        direcciontxt = new JTextField(registro.getDireccion());
        addLabelAndComponent(new JLabel("Dirección: "), direcciontxt, gbc, y++);

        // Año del Vehículo
        añotxt = new JTextField(String.valueOf(registro.getAño()));
        addLabelAndComponent(new JLabel("Año del Vehículo: "), añotxt, gbc, y++);

        // Marca
        comboboxMarca = new JComboBox<>(new String[]{"Ford", "Chevrolet", "Toyota", "Honda", "Volkswagen"});
        comboboxMarca.setSelectedItem(registro.getMarca());
        addLabelAndComponent(new JLabel("Marca: "), comboboxMarca, gbc, y++);
        comboboxMarca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarModelo();
            }
        });

        // Modelo
        comboboxModelo = new JComboBox<>(obtenerModelos((String) comboboxMarca.getSelectedItem()));
        comboboxModelo.setSelectedItem(registro.getModelo());
        addLabelAndComponent(new JLabel("Modelo: "), comboboxModelo, gbc, y++);

        // Imagen
        lblRutaImagen = new JLabel(rutaImagenSeleccionada);
        lblImagen = new JLabel();
        actualizarImagen(rutaImagenSeleccionada);

        btnSeleccionarImagen = new JButton("Seleccionar Imagen");
        btnSeleccionarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarImagen();
            }
        });
        
        addLabelAndComponent(new JLabel("Ruta de Imagen: "), lblRutaImagen, gbc, y++);
        addLabelAndComponent(new JLabel("Imagen: "), lblImagen, gbc, y++);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = y++;
        add(btnSeleccionarImagen, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = y++;

        // Color
        btnSeleccionarColor = new JButton("Seleccionar Color");
        btnSeleccionarColor.setBackground(colorSeleccionado);
        btnSeleccionarColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarColor();
            }
        });
        addLabelAndComponent(new JLabel("Color: "), btnSeleccionarColor, gbc, y++);

        // Botones de acción
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnAlquilar = new JButton("Alquilar");
        JButton btnVender = new JButton("Vender");

        buttonPanel.add(btnModificar);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(btnEliminar);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(btnAlquilar);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(btnVender);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = y++;
        add(buttonPanel, gbc);

        //EVENTOS
        
        btnModificar.addActionListener(new ActionListener() { //MODIFICAR
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarRegistro();
            }
        });

        btnEliminar.addActionListener(new ActionListener() { //ELIMINAR
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarRegistro();
            }
        });

        btnAlquilar.addActionListener(new ActionListener() { //ALQUILAR
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para alquilar
            }
        });

        btnVender.addActionListener(new ActionListener() { //VENDER
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para vender
            }
        });
    }

    private void addLabelAndComponent(JLabel label, JComponent component, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        add(label, gbc);
        gbc.gridx = 1;
        add(component, gbc);
    }

    private String[] obtenerModelos(String marca) {
        switch (marca) {
            case "Ford":
                return new String[]{"Fiesta", "Focus", "Mustang"};
            case "Chevrolet":
                return new String[]{"Silverado", "Cruze", "Camaro"};
            case "Toyota":
                return new String[]{"Corolla", "Camry", "RAV4"};
            case "Honda":
                return new String[]{"Civic", "Accord", "CR-V"};
            case "Volkswagen":
                return new String[]{"Golf", "Jetta", "Tiguan"};
            default:
                return new String[]{"Seleccione modelo"};
        }
    }
    
    private void seleccionarImagen() {
        JFileChooser seleccion = new JFileChooser();
        File ruta = new File("C:\\Users\\Jean Odriozola\\Documents\\GitHub\\Vehiculos-Java\\Proyecto 2 Lenguajes\\src\\fotos");//instancia de la ruta
        seleccion.setCurrentDirectory(ruta); //establecemos la ruta inicial del selector de imagenes
        seleccion.setFileFilter(new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png"));        
        int cuadro = seleccion.showOpenDialog(this); //abrir el cuadro de seleccion
        if (cuadro == JFileChooser.APPROVE_OPTION) {
            rutaImagenSeleccionada = seleccion.getSelectedFile().getPath();
            lblRutaImagen.setText(rutaImagenSeleccionada);
            actualizarImagen(rutaImagenSeleccionada);
        }
    }

    private void actualizarImagen(String rutaImagen) {
        ImageIcon icon = new ImageIcon(rutaImagen);
        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(image));
    }

    private void seleccionarColor() {
        Color color = JColorChooser.showDialog(this, "Seleccione un Color", colorSeleccionado);
        if (color != null) {
            colorSeleccionado = color;
            btnSeleccionarColor.setBackground(color);
        }
    }

    private void actualizarModelo() {
        comboboxModelo.setModel(new javax.swing.DefaultComboBoxModel<>(obtenerModelos((String) comboboxMarca.getSelectedItem())));
    }

    private void modificarRegistro() {
        registro.setNombre(nombretxt.getText());
        registro.setApellido(apellidotxt.getText());
        registro.setDireccion(direcciontxt.getText());
        registro.setMarca((String) comboboxMarca.getSelectedItem());
        registro.setModelo((String) comboboxModelo.getSelectedItem());
        registro.setTelefono(telefonotxt.getText());
        registro.setColor(colorSeleccionado);
        registro.setImagen(rutaImagenSeleccionada);
        registro.setPlaca(placatxt.getText());

        int year = Integer.parseInt(añotxt.getText());
        registro.setAño(year);
        int cedu = Integer.parseInt(cedulatxt.getText());
        registro.setCedula(cedu);

        interfaz.actualizarTabla();
        JOptionPane.showMessageDialog(this, "Registro Modificado exitosamente");
        this.dispose();
    }
    
    private void eliminarRegistro(){
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if(confirmar == JOptionPane.YES_OPTION){
            interfaz.elliminarRegistro(registro);
            for (int i = 0; i < interfaz.listaReg.size(); i++) {
                interfaz.listaReg.get(i).setId(i + 1);
            }
          this.dispose();
        }
    }
}