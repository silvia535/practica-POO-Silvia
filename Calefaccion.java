package Concesionario;

public class Calefaccion extends DecoratorMejora{
    public Calefaccion(Coche coche) {
        super(coche.getId(), coche.getPrecio(), coche.getStock(), 0.05, "Calefaccion");
    }
}
