package arboles_ejercicios;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 15: Camino desde Raíz hasta Nodo
 * 
 * Mostrar el camino desde la raíz hasta un nodo dado.
 * 
 * Ejemplo:
 *         10
 *        /  \
 *       5    20
 *      / \
 *     3   7
 * 
 * Buscar 7 → Salida: 10 → 5 → 7
 */
public class Ejercicio15_CaminoRaizNodo {

    /**
     * Encuentra el camino desde la raíz hasta un valor dado.
     * 
     * Estrategia: Usamos backtracking.
     *   1. Añadimos el nodo actual al camino.
     *   2. Si es el nodo buscado, retornamos true.
     *   3. Si lo encontramos en algún subárbol, retornamos true.
     *   4. Si no está en ninguno, removemos el nodo del camino (backtrack).
     */
    public static boolean encontrarCamino(NodoEntero nodo, int valorBuscado, List<Integer> camino) {
        if (nodo == null) return false;

        // Añadimos el nodo actual al camino
        camino.add(nodo.valor);

        // Si encontramos el valor, el camino está completo
        if (nodo.valor == valorBuscado) return true;

        // Buscamos en izquierda o derecha
        if (encontrarCamino(nodo.izquierdo, valorBuscado, camino) ||
            encontrarCamino(nodo.derecho, valorBuscado, camino)) {
            return true;
        }

        // Backtracking: si no está en esta rama, removemos el nodo
        camino.remove(camino.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3, 7);

        // Buscar camino hasta 7
        List<Integer> camino = new ArrayList<>();
        if (encontrarCamino(raiz, 7, camino)) {
            System.out.println("Camino hasta 7: " + String.join(" → ",
                    camino.stream().map(String::valueOf).toArray(String[]::new)));
        }
        // Salida: 10 → 5 → 7

        // Buscar camino hasta 20
        List<Integer> camino2 = new ArrayList<>();
        if (encontrarCamino(raiz, 20, camino2)) {
            System.out.println("Camino hasta 20: " + String.join(" → ",
                    camino2.stream().map(String::valueOf).toArray(String[]::new)));
        }
        // Salida: 10 → 20
    }
}
