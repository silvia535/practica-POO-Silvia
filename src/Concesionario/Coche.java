package Concesionario;

public class Coche extends Componente {

    private String modelo;
    private int anioFabricacion;
    private final double precioBase;
    private int stock;

    public Coche(String id, double precioBase, int stock, String modelo, int anioFabricacion) {
        super(id);
        this.precioBase = precioBase;
        this.stock = stock;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
    }

    public void agregar(Componente c) {

    }

    public void eliminar(Componente c) {

    }

    public Componente recuperarComponente(String id) {
        return this;
    }

    @Override
    public String getID() {
        try {
            return getId();
        } catch (Exception e) {
            System.err.println("Error al obtener el ID: " + e.getMessage());
        }
        return "";
    }

    public double getPrecio() {
        return this.precioBase;
    }

    public int getStock() {
        return stock;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void mostrarDatos() {
        try {
            System.out.println("Los datos del coche con ID:  " + getId() + "  son: ");
            System.out.println("Modelo: " + modelo);
            System.out.println("Año de fabricación: " + anioFabricacion);
            System.out.println("Precio base: " + precioBase);
            System.out.println("Stock: " + stock);
        } catch (Exception e) {
            System.err.println("Error al mostrar los datos del coche: " + e.getMessage());
        }
    }

    public void aumentarStock(int cantidad) {
        this.stock += cantidad;
        System.out.println("El stock del coche con ID " + getId() + " ha sido incrementado en " + cantidad + " unidades. Nuevo stock: " + this.stock);
    }

    public void reducirStock() {
        try {
            this.stock--;
        } catch (Exception e) {
            System.err.println("Error al reducir el stock: " + e.getMessage());
        }
    }
}
