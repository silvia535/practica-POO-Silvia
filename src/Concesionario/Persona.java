package Concesionario;

import java.io.Serializable;

public abstract class Persona implements IdentificableString, Serializable {
    private String id;
    private String nombre;
    private String telefono;

    public Persona(String id, String nombre, String telefono) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser nulo o vacío");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo o vacío");
        }
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
}
