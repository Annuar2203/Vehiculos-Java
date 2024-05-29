public abstract class Vehiculo {
    public String marca;
    public String modelo;
    public String tipo;
    public int año;

    public Vehiculo(String marca, String modelo, String tipo, int año) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.año = año;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public String getModelo(){
        return modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAño() {
        return año;
    }
    
    public abstract double getPrecioVenta(); //hereda el precio de venta de un vehiculo
    public abstract double getDescuento(); //hereda el descuento de algun tipo de vehiculo
    public abstract double getPrecioAlquilerPorDia();  //hereda el precio de alquiler de un tipo de vehiculo
}
