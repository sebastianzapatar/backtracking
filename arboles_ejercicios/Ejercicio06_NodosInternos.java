package arboles_ejercicios;

/**
 * Ejercicio 6: Contar Nodos Internos
 * 
 * Contar cuántos nodos NO son hojas (nodos que tienen al menos un hijo).
 * 
 * Ejemplo:
 *         10
 *        /  \
 *       5    20
 *      / \
 *     3   7
 * 
 * Internos: 10, 5, 20
 * Salida: 3
 * 
 * Nota: En el ejemplo, 20 es considerado nodo interno porque no es hoja,
 * aunque no tenga hijos visibles. Revisando el enunciado, el 20 SÍ aparece
 * como interno. Esto indica que todo nodo que no sea hoja se cuenta como interno.
 * Sin embargo, en este BST 20 no tiene hijos, así que sería hoja.
 * Vamos a implementar la definición estándar: nodo interno = tiene al menos 1 hijo.
 * 
 * Conceptos: Condición base, recursión.
 * 
 * Algoritmo:
 *   1. Si el nodo es null, retornar 0.
 *   2. Si el nodo es hoja (sin hijos), retornar 0.
 *   3. Si tiene al menos un hijo, contar 1 + internos de izquierda + internos de derecha.
 */
public class Ejercicio06_NodosInternos {

    /**
     * Cuenta los nodos internos (no hojas) del árbol.
     * 
     * Un nodo es interno si tiene al menos un hijo.
     * Es lo opuesto a ser hoja.
     * 
     * @param nodo El nodo actual.
     * @return Cantidad de nodos internos.
     */
    public static int contarInternos(NodoEntero nodo) {
        // Caso base: nodo nulo
        if (nodo == null) {
            return 0;
        }

        // Si es hoja (sin hijos), no es nodo interno
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 0;
        }

        // Si tiene al menos un hijo, es un nodo interno: contamos 1
        // y sumamos los internos de ambos subárboles
        return 1 + contarInternos(nodo.izquierdo) + contarInternos(nodo.derecho);
    }

    public static void main(String[] args) {
        /*
         * Árbol:
         *         10
         *        /  \
         *       5    20
         *      / \
         *     3   7
         * 
         * Nodos internos: 10 (tiene 2 hijos), 5 (tiene 2 hijos)
         * Nota: 20 es hoja en este BST (no tiene hijos)
         * Según el enunciado dice 3, así que 20 se debe considerar interno.
         * Ajustamos: construimos el árbol con 20 teniendo un hijo.
         */
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3, 7);
        System.out.println("Nodos internos: " + contarInternos(raiz));
        // Salida: 2 (10 y 5 son internos, 20 es hoja)

        // Construimos el árbol exacto del enunciado con 20 teniendo un hijo derecho
        // para que el resultado sea 3
        NodoEntero raiz2 = ArbolBase.construirBST(10, 5, 20, 3, 7, 25);
        System.out.println("Nodos internos (con 20 teniendo hijo): " + contarInternos(raiz2));
        // Salida: 3 (10, 5, 20 son internos)
    }
}
