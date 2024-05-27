
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DetalleRegistro extends JFrame {

    private Registro registro;
    private Interfaz interfaz;

    private JTextField idtxt, nombretxt, apellidotxt, cedulatxt, telefonotxt,
            direcciontxt, añotxt, marcatxt, modelotxt, colortxt,
            imagentxt;
    private JLabel lblImagen;
    private JButton btnSeleccionarColor, btnSeleccionarImagen;
    private Color colorSeleccionado;
    private String rutaImagenSeleccionada;

    public DetalleRegistro(Registro registro) {
        Interfaz interfaz = new Interfaz();
        this.registro = registro;
        this.interfaz = interfaz;
        this.colorSeleccionado = registro.getColor();
        this.rutaImagenSeleccionada = registro.getImagen();
        setTitle("Detalle del registro");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(13, 2));

        add(new JLabel("ID: "));
        add(new JLabel(String.valueOf(registro.getId())));

        add(new JLabel("Nombre: "));
        nombretxt = new JTextField(registro.getNombre());
        add(nombretxt);

        add(new JLabel("Apellido: "));
        apellidotxt = new JTextField(registro.getApellido());
        add(apellidotxt);

        add(new JLabel("Cédula: "));
        cedulatxt = new JTextField(String.valueOf(registro.getCedula()));
        add(cedulatxt);

        add(new JLabel("Teléfono: "));
        telefonotxt = new JTextField(registro.getTelefono());
        add(telefonotxt);

        add(new JLabel("Dirección: "));
        direcciontxt = new JTextField(registro.getDireccion());
        add(direcciontxt);

        add(new JLabel("Año del Vehículo: "));
        añotxt = new JTextField(String.valueOf(registro.getAño()));
        add(añotxt);

        add(new JLabel("Marca: "));
        marcatxt = new JTextField(registro.getMarca());
        add(marcatxt);

        add(new JLabel("Modelo: "));
        modelotxt = new JTextField(registro.getModelo());
        add(modelotxt);

        add(new JLabel("Imagen: "));
        lblImagen = new JLabel(rutaImagenSeleccionada);
        add(lblImagen);
        btnSeleccionarImagen = new JButton("Seleccionar Imagen");
        btnSeleccionarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarImagen();
            }
        });
        add(btnSeleccionarImagen);

        add(new JLabel("Color: "));
        btnSeleccionarColor = new JButton("Seleccionar Color");
        btnSeleccionarColor.setBackground(colorSeleccionado);
        btnSeleccionarColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarColor();
            }
        });
        //CREACION DE BOTONES
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnAlquilar = new JButton("Alquilar");
        JButton btnVender = new JButton("Vender");

        add(btnModificar);
        add(btnEliminar);
        add(btnAlquilar);
        add(btnVender);

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

            }
        });

        btnVender.addActionListener(new ActionListener() { //VENDER
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void seleccionarImagen() {
        JFileChooser seleccion = new JFileChooser();
        int cuadro = seleccion.showOpenDialog(this); //abrir el cuadro de seleccion
        if (cuadro == JFileChooser.APPROVE_OPTION) {
            imagentxt.setText(seleccion.getSelectedFile().getPath());
        }
    }

    private void seleccionarColor() {
        Color color = JColorChooser.showDialog(this, "Seleccione un Color", colorSeleccionado);
        if (color != null) {
            colorSeleccionado = color;
            btnSeleccionarColor.setBackground(color);
        }
    }

    private void modificarRegistro() {
        registro.setNombre(nombretxt.getText());
        registro.setApellido(apellidotxt.getText());
        registro.setDireccion(direcciontxt.getText());
        registro.setMarca(marcatxt.getText());
        registro.setModelo(modelotxt.getText());
        registro.setTelefono(telefonotxt.getText());
        //registro.setImagen(imagentxt.getText());
        registro.setColor(colorSeleccionado);

        int year = Integer.parseInt(añotxt.getText());
        registro.setAño(year);
        int cedu = Integer.parseInt(cedulatxt.getText());
        registro.setCedula(cedu);

        interfaz.actualizarTabla();
        JOptionPane.showMessageDialog(this, "Registro Modificado exitosamente");
        //VALIDAR
    }
    
    private void eliminarRegistro(){
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if(confirmar == JOptionPane.YES_OPTION){
            interfaz.elliminarRegistro(registro);
            dispose();
        }
        
    }
}