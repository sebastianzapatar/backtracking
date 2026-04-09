/**
 * ============================================================
 * ALGORITMO: ORDENAMIENTO POR SELECCIÓN (Selection Sort)
 * ============================================================
 * COMPLEJIDAD TEMPORAL:
 *   - Peor caso:   O(n²)
 *   - Caso medio:  O(n²)
 *   - Mejor caso:  O(n²)  ← siempre igual, sin optimización posible
 *
 * COMPLEJIDAD ESPACIAL: O(1) — solo usa variables auxiliares
 *
 * ¿POR QUÉ ES O(n²)?
 *   Para cada posición i del arreglo (n iteraciones del bucle
 *   externo), el bucle interno escanea TODA la parte no ordenada
 *   restante (n-1, n-2, ..., 1) para encontrar el mínimo.
 *   Total de comparaciones = (n-1) + (n-2) + ... + 1 = n(n-1)/2 → O(n²).
 *
 * IDEA PRINCIPAL:
 *   Divide el arreglo en dos secciones:
 *   [ya ordenado | sin ordenar]
 *   En cada iteración, SELECCIONA el mínimo de la sección
 *   sin ordenar y lo ubica al inicio de esa sección.
 *
 * USO DE Comparable<T>:
 *   Al usar T extends Comparable<T>, podemos ordenar cualquier
 *   tipo: Integer, String, Character, o clases propias.
 * ============================================================
 */
public class SelectionSort {

    /**
     * Ordena un arreglo genérico usando Selección ascendente.
     *
     * @param <T>  Tipo de datos, debe implementar Comparable<T>
     * @param arr  El arreglo a ordenar (se modifica en sitio)
     */
    public static <T extends Comparable<T>> void selectionSort(T[] arr) {

        // Obtenemos la longitud del arreglo
        int n = arr.length;

        // --- BUCLE EXTERNO ---
        // Representa la "frontera" entre la sección ordenada y la no ordenada.
        // En cada iteración i, encontramos el mínimo de arr[i..n-1]
        // y lo colocamos en arr[i].
        // Después de la iteración i, arr[0..i] está ordenado.
        for (int i = 0; i < n - 1; i++) {

            // Asumimos que el primer elemento de la sección no ordenada
            // es el mínimo (posición i). Guardamos su ÍNDICE.
            int indiceMínimo = i;

            // --- BUCLE INTERNO ---
            // Recorre la sección no ordenada (desde i+1 hasta el final)
            // buscando si existe un elemento menor al mínimo actual.
            for (int j = i + 1; j < n; j++) {

                // compareTo() devuelve:
                //   < 0  si arr[j] < arr[indiceMínimo] → nuevo mínimo encontrado
                //   = 0  si son iguales                → no cambia el mínimo
                //   > 0  si arr[j] > arr[indiceMínimo] → el mínimo no cambia
                if (arr[j].compareTo(arr[indiceMínimo]) < 0) {

                    // Actualizamos el índice del mínimo encontrado.
                    // OJO: aún NO intercambiamos; solo guardamos el índice.
                    indiceMínimo = j;
                }
            }

            // ¿El mínimo de la sección no ordenada NO está ya en la posición i?
            // Solo intercambiamos si encontramos un elemento más pequeño.
            if (indiceMínimo != i) {

                // INTERCAMBIO: llevamos el mínimo a la posición i
                T temp              = arr[i];
                arr[i]              = arr[indiceMínimo];
                arr[indiceMínimo]   = temp;
            }
            // Si indiceMínimo == i, el elemento ya está en su lugar; no hacemos nada.
        }
    }

    // ==================== DEMO ====================
    public static void main(String[] args) {

        // ---- Ejemplo 1: Ordenar enteros ----
        Integer[] numeros = {64, 25, 12, 22, 11, 90, 34};
        System.out.println("=== SELECTION SORT ===");
        System.out.print("Antes (Integer):  ");
        imprimirArreglo(numeros);

        selectionSort(numeros);

        System.out.print("Después (Integer): ");
        imprimirArreglo(numeros);

        System.out.println();

        // ---- Ejemplo 2: Ordenar caracteres ----
        // Character implementa Comparable<Character> (orden ASCII/Unicode)
        Character[] letras = {'z', 'a', 'm', 'b', 'y', 'c'};
        System.out.print("Antes (Character): ");
        imprimirArreglo(letras);

        selectionSort(letras);

        System.out.print("Después (Character): ");
        imprimirArreglo(letras);

        System.out.println();

        // ---- Ejemplo 3: Ordenar cadenas ----
        String[] nombres = {"Carlos", "Ana", "Beatriz", "David", "Elena"};
        System.out.print("Antes (String):   ");
        imprimirArreglo(nombres);

        selectionSort(nombres);

        System.out.print("Después (String): ");
        imprimirArreglo(nombres);
    }

    /** Imprime los elementos del arreglo en una línea */
    private static <T> void imprimirArreglo(T[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
