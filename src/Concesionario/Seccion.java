package Concesionario;

import java.util.ArrayList;

public class Seccion extends Componente {
    private String descripcion;
    private ArrayList<Componente> listaComponentes;


    public Seccion(String id, String descripcion) {
        super(id);
        this.descripcion = descripcion;
        listaComponentes = new ArrayList<>();
    }

    public void mostrarDatos() {
        try {
            System.out.println("Seccion: " + getId() + "\nDescripción: " + descripcion);
        } catch (Exception e) {
            System.err.println("Error al mostrar los datos de la sección: " + e.getMessage());
        }
    }

    @Override
    public void agregar(Componente c) {
        listaComponentes.add(c);
    }

    @Override
    public void eliminar(Componente c) {
        if (listaComponentes.contains(c)) {
            listaComponentes.remove(c);
        } else {
            for (Componente c1 : listaComponentes) {
                c1.eliminar(c);
            }
        }
    }

    @Override
    public Componente recuperarComponente(String id) {
        if (this.getId().equals(id)) return this;
        else {
            for (Componente c : listaComponentes)
                if (c.recuperarComponente(id) != null)
                    if (c.recuperarComponente(id).getId().equals(id))
                        return c.recuperarComponente(id);
            return null;
        }
    }

    @Override
    public String getID() {
        return "";
    }

    public void altaCoche(String idCoche, String modelo, int anioFabricacion, double precioBase, int stock) {
        for (Componente componente : listaComponentes) {
            if (componente instanceof Coche && componente.getId().equals(idCoche)) {
                System.out.println("El coche con ID " + idCoche + " ya existe en esta sección.");
                return;
            }
        }
        // Crear y agregar el nuevo coche
        try {
            Coche nuevoCoche = new Coche(idCoche, precioBase, stock, modelo, anioFabricacion);
            this.agregar(nuevoCoche);
            System.out.println("El coche con ID " + idCoche + " ha sido dado de alta en esta sección.");
        } catch (Exception e) {
            System.err.println("Error al dar de alta el coche: " + e.getMessage());
        }
    }

    public void aumentarStock(String idCoche, int cantidad) {
        try {
            for (Componente componente : listaComponentes) {
                if (componente instanceof Coche && componente.getId().equals(idCoche)) {
                    ((Coche) componente).aumentarStock(cantidad);
                    return;
                }
                if (componente instanceof Seccion) {
                    ((Seccion) componente).aumentarStock(idCoche, cantidad);
                }
            }
            System.out.println("El coche con ID " + idCoche + " no fue encontrado en esta sección.");
        } catch (Exception e) {
            System.err.println("Error al aumentar el stock del coche: " + e.getMessage());
        }
    }

    public void mostrarCochesSeccion() {
        try {
            System.out.println("Sección: " + this.getId());
            for (Componente componente : listaComponentes) {
                if (componente instanceof Coche) {
                    Coche coche = (Coche) componente;
                    System.out.println("ID: " + coche.getId() +
                            ", Precio Base: " + coche.getPrecio() +
                            ", Stock: " + coche.getStock());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al mostrar los coches de la sección: " + e.getMessage());
        }
    }

    public void mostrarDetallesCoche(String idSeccion, String idCoche) {
        try {
            if (this.getId().equals(idSeccion)) {
                for (Componente componente : listaComponentes) {
                    if (componente instanceof Coche && componente.getId().equals(idCoche)) {
                        Coche coche = (Coche) componente;
                        coche.mostrarDatos();
                        return;
                    }
                }
                System.out.println("Coche con ID " + idCoche + " no encontrado en la sección " + idSeccion);
            } else {
                for (Componente componente : listaComponentes) {
                    if (componente instanceof Seccion) {
                        ((Seccion) componente).mostrarDetallesCoche(idSeccion, idCoche);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al mostrar los detalles del coche: " + e.getMessage());
        }
    }

    public ArrayList<Componente> getListaComponentes() {
        return listaComponentes;
    }
}
