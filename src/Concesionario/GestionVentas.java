package Concesionario;

import java.util.ArrayList;
import java.io.Serializable;

public class GestionVentas implements Gestionable<VentaCoche, String, Integer>, Serializable {

    private ArrayList<VentaCoche> listaVentas;

    public GestionVentas() {
        listaVentas = new ArrayList<>();
    }

    @Override
    public void alta(VentaCoche a) {
        listaVentas.add(a);
    }

    @Override
    public VentaCoche buscar(String id) {
        for (VentaCoche c : listaVentas) {
            if (c.getID().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public VentaCoche recuperar(Integer indice) {
        try {
            if (indice < listaVentas.size()) {
                return listaVentas.get(indice);
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fuera de rango: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int numeroElementos() {
        return listaVentas.size();
    }

    public void mostrarVenta(String idVenta) {
        try {
            VentaCoche venta = buscar(idVenta);
            if (venta == null) {
                System.out.println("No se encontró la venta con ID: " + idVenta);
                return;
            }

            System.out.println("Información de la venta:");
            System.out.println("Cliente: " + venta.getCliente().getNombre() + " (DNI/NIF: " + venta.getCliente().getId() + ")");
            System.out.println("Fecha de la venta: " + venta.getFecha());
            System.out.println("Matrícula: " + venta.getMatricula());
            System.out.println("Modelo: " + venta.getCocheDecorado().getModelo());
            System.out.println("Fecha de fabricación: " + venta.getCocheDecorado().getAnioFabricacion());

            System.out.println("Mejoras aplicadas:");
            Coche cocheDecorado = venta.getCocheDecorado();
            while (cocheDecorado instanceof DecoratorMejora) {
                DecoratorMejora mejora = (DecoratorMejora) cocheDecorado;
                System.out.println("- " + mejora.getNombre() + ": +" + (mejora.getIncremento() * 100) + "%");
                cocheDecorado = mejora.getCoche();
            }
            venta.mostrarPrecios();
        } catch (Exception e) {
            System.err.println("Error al mostrar la venta: " + e.getMessage());
        }
    }

    public void mostrarVentasCliente(String idCliente) {
        try {
            boolean hayVentas = false;

            for (VentaCoche venta : listaVentas) {
                if (venta.getCliente().getId().equals(idCliente)) {
                    hayVentas = true;
                    System.out.println("ID Venta: " + venta.getID());
                    System.out.println("Fecha: " + venta.getFecha());
                    venta.mostrarPrecios();
                }
            }

            if (!hayVentas) {
                System.out.println("No se encontraron ventas para el cliente con ID: " + idCliente);
            }
        } catch (Exception e) {
            System.err.println("Error al mostrar las ventas del cliente: " + e.getMessage());
        }
    }
}
