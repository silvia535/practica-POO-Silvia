package Concesionario;

import java.time.LocalDate;
import EntradaSalida.MyInput;

public class VentaCoche extends Venta {
    private Coche cocheDecorado;
    private final Cliente cliente;
    private String matricula;
    private double precioBase;
    private double precioBaseCoche;

    public VentaCoche(String id, LocalDate fecha, Catalogo catalogo, GestionClientes gestionClientes, double precioBaseCoche) {
        super(id, fecha);
        this.cliente = asignarClienteVenta(gestionClientes);
        this.cocheDecorado = asignarCocheVenta(catalogo);
        this.precioBaseCoche = precioBaseCoche;
    }

    public Coche asignarCocheVenta(Catalogo catalogo) {
        try {
            // Solicitar datos del coche
            System.out.print("Ingrese el ID de la sección: ");
            String idSeccion = MyInput.readString();
            System.out.print("Ingrese el ID del coche: ");
            String idCoche = MyInput.readString();

            // Buscar el coche en el catálogo
            Seccion seccion = catalogo.buscar(idSeccion);
            if (seccion == null) {
                System.out.println("La sección no existe. Proceso cancelado.");
                return null;
            }

            Coche coche = null;

            for (Componente componente : seccion.getListaComponentes()) {
                if (componente instanceof Coche && componente.getId().equals(idCoche)) {
                    coche = (Coche) componente;
                    break;
                }
            }

            if (coche == null || coche.getStock() <= 0) {
                System.out.println("El coche no existe o no tiene stock disponible. Proceso cancelado.");
                return null;
            }

            setMatricula();

            System.out.println("Seleccione las mejoras que desea aplicar al coche:");
            System.out.println("1. Tapicería de cuero (+10%)");
            System.out.println("2. GPS (+1%)");
            System.out.println("3. Calefacción en asientos y volante (+5%)");
            System.out.println("4. Llantas de aleación (+5%)");
            System.out.println("5. Tapicería de cuero y GPS: 11%");
            System.out.println("6. Tapicería de cuero y calefacción: 15%");
            System.out.println("7. Tapicería de cuero y llantas de aleación: 15%");
            System.out.println("8. GPS y calefacción: 6%");
            System.out.println("9. GPS y llantas de aleación: 6%");
            System.out.println("10. Calefacción y llantas de aleación: 10%");
            System.out.println("11. Tapicería de cuero, GPS y calefacción: 16%");
            System.out.println("12. Tapicería de cuero, GPS y llantas de aleación: 16%");
            System.out.println("13. Tapicería de cuero, calefacción y llantas de aleación: 20%");
            System.out.println("14. GPS, calefacción y llantas de aleación: 11%");
            System.out.println("15. Tapicería de cuero, GPS, calefacción y llantas de aleación: 21%");
            System.out.println("16. Ninguna mejora");
            System.out.print("Elige una opción: ");
            String opcion = MyInput.readString();

            switch (opcion) {
                case "1":
                    cocheDecorado = new TapiceriaCuero(coche);
                    break;
                case "2":
                    cocheDecorado = new GPS(coche);
                    break;
                case "3":
                    cocheDecorado = new Calefaccion(coche);
                    break;
                case "4":
                    cocheDecorado = new Llantas(coche);
                    break;
                case "5":
                    cocheDecorado = new TapiceriaCuero(new GPS(coche));
                    break;
                case "6":
                    cocheDecorado = new TapiceriaCuero(new Calefaccion(coche));
                    break;
                case "7":
                    cocheDecorado = new TapiceriaCuero(new Llantas(coche));
                    break;
                case "8":
                    cocheDecorado = new GPS(new Calefaccion(coche));
                    break;
                case "9":
                    cocheDecorado = new GPS(new Llantas(coche));
                    break;
                case "10":
                    cocheDecorado = new Calefaccion(new Llantas(coche));
                    break;
                case "11":
                    cocheDecorado = new TapiceriaCuero(new GPS(new Calefaccion(coche)));
                    break;
                case "12":
                    cocheDecorado = new TapiceriaCuero(new GPS(new Llantas(coche)));
                    break;
                case "13":
                    cocheDecorado = new TapiceriaCuero(new Calefaccion(new Llantas(coche)));
                    break;
                case "14":
                    cocheDecorado = new GPS(new Calefaccion(new Llantas(coche)));
                    break;
                case "15":
                    cocheDecorado = new TapiceriaCuero(new GPS(new Calefaccion(new Llantas(coche))));
                    break;
                case "16":
                    cocheDecorado = coche;
                    System.out.println("Mejoras no aplicadas");
                    break;
                default:
                    cocheDecorado = coche;
                    System.out.println("Opción no válida");
                    break;
            }

            // Reducir el stock
            coche.reducirStock();

            return cocheDecorado;
        } catch (Exception e) {
            System.err.println("Error al asignar coche: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String getID() {
        return getId();
    }

    public Coche getCocheDecorado() {
        return cocheDecorado;
    }

    public double getPrecioBase() {
        return precioBase = cocheDecorado.getPrecio();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula() {
        try {
            System.out.print("Ingrese la matrícula del coche: ");
            this.matricula = MyInput.readString();
        } catch (Exception e) {
            System.err.println("Error al establecer la matrícula: " + e.getMessage());
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Cliente asignarClienteVenta(GestionClientes gestionClientes) {
        try {
            System.out.print("Ingrese el DNI/NIF del cliente: ");
            String idCliente = MyInput.readString();
            Cliente cliente = gestionClientes.buscar(idCliente);

            if (cliente == null) {
                // Registrar nuevo cliente
                System.out.println("El cliente no existe. Registrando nuevo cliente...");
                System.out.print("Ingrese el nombre del cliente: ");
                String nombre = MyInput.readString();
                System.out.print("Ingrese el teléfono del cliente: ");
                String telefono = MyInput.readString();
                System.out.print("¿Desea recibir publicidad? (true/false): ");
                boolean publicidad = Boolean.parseBoolean(MyInput.readString());

                cliente = new Cliente(idCliente, nombre, telefono, publicidad);
                gestionClientes.alta(cliente);
            } else {
                System.out.println("El cliente ya existe");
            }

            return cliente;
        } catch (Exception e) {
            System.err.println("Error al asignar cliente: " + e.getMessage());
            return null;
        }
    }

    public void mostrarPrecios() {
        try {
            double precioBase = getPrecioBase();
            double precioFinal = precioBase * 1.21;
            System.out.println("Precio base del coche (sin mejoras y sin IVA): " + precioBaseCoche);
            System.out.println("Precio final del coche (con mejoras y con IVA): " + precioFinal);
        } catch (Exception e) {
            System.err.println("Error al mostrar los precios: " + e.getMessage());
        }
    }
}
