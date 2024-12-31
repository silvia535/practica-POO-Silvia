package Concesionario;
public class GPS extends DecoratorMejora {
    public GPS(Coche coche) {
        super(coche, 0.01, "GPS");
    }
}