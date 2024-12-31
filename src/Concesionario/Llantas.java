package Concesionario;

public class Llantas extends DecoratorMejora {
    public Llantas(Coche coche) {
        super(coche, 0.05, "Llanta");
    }
}


