package Concesionario;


public class Calefaccion extends DecoratorMejora {
    public Calefaccion(Coche coche) {
        super(coche, 0.05, "Calefaccion");
    }
}

