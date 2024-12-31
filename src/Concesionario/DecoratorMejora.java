package Concesionario;

public abstract class DecoratorMejora extends Coche {

    private final Coche coche;
    private final double incremento;
    private final String nombre;

    public DecoratorMejora(Coche coche, double incremento, String nombre) {
        super(coche.getId(), coche.getPrecio(), coche.getStock(), coche.getModelo(), coche.getAnioFabricacion());
        this.coche = coche;
        this.incremento = incremento;
        this.nombre = nombre;
    }

    @Override
    public double getPrecio() {
        try {
            return coche.getPrecio() * (1 + incremento);
        } catch (Exception e) {
            System.err.println("Error al obtener el precio: " + e.getMessage());
        }
        return 0.0;
    }

    public Coche getCoche() {
        return coche;
    }

    public double getIncremento() {
        return this.incremento;
    }

    public String getNombre() {
        return this.nombre;
    }
}
