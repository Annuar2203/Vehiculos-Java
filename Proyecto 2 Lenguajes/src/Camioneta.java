public class Camioneta extends Vehiculo{
    
    public Camioneta(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public String getMarca() {
        return marca;
    }

    @Override
    public String getModelo() {
        return modelo;
    }
    
}
