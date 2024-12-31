package Concesionario;



public class Cliente extends Persona {

    private boolean info;

    public Cliente(String id, String nombre, String telefono, boolean info) {
        super(id, nombre, telefono);
        this.info = info;
    }

    public boolean getInfo() {
        return info;
    }

    @Override
    public String getID() {
        try {
            return getId();
        } catch (Exception e) {
            System.err.println("Error al obtener el ID: " + e.getMessage());
        }
        return null;
    }

}
