package Menus;

import Concesionario.*;
import EntradaSalida.MyInput;

public class MenuGestionClientes extends Menu {
    private final GestionClientes clientes;

    public MenuGestionClientes(GestionClientes clientes) {
        super(null);
        this.clientes = clientes;
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("GESTIÓN DE CLIENTES");
        System.out.println("Seleccione una opción");
        System.out.println("0. Volver al menú principal");
        System.out.println("1. Dar de alta a un cliente");
        System.out.println("2. Mostrar información completa de un cliente");
        System.out.println("3. Mostrar clientes registrados");
        System.out.println("4. Mostrar clientes que reciben publicidad");
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
                        clientes.alta(nuevoCliente());
                        break;
                    case 2:
                        clientes.mostrarCliente(getDNI());
                        break;
                    case 3:
                        clientes.mostrarClientes();
                        break;
                    case 4:
                        clientes.mostrarClienteInfo();
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

    public Cliente nuevoCliente() {
        try {
            System.out.println("Introduce el DNI/NIF del cliente");
            String dni = MyInput.readString();
            System.out.println("Introduce el nombre completo del cliente");
            String nombre = MyInput.readString();
            System.out.println("Introduce el teléfono del cliente");
            String telefono = MyInput.readString();
            System.out.print("¿Desea recibir publicidad? (true/false): ");
            boolean publicidad = Boolean.parseBoolean(MyInput.readString());
            return new Cliente(dni, nombre, telefono, publicidad);
        } catch (Exception e) {
            System.err.println("Error al crear el nuevo cliente: " + e.getMessage());
            return null;
        }
    }

    public String getDNI() {
        System.out.println("Introduce el DNI/NIF del cliente");
        return MyInput.readString();
    }
}
