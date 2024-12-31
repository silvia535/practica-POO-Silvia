package Concesionario;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

public class Catalogo implements Gestionable<Seccion, String, Integer>, Serializable {
    private ArrayList<Seccion> listaSecciones;
    private ArrayList<Componente> listaComponentes;

    public Catalogo() {
        listaSecciones = new ArrayList<Seccion>();
    }

    @Override
    public void alta(Seccion a) {
        for (Seccion s : listaSecciones) {
            if (s.getId().equals(a.getId())) {
                System.out.println("La sección " + a.getId() + " ya está registrada");
                return;
            }
        }
        listaSecciones.add(a);
    }

    public void baja(Seccion a) {
        if (a == null) {
            System.out.println("Error: La sección proporcionada no existe.");
            return;
        }

        Iterator<Seccion> iterator = listaSecciones.iterator();

        while (iterator.hasNext()) {
            Seccion s = iterator.next();

            if (s.getId().equals(a.getId())) {

                boolean tieneCoches = false;
                listaComponentes = s.getListaComponentes();
                for (Componente componente : listaComponentes) {
                    if (componente instanceof Coche) {
                        tieneCoches = true;
                        break;
                    }
                }

                if (tieneCoches) {
                    System.out.println("No se puede eliminar la sección con ID " + a.getId() + " porque tiene coches asociados.");
                } else {
                    iterator.remove();
                    System.out.println("Sección con ID " + a.getId() + " ha sido eliminada.");
                }
                return;
            }
        }
    }
    public Seccion buscar(String id) {
        for (Seccion s : listaSecciones) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Seccion recuperar(Integer indice) {
        try {
            if (indice < listaSecciones.size()) {
                return listaSecciones.get(indice);
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fuera de rango: " + e.getMessage());
        }
        return null;
    }

    public int numeroElementos() {
        return listaSecciones.size();
    }


    public void mostrarSecciones() {
        for (Seccion s : listaSecciones) {
            s.mostrarDatos();
        }
    }

    public void altaCoche(String idSeccion, String idCoche, String modelo, int anioFabricacion, double precioBase, int stock) {
        Seccion seccion = buscar(idSeccion);
        if (seccion == null) {
            System.out.println("La sección con ID " + idSeccion + " no existe en el catálogo.");
        } else {
            seccion.altaCoche(idCoche, modelo, anioFabricacion, precioBase, stock);
        }
    }

    public void mostrarDetallesCoche(String idSeccion, String idCoche) {
        Seccion seccion = buscar(idSeccion);
        if (seccion != null) {
            seccion.mostrarDetallesCoche(idSeccion, idCoche);
        } else {
            System.out.println("Sección con ID " + idSeccion + " no encontrada.");
        }
    }

    public void aumentarStock(String idSeccion, String idCoche, int cantidad) {
        Seccion seccion = buscar(idSeccion);
        if (seccion != null) {
            seccion.aumentarStock(idCoche, cantidad);
        } else {
            System.out.println("La sección con ID " + idSeccion + " no existe en el catálogo.");
        }
    }

    public void mostrarCochesSeccion(String idSeccion) {
        Seccion seccion = buscar(idSeccion);
        if (seccion != null) {
            seccion.mostrarCochesSeccion();
        } else {
            System.out.println("La sección con ID " + idSeccion + " no existe.");
        }
    }
}
