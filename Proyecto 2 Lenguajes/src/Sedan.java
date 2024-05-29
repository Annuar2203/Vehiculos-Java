public class Sedan extends Vehiculo{
    private static final double descuento = 0.15; // 10%
    private static final double alquilerDia = 50.0; // Precio base por día
    private static final double precioVenta = 5000.0; // Precio base por día
    
    public Sedan(String marca, String modelo, String tipo, int año) {
        super(marca, modelo, tipo, año);
    }

    @Override
    public String getMarca() {
        return marca;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public String getTipo() {
        return tipo;
    }
    
    @Override
    public double getDescuento() {
        return descuento;
    }

    @Override
    public double getPrecioAlquilerPorDia() {
        return alquilerDia;
    }
    
    @Override
    public int getAño() {
        return año;
    }
    
    @Override
    public double getPrecioVenta() {
        return precioVenta;
    }      
}
