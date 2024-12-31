package Concesionario;

import java.time.LocalDate;
import java.io.Serializable;

public abstract class Venta implements IdentificableString, Serializable {
    private String id;
    private LocalDate fecha;

    public Venta(String id, LocalDate fecha) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser nulo o vacío");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        this.id = id;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
