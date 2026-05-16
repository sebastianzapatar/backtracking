package arboles_ejercicios;

/**
 * Ejercicio 4: Buscar un Valor
 * 
 * Determinar si un valor existe dentro del árbol.
 * 
 * Ejemplo:
 *   Buscar 15 → true
 *   Buscar 99 → false
 * 
 * Conceptos: DFS recursivo (Depth-First Search).
 * 
 * Algoritmo:
 *   1. Si el nodo es null, el valor no existe → false.
 *   2. Si el nodo actual tiene el valor buscado → true.
 *   3. Buscar recursivamente en el subárbol izquierdo O derecho.
 *      (Si lo encontramos en cualquiera de los dos, retornamos true)
 */
public class Ejercicio04_BuscarValor {

    /**
     * Busca un valor en el árbol usando DFS recursivo.
     * 
     * Nota: Esta versión funciona para CUALQUIER árbol binario (no necesita ser BST).
     * Si fuera un BST, podríamos optimizar la búsqueda comparando para ir
     * solo a izquierda o derecha.
     * 
     * @param nodo El nodo actual.
     * @param valorBuscado El valor que estamos buscando.
     * @return true si el valor existe en el árbol, false en caso contrario.
     */
    public static boolean buscar(NodoEntero nodo, int valorBuscado) {
        // Caso base: si el nodo es null, el valor no está en esta rama
        if (nodo == null) {
            return false;
        }

        // Si encontramos el valor en el nodo actual, ¡éxito!
        if (nodo.valor == valorBuscado) {
            return true;
        }

        // Buscamos en el subárbol izquierdo; si lo encontramos, retornamos true
        // Si no, buscamos en el subárbol derecho
        return buscar(nodo.izquierdo, valorBuscado) || buscar(nodo.derecho, valorBuscado);
    }

    public static void main(String[] args) {
        /*
         * Árbol:
         *           10
         *         /    \
         *        5      20
         *       / \    /  \
         *      3   7  15   30
         */
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3, 7, 15, 30);

        System.out.println("Buscar 15: " + buscar(raiz, 15));
        // Salida esperada: true

        System.out.println("Buscar 99: " + buscar(raiz, 99));
        // Salida esperada: false

        System.out.println("Buscar 10: " + buscar(raiz, 10));
        // Salida esperada: true (la raíz)

        System.out.println("Buscar 3: " + buscar(raiz, 3));
        // Salida esperada: true (una hoja)
    }
}
