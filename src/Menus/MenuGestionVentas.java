package Menus;

import Concesionario.*;
import EntradaSalida.MyInput;
import java.time.LocalDate;

public class MenuGestionVentas extends Menu {
    private final GestionVentas ventas;
    private final Catalogo catalogo;
    private final GestionClientes gestionClientes;

    public MenuGestionVentas(GestionVentas ventas, Catalogo catalogo, GestionClientes gestionClientes) {
        super(null);
        this.ventas = ventas;
        this.catalogo = catalogo;
        this.gestionClientes = gestionClientes;
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("GESTIÓN DE VENTAS");
        System.out.println("Seleccione una opción");
        System.out.println("0. Volver al menú principal");
        System.out.println("1. Registrar una venta");
        System.out.println("2. Mostrar información de una venta");
        System.out.println("3. Mostrar ventas de un cliente");
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
                        ventas.alta(nuevaVenta());
                        break;
                    case 2:
                        ventas.mostrarVenta(getIDVenta());
                        break;
                    case 3:
                        ventas.mostrarVentasCliente(getDNI());
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

    public VentaCoche nuevaVenta() {
        try {
            System.out.println("Introduzca el ID de la venta");
            String idVenta = MyInput.readString();
            LocalDate fechaVenta = LocalDate.now();
            return new VentaCoche(idVenta, fechaVenta, catalogo, gestionClientes, getPrecioBaseCoche());
        } catch (Exception e) {
            System.err.println("Error al crear una nueva venta: " + e.getMessage());
            return null;
        }
    }

    public String getIDVenta() {
        System.out.println("Introduzca el ID de la venta");
        return MyInput.readString();
    }

    public String getDNI() {
        System.out.println("Introduzca el DNI del cliente");
        return MyInput.readString();
    }

    public double getPrecioBaseCoche() {
        try {
            System.out.println("Introduzca el precio base del coche");
            return MyInput.readDouble();
        } catch (Exception e) {
            System.err.println("Error al establecer el precio base del coche: " + e.getMessage());
            return 0.0;
        }
    }
}
