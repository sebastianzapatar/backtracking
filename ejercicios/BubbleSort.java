/**
 * ============================================================
 * ALGORITMO: ORDENAMIENTO BURBUJA (Bubble Sort)
 * ============================================================
 * COMPLEJIDAD TEMPORAL:
 *   - Peor caso:   O(n²) — arreglo en orden inverso
 *   - Caso medio:  O(n²)
 *   - Mejor caso:  O(n)  — con bandera de optimización (ya ordenado)
 *
 * COMPLEJIDAD ESPACIAL: O(1) — solo usa variables auxiliares
 *
 * ¿POR QUÉ ES O(n²)?
 *   Tiene dos bucles anidados. El externo recorre n-1 veces y
 *   el interno compara pares adyacentes en cada pasada.
 *   Total de comparaciones ≈ n*(n-1)/2 → O(n²).
 *
 * IDEA PRINCIPAL:
 *   En cada pasada, el elemento más grande "burbujea" hasta
 *   el final del arreglo, como burbujas subiendo al agua.
 *
 * USO DE Comparable<T>:
 *   Aceptamos arreglos de tipo T que implementen Comparable<T>,
 *   lo que permite ordenar cualquier objeto (Integer, String,
 *   o cualquier clase personalizada que implemente compareTo).
 * ============================================================
 */
public class BubbleSort {

    /**
     * Ordena un arreglo genérico usando Burbuja ascendente.
     *
     * @param <T>    Tipo de datos, debe implementar Comparable<T>
     * @param arr    El arreglo a ordenar (se modifica en sitio)
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {

        // Obtenemos la longitud del arreglo
        int n = arr.length;

        // --- BUCLE EXTERNO ---
        // Controla cuántas "pasadas" completas se hacen sobre el arreglo.
        // Después de la pasada i-ésima, los últimos (i+1) elementos ya
        // están en su posición definitiva, por eso recorremos hasta n-1-i.
        for (int i = 0; i < n - 1; i++) {

            // Bandera de optimización: si en una pasada no hubo
            // ningún intercambio, el arreglo ya está ordenado
            // → salimos antes de tiempo (mejor caso O(n)).
            boolean huboIntercambio = false;

            // --- BUCLE INTERNO ---
            // Compara pares adyacentes [j] y [j+1].
            // El límite (n - 1 - i) evita comparar con los
            // elementos ya ordenados al final del arreglo.
            for (int j = 0; j < n - 1 - i; j++) {

                // compareTo() devuelve:
                //   < 0  si arr[j] < arr[j+1]  → ya están en orden
                //   = 0  si arr[j] == arr[j+1] → no importa el orden
                //   > 0  si arr[j] > arr[j+1]  → HAY QUE INTERCAMBIAR
                if (arr[j].compareTo(arr[j + 1]) > 0) {

                    // INTERCAMBIO: usamos una variable temporal
                    // para no perder el valor de arr[j]
                    T temp   = arr[j];
                    arr[j]   = arr[j + 1];
                    arr[j + 1] = temp;

                    // Marcamos que sí ocurrió un intercambio
                    huboIntercambio = true;
                }
            }

            // Si no hubo ningún intercambio en esta pasada,
            // el arreglo ya está completamente ordenado → terminamos
            if (!huboIntercambio) {
                break;
            }
        }
    }

    // ==================== DEMO ====================
    public static void main(String[] args) {

        // ---- Ejemplo 1: Ordenar enteros ----
        Integer[] numeros = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("=== BUBBLE SORT ===");
        System.out.print("Antes (Integer): ");
        imprimirArreglo(numeros);

        bubbleSort(numeros);

        System.out.print("Después (Integer): ");
        imprimirArreglo(numeros);

        System.out.println();

        // ---- Ejemplo 2: Ordenar cadenas de texto ----
        // String ya implementa Comparable<String> (orden lexicográfico)
        String[] palabras = {"manzana", "cereza", "banana", "durazno", "aguacate"};
        System.out.print("Antes (String):   ");
        imprimirArreglo(palabras);

        bubbleSort(palabras);

        System.out.print("Después (String): ");
        imprimirArreglo(palabras);

        System.out.println();

        // ---- Ejemplo 3: Ordenar números decimales ----
        Double[] decimales = {3.14, 2.71, 1.41, 1.73, 0.57};
        System.out.print("Antes (Double):   ");
        imprimirArreglo(decimales);

        bubbleSort(decimales);

        System.out.print("Después (Double): ");
        imprimirArreglo(decimales);
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
