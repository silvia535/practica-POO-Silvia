package Concesionario;

public interface Gestionable <T,K,V> {
    void alta(T a);
    T buscar(K id);
    T recuperar(V indice);
    int numeroElementos();
}
