package arboles_ejercicios;

/**
 * Ejercicio 7: Encontrar el Mayor Valor
 * 
 * Ejemplo:
 *         10
 *        /  \
 *       5    40
 *      / \
 *     3   7
 * 
 * Salida: 40
 */
public class Ejercicio07_MayorValor {

    /**
     * Encuentra el valor máximo en el árbol recorriendo todos los nodos.
     */
    public static int encontrarMayor(NodoEntero nodo) {
        if (nodo == null) {
            return Integer.MIN_VALUE;
        }
        int valorActual = nodo.valor;
        int mayorIzq = encontrarMayor(nodo.izquierdo);
        int mayorDer = encontrarMayor(nodo.derecho);
        return Math.max(valorActual, Math.max(mayorIzq, mayorDer));
    }

    public static void main(String[] args) {
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 40, 3, 7);
        System.out.println("Mayor valor: " + encontrarMayor(raiz));
        // Salida: 40
    }
}
