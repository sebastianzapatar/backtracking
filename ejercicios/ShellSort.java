/**
 * ============================================================
 * ALGORITMO: ORDENAMIENTO DE SHELL (Shell Sort)
 * ============================================================
 * COMPLEJIDAD TEMPORAL:
 *   - Peor caso:   O(n²)       — con secuencia de brechas original (Shell, 1959)
 *   - Caso medio:  O(n^(4/3))  — con secuencia de Knuth: 1, 4, 13, 40, 121...
 *   - Mejor caso:  O(n log n)  — arreglo casi ordenado
 *
 * COMPLEJIDAD ESPACIAL: O(1) — in-place, sin arreglos auxiliares
 *
 * ¿POR QUÉ ES MÁS RÁPIDO QUE INSERTION SORT?
 *   Insertion Sort mueve elementos un solo paso a la vez → lento en arreglos desordenados.
 *   Shell Sort usa BRECHAS (gaps) grandes para mover elementos LEJOS en pocos pasos.
 *   Cuando la brecha llega a 1, el arreglo ya está "casi ordenado"
 *   → Insertion Sort termina muy rápido (mejor caso O(n)).
 *
 * IDEA PRINCIPAL:
 *   Es una GENERALIZACIÓN de Insertion Sort.
 *   En vez de comparar e insertar elementos adyacentes (brecha=1),
 *   usa brechas decrecientes:
 *     - Primero ordena elementos separados por una brecha grande (ej. n/2).
 *     - Luego reduce la brecha (ej. gap/2) y repite.
 *     - Finalmente, cuando brecha=1, es un Insertion Sort sobre datos casi ordenados.
 *
 * SECUENCIA DE KNUTH (usada aquí):
 *   1, 4, 13, 40, 121, 364, ...  → gap = 3*gap + 1
 *   Garantiza mejor rendimiento que la secuencia original de Shell (n/2, n/4, ..., 1).
 *
 * USO DE Comparable<T>:
 *   Permite ordenar Integer, String, Double, o cualquier clase personalizada.
 * ============================================================
 */
public class ShellSort {

    /**
     * Ordena un arreglo genérico usando Shell Sort con secuencia de Knuth.
     *
     * @param <T>  Tipo de datos, debe implementar Comparable<T>
     * @param arr  El arreglo a ordenar (se modifica en sitio)
     */
    public static <T extends Comparable<T>> void shellSort(T[] arr) {

        // Obtenemos la longitud del arreglo
        int n = arr.length;

        // --- PASO 1: CALCULAR BRECHA INICIAL (Secuencia de Knuth) ---
        // Generamos la secuencia 1, 4, 13, 40, 121... hasta el valor
        // máximo que sea menor que n/3. Esa es nuestra brecha inicial.
        int gap = 1;
        while (gap < n / 3) {
            gap = 3 * gap + 1;  // Fórmula de Knuth: h = 3h + 1
        }

        // --- PASO 2: BUCLE DE REDUCCIÓN DE BRECHA ---
        // Ejecutamos un "Insertion Sort con brecha" para cada valor de brecha,
        // reduciendo hasta gap=1 (Insertion Sort estándar al final).
        while (gap >= 1) {

            // --- BUCLE DE INSERCIÓN CON BRECHA ---
            // Igual que Insertion Sort, pero en vez de comparar arr[i] con arr[i-1],
            // comparamos arr[i] con arr[i-gap] (elementos distantes 'gap' posiciones).
            for (int i = gap; i < n; i++) {

                // Guardamos el elemento a insertar en su posición correcta
                T llave = arr[i];

                // Retrocedemos de 'gap en gap' hacia la izquierda
                int j = i;

                // Desplazamos elementos que son mayores que 'llave'
                // 'gap' posiciones hacia la derecha.
                // Condición: j-gap >= 0 (no salirnos del arreglo)
                //        AND arr[j-gap] > llave (el elemento de atrás es mayor)
                while (j >= gap && arr[j - gap].compareTo(llave) > 0) {

                    // Desplazamos el elemento 'gap' posiciones a la derecha
                    arr[j] = arr[j - gap];

                    // Retrocedemos el puntero 'gap' posiciones
                    j -= gap;
                }

                // Insertamos la 'llave' en su posición correcta dentro del
                // subarreglo con "brecha gap"
                arr[j] = llave;
            }

            // --- REDUCCIÓN DE LA BRECHA ---
            // Usamos la fórmula inversa de Knuth: h = (h - 1) / 3
            // Esto lleva la secuencia ...40 → 13 → 4 → 1 → 0 (termina)
            gap = (gap - 1) / 3;
        }
    }

    // ==================== DEMO ====================
    public static void main(String[] args) {

        // ---- Ejemplo 1: Ordenar enteros ----
        Integer[] numeros = {62, 83, 18, 53, 07, 17, 95, 86, 47, 69, 25, 28};
        System.out.println("=== SHELL SORT (Secuencia de Knuth) ===");
        System.out.print("Antes (Integer):  ");
        imprimirArreglo(numeros);

        shellSort(numeros);

        System.out.print("Después (Integer): ");
        imprimirArreglo(numeros);

        System.out.println();

        // ---- Ejemplo 2: Ordenar strings ----
        String[] animales = {"Zebra", "Mono", "Elefante", "Rana", "Tigre", "Ardilla", "Puma"};
        System.out.print("Antes (String):   ");
        imprimirArreglo(animales);

        shellSort(animales);

        System.out.print("Después (String): ");
        imprimirArreglo(animales);

        System.out.println();

        // ---- Ejemplo 3: Ordenar doubles ----
        Double[] temperaturas = {36.8, 37.5, 35.9, 38.2, 36.1, 37.0, 39.4};
        System.out.print("Antes (Double):   ");
        imprimirArreglo(temperaturas);

        shellSort(temperaturas);

        System.out.print("Después (Double): ");
        imprimirArreglo(temperaturas);

        System.out.println();

        // ---- Ejemplo 4: Demostrar la secuencia de brechas usada ----
        System.out.println("Secuencia de Knuth generada para n=12:");
        int n = 12;
        int gap = 1;
        System.out.print("  Brechas: ");
        // Calculamos mostrando los gaps usados (de mayor a menor)
        java.util.List<Integer> gaps = new java.util.ArrayList<>();
        gap = 1;
        while (gap < n / 3) gap = 3 * gap + 1;
        while (gap >= 1) {
            gaps.add(gap);
            gap = (gap - 1) / 3;
        }
        System.out.println(gaps);
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
