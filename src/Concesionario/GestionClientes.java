package Concesionario;

import java.util.ArrayList;
import java.io.Serializable;

public class GestionClientes implements Gestionable<Cliente, String, Integer>, Serializable {

    private ArrayList<Cliente> listaClientes;

    public GestionClientes() {
        listaClientes = new ArrayList<Cliente>();
    }

    @Override
    public void alta(Cliente a) {
        listaClientes.add(a);
    }

    @Override
    public Cliente buscar(String id) {
        for (Cliente c : listaClientes) {
            if (c.getID().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Cliente recuperar(Integer indice) {
        try {
            if (indice < listaClientes.size()) {
                return listaClientes.get(indice);
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fuera de rango: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int numeroElementos() {
        return listaClientes.size();
    }

    public void mostrarClientes() {
        for (Cliente c : listaClientes) {
            System.out.println("DNI: " + c.getID());
            System.out.println("Nombre: " + c.getNombre());
        }
    }

    public void mostrarCliente(String id) {
        Cliente c = buscar(id);
        if (c != null) {
            System.out.println("DNI: " + c.getID());
            System.out.println("Nombre: " + c.getNombre());
            System.out.println("Teléfono: " + c.getTelefono());
            if (c.getInfo()) {
                System.out.println("El cliente recibe publicidad");
            } else {
                System.out.println("El cliente no recibe publicidad");
            }
        } else {
            System.out.println("Cliente con ID " + id + " no encontrado.");
        }
    }

    public void mostrarClienteInfo() {
        for (Cliente c : listaClientes) {
            if (c.getInfo()) {
                System.out.println("Nombre: " + c.getNombre());
                System.out.println("Teléfono: " + c.getTelefono());
            }
        }
    }
}
