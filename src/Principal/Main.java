package Principal;


import Concesionario.*;
import EntradaSalida.MyInput;
import Menus.*;

public class Main {
    public static void main(String[] args) {
        Concesionario c = MyInput.deserialize("fichero.dat");
        if(c == null) {
            c = new Concesionario();
        }
        MenuPrincipal menuPrincipal = new MenuPrincipal(c);
        menuPrincipal.ejecutarOpciones();
        MyInput.serialize(c, "fichero.dat");
    }
}