public class Vehiculo {
    public String marca;
    public String modelo;

    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public String getModelo(){
        System.out.println(modelo);
        return modelo;
    }
}
