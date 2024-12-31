package Concesionario;

import java.io.Serializable;

public abstract class Componente implements IdentificableString, Serializable {
    private String id;

    public Componente(String s){
        this.id = s;
    }

    public String getId(){
        return id;
    }

    public  abstract void agregar(Componente c);
    public  abstract void eliminar (Componente c);
    public  abstract Componente recuperarComponente(String id);
}
