package arboles_ejercicios;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ejercicio 13: Imprimir Nodos por Nivel (BFS - Level Order Traversal)
 * 
 * Mostrar el árbol nivel por nivel.
 * 
 * Ejemplo:
 *         10
 *        /  \
 *       5    20
 *      / \
 *     3   7
 * 
 * Salida:
 *   Nivel 1: 10
 *   Nivel 2: 5 20
 *   Nivel 3: 3 7
 * 
 * Conceptos: Queue (cola), BFS (Breadth-First Search).
 */
public class Ejercicio13_NodosPorNivel {

    /**
     * Imprime los nodos del árbol nivel por nivel usando BFS.
     * 
     * Usamos una cola (Queue) para procesar los nodos en orden de nivel.
     * En cada iteración, procesamos todos los nodos del nivel actual
     * y añadimos sus hijos a la cola para el siguiente nivel.
     */
    public static void imprimirPorNivel(NodoEntero raiz) {
        if (raiz == null) {
            System.out.println("Árbol vacío");
            return;
        }

        // Cola para almacenar los nodos pendientes de visitar
        Queue<NodoEntero> cola = new LinkedList<>();
        cola.add(raiz);
        int nivel = 1;

        while (!cola.isEmpty()) {
            // Cantidad de nodos en el nivel actual
            int nodosEnNivel = cola.size();

            System.out.print("Nivel " + nivel + ": ");

            // Procesamos TODOS los nodos del nivel actual
            for (int i = 0; i < nodosEnNivel; i++) {
                NodoEntero actual = cola.poll(); // Sacamos el primero de la cola
                System.out.print(actual.valor + " ");

                // Añadimos los hijos a la cola (serán procesados en el siguiente nivel)
                if (actual.izquierdo != null) cola.add(actual.izquierdo);
                if (actual.derecho != null) cola.add(actual.derecho);
            }

            System.out.println();
            nivel++;
        }
    }

    public static void main(String[] args) {
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3, 7);
        imprimirPorNivel(raiz);
        // Nivel 1: 10
        // Nivel 2: 5 20
        // Nivel 3: 3 7
    }
}
