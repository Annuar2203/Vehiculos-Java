public class Deportivo extends Vehiculo{
    
    public Deportivo(String marca, String modelo) {
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
