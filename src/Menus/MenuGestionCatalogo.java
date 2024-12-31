package Menus;

import Concesionario.*;
import EntradaSalida.MyInput;

public class MenuGestionCatalogo extends Menu {
    private  Catalogo catalogo;

    public MenuGestionCatalogo(Catalogo catalogo) {
        super(null);
        this.catalogo = catalogo;
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("GESTIÓN DEL CATÁLOGO");
        System.out.println("Seleccione una opción");
        System.out.println("0. Volver al menú principal");
        System.out.println("1. Dar de alta una sección");
        System.out.println("2. Dar de baja una sección");
        System.out.println("3. Mostrar las secciones");
        System.out.println("4. Dar de alta un coche");
        System.out.println("5. Mostrar detalles de un coche");
        System.out.println("6. Aumentar el stock de un coche");
        System.out.println("7. Mostrar todos los coches de una sección");
    }

    @Override
    public void ejecutarOpciones() {
        String respuesta = "";
        do {
            mostrarOpciones();
            try {
                int option = MyInput.readInt();
                switch (option) {
                    case 0:
                        System.out.println("Volviendo al menú principal");
                        return;
                    case 1:
                        catalogo.alta(nuevaSeccion());
                        break;
                    case 2:
                        catalogo.baja(catalogo.buscar(setIdSeccion()));
                        break;
                    case 3:
                        catalogo.mostrarSecciones();
                        break;
                    case 4:
                        catalogo.altaCoche(setIdSeccion(), setIdCoche(), setModelo(), setAnioFabricacion(), setPrecioBase(), 1);
                        break;
                    case 5:
                        catalogo.mostrarDetallesCoche(setIdSeccion(), setIdCoche());
                        break;
                    case 6:
                        catalogo.aumentarStock(setIdSeccion(), setIdCoche(), setCantidad());
                        break;
                    case 7:
                        catalogo.mostrarCochesSeccion(setIdSeccion());
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
                if (option != 0) {
                    System.out.println("¿Desea realizar otra operación? (s/n)");
                    respuesta = MyInput.readString().toLowerCase();
                }
            } catch (Exception e) {
                System.err.println("Error al ejecutar la opción: " + e.getMessage());
            }
        } while (respuesta.equals("s"));
    }

    public Seccion nuevaSeccion() {
        try {
            System.out.println("Introduce el nombre de la sección");
            String nombre = MyInput.readString();
            System.out.println("Introduce la descripción de la sección");
            String descripcion = MyInput.readString();
            return new Seccion(nombre, descripcion);
        } catch (Exception e) {
            System.err.println("Error al crear la nueva sección: " + e.getMessage());
            return null;
        }
    }

    public String setIdSeccion() {
        System.out.println("Introduzca el nombre de la seccion");
        return MyInput.readString();
    }

    public String setIdCoche() {
        System.out.println("Introduzca el ID del coche");
        return MyInput.readString();
    }

    public String setModelo() {
        System.out.println("Introduzca el modelo del coche");
        return MyInput.readString();
    }

    public int setAnioFabricacion() {
        System.out.println("Introduzca el año de fabricación del coche");
        return MyInput.readInt();
    }

    public double setPrecioBase() {
        System.out.println("Introduzca el precio base del coche");
        return MyInput.readDouble();
    }

    public int setCantidad() {
        System.out.println("Introduzca la cantidad de stock");
        return MyInput.readInt();
    }
}
