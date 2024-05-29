
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
                abrirAlquilar();
            }
        });

        btnVender.addActionListener(new ActionListener() { //VENDER
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVender();
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
        return marca.equals("Ford") ? new String[]{"Fiesta", "Focus", "Mustang"} :
               marca.equals("Chevrolet") ? new String[]{"Silverado", "Cruze", "Camaro"} :
               marca.equals("Toyota") ? new String[]{"Corolla", "Camry", "RAV4"} :
               marca.equals("Honda") ? new String[]{"Civic", "Accord", "CR-V"} :
               marca.equals("Volkswagen") ? new String[]{"Golf", "Jetta", "Tiguan"} :
               new String[]{"Seleccione modelo"};
    }

    private void seleccionarImagen() {
        JFileChooser seleccion = new JFileChooser();
        File ruta = new File("C:\\Users\\Jean Odriozola\\Documents\\GitHub\\Vehiculos-Java\\Proyecto 2 Lenguajes\\src\\fotos");//instancia de la ruta
        seleccion.setCurrentDirectory(ruta); //establecemos la ruta inicial del selector de imagenes
        seleccion.setFileFilter(new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png"));        
        int cuadro = seleccion.showOpenDialog(this); //abrir el cuadro de seleccion
        rutaImagenSeleccionada = (cuadro == JFileChooser.APPROVE_OPTION) ? seleccion.getSelectedFile().getPath() : rutaImagenSeleccionada;
        lblRutaImagen.setText(rutaImagenSeleccionada);
        actualizarImagen(rutaImagenSeleccionada);
    }

    private void actualizarImagen(String rutaImagen) {
        ImageIcon icon = new ImageIcon(rutaImagen);
        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(image));
    }

    private void seleccionarColor() {
        Color color = JColorChooser.showDialog(this, "Seleccione un Color", colorSeleccionado);
        colorSeleccionado = (color != null) ? color : colorSeleccionado;
        btnSeleccionarColor.setBackground(colorSeleccionado);
    }

    private void actualizarModelo() {
        comboboxModelo.setModel(new javax.swing.DefaultComboBoxModel<>(obtenerModelos((String) comboboxMarca.getSelectedItem())));
    }

private void modificarRegistro() {
    try {
        int cedula = Integer.parseInt(cedulatxt.getText());
        int año = Integer.parseInt(añotxt.getText());
        
        // Verificar si todos los campos están llenos
        boolean camposLlenos = !nombretxt.getText().isEmpty() && !apellidotxt.getText().isEmpty() && 
                               !cedulatxt.getText().isEmpty() && !telefonotxt.getText().isEmpty() && 
                               !direcciontxt.getText().isEmpty() && !placatxt.getText().isEmpty() && 
                               !añotxt.getText().isEmpty() && !comboboxMarca.getSelectedItem().equals("Seleccione marca") && 
                               !comboboxModelo.getSelectedItem().equals("Seleccione modelo");

        // Validaciones de los campos
        String message = !camposLlenos ? "Todos los campos deben estar llenos"
                        : !nombretxt.getText().matches("^[a-zA-ZñÑÁÉÍÓÚáéíóú]+$") ? "El nombre solo debe contener letras"
                        : !apellidotxt.getText().matches("^[a-zA-ZñÑÁÉÍÓÚáéíóú]+$") ? "El apellido solo debe contener letras"
                        : !(cedula >= 1 && cedula < 40000001) ? "Introduzca una cédula válida (Máximo 40 millones)"
                        : !placatxt.getText().matches("^[A-Z0-9-]+$") ? "La placa debe contener solo letras mayúsculas, números y puede contener guiones (-)"
                        : !telefonotxt.getText().matches("^0[0-9]{3}-[0-9]{7}$") ? "Introduzca un teléfono válido (0XXX-XXXXXXX)"
                        : !direcciontxt.getText().matches("^[a-zA-ZñÑ\\s\\-\\.]+$") ? "Introduzca una dirección válida"
                        : !(año >= 1960 && año <= 2024) ? "Introduzca un año válido (1960-2024)"
                        : "Datos validados exitosamente";

        JOptionPane.showMessageDialog(this, message);

        if ("Datos validados exitosamente".equals(message)) {
            registro.setNombre(nombretxt.getText());
            registro.setApellido(apellidotxt.getText());
            registro.setDireccion(direcciontxt.getText());
            registro.setMarca((String) comboboxMarca.getSelectedItem());
            registro.setModelo((String) comboboxModelo.getSelectedItem());
            registro.setTelefono(telefonotxt.getText());
            registro.setColor(colorSeleccionado);
            registro.setImagen(rutaImagenSeleccionada);
            registro.setPlaca(placatxt.getText());
            registro.setAño(año);
            registro.setCedula(cedula);

            interfaz.actualizarTabla();
            JOptionPane.showMessageDialog(this, "Registro Modificado exitosamente");
            this.dispose();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Debe llenar la Cédula y el Año del registro adecuadamente", "Error", JOptionPane.ERROR_MESSAGE);
    }
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
    
        private void abrirAlquilar() {
            String marca = (String) comboboxMarca.getSelectedItem();
            String modelo = (String) comboboxModelo.getSelectedItem();
            int year = Integer.parseInt(añotxt.getText());
            Vehiculo vehiculo =
                (modelo.equals("Camry") || modelo.equals("Accord") || modelo.equals("Jetta") || modelo.equals("Fiesta") ||
                 modelo.equals("Cruze") || modelo.equals("Corolla") || modelo.equals("Civic")) ?
                    new Sedan(marca, modelo, "Sedán", year) :
                (modelo.equals("RAV4") || modelo.equals("CR-V") || modelo.equals("Tiguan") || modelo.equals("Silverado")) ?
                    new Camioneta(marca, modelo, "Camioneta", year) :
                (modelo.equals("Mustang") || modelo.equals("Camaro") || modelo.equals("Focus") || modelo.equals("Golf")) ?
                    new Deportivo(marca, modelo, "Deportivo", year) :
                null;

            Alquiler alquilerVentana = vehiculo != null ? new Alquiler(vehiculo) : null;
            alquilerVentana.setVisible(vehiculo != null);
        }
        
        private void abrirVender() {
            String marca = (String) comboboxMarca.getSelectedItem();
            String modelo = (String) comboboxModelo.getSelectedItem();
            int year = Integer.parseInt(añotxt.getText());
            Vehiculo vehiculo =
                (modelo.equals("Camry") || modelo.equals("Accord") || modelo.equals("Jetta") || modelo.equals("Fiesta") ||
                 modelo.equals("Cruze") || modelo.equals("Corolla") || modelo.equals("Civic")) ?
                    new Sedan(marca, modelo, "Sedán", year) :
                (modelo.equals("RAV4") || modelo.equals("CR-V") || modelo.equals("Tiguan") || modelo.equals("Silverado")) ?
                    new Camioneta(marca, modelo, "Camioneta", year) :
                (modelo.equals("Mustang") || modelo.equals("Camaro") || modelo.equals("Focus") || modelo.equals("Golf")) ?
                    new Deportivo(marca, modelo, "Deportivo", year) :
                null;

            Venta alquilerVentana = vehiculo != null ? new Venta(vehiculo) : null;
            alquilerVentana.setVisible(vehiculo != null);
    }        
}