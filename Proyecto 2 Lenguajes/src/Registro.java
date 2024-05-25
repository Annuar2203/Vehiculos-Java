
import java.awt.Color;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author annuar2203
 */
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
    
    public String getNombre(){
        return nombre;
    }
    
}
