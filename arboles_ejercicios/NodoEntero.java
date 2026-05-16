package arboles_ejercicios;

/**
 * Nodo simple que almacena un valor entero.
 * Se usa un nodo con int directamente para simplificar los ejercicios
 * y enfocarnos en los algoritmos sobre árboles.
 */
public class NodoEntero {
    int valor;              // Valor almacenado en el nodo
    NodoEntero izquierdo;   // Referencia al hijo izquierdo
    NodoEntero derecho;     // Referencia al hijo derecho

    /**
     * Constructor del nodo.
     * @param valor El valor entero que almacenará este nodo.
     */
    public NodoEntero(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}
