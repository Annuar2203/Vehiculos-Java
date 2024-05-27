
import java.awt.Color;

public class Registro {
    private String nombre,apellido,direccion,marca,modelo,telefono,imagen;
    private Color color;
    private int id,cedula,año;

    public Registro(String nombre, String apellido, String direccion, String marca, String modelo, String telefono, String imagen, Color color, int id, int cedula, int año) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.marca = marca;
        this.modelo = modelo;
        this.telefono = telefono;
        this.imagen = imagen;
        this.color = color;
        this.id = id;
        this.cedula = cedula;
        this.año = año;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getImagen() {
        return imagen;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public int getCedula() {
        return cedula;
    }

    public int getAño() {
        return año;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setAño(int año) {
        this.año = año;
    }
    
    
}
