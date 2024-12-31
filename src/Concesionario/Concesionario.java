package Concesionario;

import java.util.ArrayList;
import java.io.Serializable;

public class Concesionario implements Serializable {
    private ArrayList<Gestionable> listaGestionables;

    public Concesionario() {
        listaGestionables = new ArrayList<Gestionable>();
        listaGestionables.add(new Catalogo());
        listaGestionables.add(new GestionVentas());
        listaGestionables.add(new GestionClientes());
    }

    protected void agregar(Gestionable g) {
        listaGestionables.add(g);
    }

    public Gestionable<?, ?, ?> recuperarGestionable(int i) {
        try {
            return listaGestionables.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fuera de rango: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al recuperar gestionable: " + e.getMessage());
        }
        return null;
    }
}
