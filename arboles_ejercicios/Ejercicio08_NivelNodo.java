package arboles_ejercicios;

/**
 * Ejercicio 8: Nivel de un Nodo
 * 
 * Dado un valor, indicar en qué nivel se encuentra (raíz = nivel 1).
 * 
 * Ejemplo:
 *         10       ← nivel 1
 *        /  \
 *       5    20    ← nivel 2
 *      /
 *     3            ← nivel 3
 * 
 * Buscar 3 → Salida: 3
 */
public class Ejercicio08_NivelNodo {

    /**
     * Encuentra el nivel de un valor en el árbol.
     * 
     * @param nodo El nodo actual.
     * @param valorBuscado El valor a buscar.
     * @param nivelActual El nivel del nodo actual.
     * @return El nivel donde se encuentra el valor, o -1 si no existe.
     */
    public static int encontrarNivel(NodoEntero nodo, int valorBuscado, int nivelActual) {
        // Caso base: nodo nulo, el valor no está en esta rama
        if (nodo == null) {
            return -1;
        }

        // Si encontramos el valor, retornamos el nivel actual
        if (nodo.valor == valorBuscado) {
            return nivelActual;
        }

        // Buscamos en el subárbol izquierdo
        int nivelIzq = encontrarNivel(nodo.izquierdo, valorBuscado, nivelActual + 1);
        if (nivelIzq != -1) {
            return nivelIzq; // Lo encontramos a la izquierda
        }

        // Si no está a la izquierda, buscamos a la derecha
        return encontrarNivel(nodo.derecho, valorBuscado, nivelActual + 1);
    }

    public static void main(String[] args) {
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3);

        System.out.println("Nivel de 3: " + encontrarNivel(raiz, 3, 1));   // 3
        System.out.println("Nivel de 10: " + encontrarNivel(raiz, 10, 1)); // 1
        System.out.println("Nivel de 20: " + encontrarNivel(raiz, 20, 1)); // 2
        System.out.println("Nivel de 99: " + encontrarNivel(raiz, 99, 1)); // -1
    }
}
