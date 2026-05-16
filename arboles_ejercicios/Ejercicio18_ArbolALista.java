package arboles_ejercicios;

/**
 * Ejercicio 18: Convertir Árbol a Lista Enlazada (Inorden)
 * 
 * Convertir el árbol en una lista enlazada usando recorrido inorden.
 * Cada nodo del árbol se convierte en un nodo de la lista,
 * usando el puntero 'derecho' como 'siguiente'.
 * 
 * Ejemplo:
 *         10
 *        /  \
 *       5    20
 *      / \
 *     3   7
 * 
 * Lista inorden: 3 → 5 → 7 → 10 → 20
 */
public class Ejercicio18_ArbolALista {

    // Nodo auxiliar que actúa como predecesor durante la conversión
    static NodoEntero anterior = null;
    // Referencia a la cabeza de la lista resultante
    static NodoEntero cabezaLista = null;

    /**
     * Convierte el árbol a una lista enlazada usando recorrido inorden.
     * 
     * Estrategia: Hacemos un recorrido inorden. En cada nodo:
     *   1. Si es el primero (anterior == null), es la cabeza de la lista.
     *   2. Si no, conectamos anterior.derecho → nodo actual.
     *   3. Ponemos nodo.izquierdo = null (ya no hay rama izquierda en una lista).
     *   4. Actualizamos 'anterior' al nodo actual.
     */
    public static NodoEntero convertirALista(NodoEntero raiz) {
        anterior = null;
        cabezaLista = null;
        convertirInorden(raiz);
        return cabezaLista;
    }

    private static void convertirInorden(NodoEntero nodo) {
        if (nodo == null) return;

        // 1. Recorremos el subárbol izquierdo
        convertirInorden(nodo.izquierdo);

        // 2. Procesamos el nodo actual
        if (anterior == null) {
            // Es el primer nodo (el menor), será la cabeza de la lista
            cabezaLista = nodo;
        } else {
            // Conectamos el nodo anterior con el actual
            anterior.derecho = nodo;
        }
        // Limpiamos el hijo izquierdo (ya no lo necesitamos en la lista)
        nodo.izquierdo = null;
        // Actualizamos el puntero 'anterior'
        anterior = nodo;

        // 3. Recorremos el subárbol derecho
        convertirInorden(nodo.derecho);
    }

    /** Imprime la lista enlazada resultante */
    private static void imprimirLista(NodoEntero cabeza) {
        NodoEntero actual = cabeza;
        while (actual != null) {
            System.out.print(actual.valor);
            if (actual.derecho != null) System.out.print(" → ");
            actual = actual.derecho;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3, 7);

        System.out.println("Árbol convertido a lista enlazada (inorden):");
        NodoEntero lista = convertirALista(raiz);
        imprimirLista(lista);
        // Salida: 3 → 5 → 7 → 10 → 20
    }
}
