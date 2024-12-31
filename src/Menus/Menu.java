package Menus;


import Concesionario.Concesionario;


public abstract class Menu {
    private Concesionario c;

    public Menu(Concesionario c) {
        this.c = c;
    }

    public abstract void mostrarOpciones();
    public abstract void ejecutarOpciones();

    public Concesionario getConcesionario() {
        return c;
    }
}
