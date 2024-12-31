package Menus;

import Concesionario.*;
import EntradaSalida.MyInput;

public class MenuPrincipal extends Menu {
    private String respuesta;

    public MenuPrincipal(Concesionario c) {
        super(c);
        respuesta = "s";
    }

    public void mostrarOpciones() {
        System.out.println("Menú Principal");
        System.out.println("Seleccione una opción");
        System.out.println("0. Guardar y salir del programa");
        System.out.println("1. Gestión del catálogo");
        System.out.println("2. Gestión de ventas");
        System.out.println("3. Gestión de clientes");
    }

    @Override
    public void ejecutarOpciones() {
        do {
            mostrarOpciones();
            try {
                int option = MyInput.readInt();
                switch (option) {
                    case 0:
                        System.out.println("Guardando y saliendo del programa");
                        return;
                    case 1:
                        Catalogo catalogo = (Catalogo) getConcesionario().recuperarGestionable(option);
                        new MenuGestionCatalogo(catalogo).ejecutarOpciones();
                        break;
                    case 2:
                        GestionVentas ventas = (GestionVentas) getConcesionario().recuperarGestionable(option);
                        Catalogo catalogoVentas = (Catalogo) getConcesionario().recuperarGestionable(1);
                        GestionClientes gestionClientes = (GestionClientes) getConcesionario().recuperarGestionable(3);
                        new MenuGestionVentas(ventas, catalogoVentas, gestionClientes).ejecutarOpciones();
                        break;
                    case 3:
                        GestionClientes clientes = (GestionClientes) getConcesionario().recuperarGestionable(option);
                        new MenuGestionClientes(clientes).ejecutarOpciones();
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
                if (option != 0) {
                    System.out.println("¿Desea realizar más operaciones? (s/n)");
                    respuesta = MyInput.readString().toLowerCase();
                }
            } catch (Exception e) {
                System.err.println("Error al ejecutar la opción: " + e.getMessage());
            }
        } while (respuesta.equals("s"));
    }
}
