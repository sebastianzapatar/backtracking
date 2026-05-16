package arboles_ejercicios;

/**
 * Ejercicio 5: Suma de Todos los Nodos
 * 
 * Sumar todos los valores del árbol.
 * 
 * Ejemplo:
 *       10
 *      /  \
 *     5    20
 * 
 * Salida: 35
 * 
 * Conceptos: Recorrido completo, recursión.
 * 
 * Algoritmo:
 *   1. Si el nodo es null, retornar 0 (no suma nada).
 *   2. Retornar: valor del nodo + suma del subárbol izquierdo + suma del subárbol derecho.
 */
public class Ejercicio05_SumaNodos {

    /**
     * Suma todos los valores del árbol de forma recursiva.
     * 
     * La idea es sencilla: el valor de este nodo + todo lo que haya
     * a la izquierda + todo lo que haya a la derecha.
     * 
     * @param nodo El nodo actual.
     * @return La suma de todos los valores del subárbol.
     */
    public static int sumarNodos(NodoEntero nodo) {
        // Caso base: nodo nulo no aporta a la suma
        if (nodo == null) {
            return 0;
        }

        // Sumamos: valor actual + suma de izquierda + suma de derecha
        return nodo.valor + sumarNodos(nodo.izquierdo) + sumarNodos(nodo.derecho);
    }

    public static void main(String[] args) {
        /*
         * Árbol:
         *       10
         *      /  \
         *     5    20
         */
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20);

        int suma = sumarNodos(raiz);
        System.out.println("Suma de todos los nodos: " + suma);
        // Salida esperada: 35

        // Árbol más grande
        NodoEntero raiz2 = ArbolBase.construirBST(10, 5, 20, 3, 7, 15, 30);
        System.out.println("Suma del árbol grande: " + sumarNodos(raiz2));
        // Salida esperada: 10+5+20+3+7+15+30 = 90
    }
}
