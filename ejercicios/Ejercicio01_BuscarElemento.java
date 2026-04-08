/**
 * EJERCICIO 1: Búsqueda de un Elemento en un Arreglo
 * =====================================================
 * Nivel: Principiante (⭐)
 *
 * Descripción:
 *   Dado un arreglo de enteros y un valor objetivo, usa backtracking para
 *   encontrar si el elemento existe en el arreglo y retornar su índice.
 *
 * Concepto de Backtracking aplicado:
 *   Exploramos posición por posición. Si la posición actual no es la solución,
 *   retrocedemos (backtrack) e intentamos con la siguiente posición.
 *
 * Complejidad: O(n) tiempo, O(n) espacio por la pila de recursión.
 */
public class Ejercicio01_BuscarElemento {

    /**
     * Busca un elemento en el arreglo usando backtracking recursivo.
     *
     * @param arreglo El arreglo donde buscar.
     * @param objetivo El valor a encontrar.
     * @param indice   El índice actual que estamos explorando.
     * @return El índice donde se encontró el elemento, o -1 si no existe.
     */
    public static int buscar(int[] arreglo, int objetivo, int indice) {
        // Caso base: hemos revisado todos los elementos sin éxito
        if (indice == arreglo.length) {
            return -1; // No encontrado
        }

        // ¿La posición actual es la solución?
        if (arreglo[indice] == objetivo) {
            return indice; // ✅ Encontrado
        }

        // Backtrack: no es aquí, intentamos con la siguiente posición
        return buscar(arreglo, objetivo, indice + 1);
    }

    public static void main(String[] args) {
        int[] arreglo = {5, 3, 8, 1, 9, 2, 7};
        int objetivo = 9;

        System.out.println("=== Ejercicio 1: Búsqueda de Elemento ===");
        System.out.println("Arreglo: [5, 3, 8, 1, 9, 2, 7]");
        System.out.println("Buscando: " + objetivo);

        int resultado = buscar(arreglo, objetivo, 0);

        if (resultado != -1) {
            System.out.println("✅ Elemento " + objetivo + " encontrado en el índice: " + resultado);
        } else {
            System.out.println("❌ Elemento " + objetivo + " NO encontrado en el arreglo.");
        }

        // Caso no encontrado
        int noExiste = 100;
        System.out.println("\nBuscando: " + noExiste);
        int resultado2 = buscar(arreglo, noExiste, 0);
        if (resultado2 != -1) {
            System.out.println("✅ Elemento encontrado en índice: " + resultado2);
        } else {
            System.out.println("❌ Elemento " + noExiste + " NO encontrado en el arreglo.");
        }
    }
}
