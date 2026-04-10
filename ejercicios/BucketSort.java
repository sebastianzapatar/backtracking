/**
 * ============================================================
 * ALGORITMO: ORDENAMIENTO POR CUBETAS (Bucket Sort)
 * ============================================================
 * COMPLEJIDAD TEMPORAL:
 *   - Peor caso:   O(n²) — todos los elementos caen en un solo bucket
 *   - Caso medio:  O(n + k) — distribución uniforme, k = número de buckets
 *   - Mejor caso:  O(n + k) — distribución uniforme perfecta
 *
 * COMPLEJIDAD ESPACIAL: O(n + k) — buckets auxiliares + listas internas
 *
 * ¿POR QUÉ PUEDE SER O(n)?
 *   Si los datos están uniformemente distribuidos en un rango conocido,
 *   cada bucket recibe ~n/k elementos. Ordenar cada bucket con Insertion
 *   Sort cuesta O((n/k)²). Con k ≈ n buckets → O(n·(1)²) = O(n).
 *
 * IDEA PRINCIPAL (Distribución):
 *   1. CREAR BUCKETS: dividir el rango de valores en k intervalos iguales.
 *   2. DISTRIBUIR: colocar cada elemento en el bucket correspondiente.
 *   3. ORDENAR: ordenar cada bucket internamente (Insertion Sort).
 *   4. CONCATENAR: unir todos los buckets en orden para obtener el resultado.
 *
 * IMPORTANTE:
 *   - NO es un algoritmo basado en comparaciones puras.
 *   - Funciona mejor con datos numéricos distribuidos uniformemente.
 *   - Es ESTABLE si el ordenamiento interno es estable (Insertion Sort lo es).
 *
 * USO DE Comparable<T> Y Number:
 *   Se usa <T extends Number & Comparable<T>> para aceptar cualquier tipo
 *   numérico que defina orden natural: Integer, Double, Float, Long, etc.
 *   - Comparable<T>: proporciona compareTo() para Insertion Sort interno.
 *   - Number:        proporciona doubleValue() para calcular el índice del bucket.
 * ============================================================
 */
import java.util.ArrayList;
import java.util.List;

public class BucketSort {

    /**
     * Ordena un arreglo genérico de tipos numéricos usando Bucket Sort.
     * Usa n buckets por defecto (óptimo para distribución uniforme).
     *
     * <T> debe extender Number (para obtener valor numérico) y
     *     Comparable<T> (para comparaciones en Insertion Sort interno).
     *
     * @param <T> Tipo de datos numérico con orden natural (Integer, Double, Float, Long…)
     * @param arr El arreglo a ordenar (se modifica en sitio)
     */
    public static <T extends Number & Comparable<T>> void bucketSort(T[] arr) {
        // Por defecto, usamos n buckets (óptimo para distribución uniforme)
        bucketSort(arr, arr.length);
    }

    /**
     * Ordena un arreglo genérico de tipos numéricos usando Bucket Sort
     * con un número personalizado de buckets.
     *
     * @param <T>        Tipo de datos numérico con orden natural
     * @param arr        El arreglo a ordenar (se modifica en sitio)
     * @param numBuckets Número de cubetas a utilizar
     */
    public static <T extends Number & Comparable<T>> void bucketSort(T[] arr, int numBuckets) {
        int n = arr.length;

        // Caso base: 0 ó 1 elementos ya están ordenados
        if (n <= 1) return;

        // Aseguramos al menos 1 bucket
        if (numBuckets < 1) numBuckets = n;

        // --- Encontrar min y max usando compareTo() ---
        // Necesitamos el rango para normalizar los valores a índices de bucket.
        T min = arr[0];
        T max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i].compareTo(min) < 0) min = arr[i];
            if (arr[i].compareTo(max) > 0) max = arr[i];
        }

        // Si todos los elementos son iguales, ya está ordenado
        if (min.compareTo(max) == 0) return;

        // --- PASO 1: CREAR BUCKETS ---
        // Creamos numBuckets listas vacías. Cada lista almacenará los elementos
        // que caen en ese intervalo del rango [min, max].
        @SuppressWarnings("unchecked")
        List<T>[] buckets = new ArrayList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }

        // --- PASO 2: DISTRIBUIR ---
        // Usamos doubleValue() (de Number) para convertir el elemento a double
        // y calcular en qué bucket debe caer.
        //
        // Fórmula de normalización:
        //   índice = (valor - min) * (numBuckets - 1) / (max - min)
        //
        // Esto mapea el valor mínimo al bucket 0 y el valor máximo al bucket (numBuckets-1).
        double minVal = min.doubleValue();
        double rango = max.doubleValue() - minVal;

        for (int i = 0; i < n; i++) {
            int indBucket = (int) ((arr[i].doubleValue() - minVal) * (numBuckets - 1) / rango);

            // Protección por precisión de punto flotante
            if (indBucket >= numBuckets) indBucket = numBuckets - 1;
            if (indBucket < 0) indBucket = 0;

            buckets[indBucket].add(arr[i]);
        }

        // --- PASO 3: ORDENAR CADA BUCKET con Insertion Sort genérico ---
        // Usamos Insertion Sort porque:
        //   - Es eficiente en listas pequeñas (los buckets suelen ser pequeños).
        //   - Es ESTABLE (mantiene el orden relativo de duplicados).
        //   - Usa compareTo() → funciona con cualquier Comparable<T>.
        for (int i = 0; i < numBuckets; i++) {
            insertionSortLista(buckets[i]);
        }

        // --- PASO 4: CONCATENAR ---
        // Recorremos todos los buckets en orden y volcamos los elementos
        // de vuelta al arreglo original.
        int idx = 0;
        for (int i = 0; i < numBuckets; i++) {
            for (T valor : buckets[i]) {
                arr[idx] = valor;
                idx++;
            }
        }
    }

    /**
     * Insertion Sort genérico sobre una lista (para ordenar cada bucket).
     *
     * Usa compareTo() para comparar elementos → funciona con cualquier
     * tipo que implemente Comparable<T>.
     *
     * Se usa Insertion Sort porque:
     *   - Es eficiente en listas pequeñas (los buckets suelen tener pocos elementos).
     *   - Es estable (mantiene el orden relativo de duplicados).
     *   - No requiere espacio adicional.
     *
     * @param <T>   Tipo de datos con orden natural
     * @param lista La lista a ordenar en sitio
     */
    private static <T extends Comparable<T>> void insertionSortLista(List<T> lista) {
        for (int i = 1; i < lista.size(); i++) {
            T llave = lista.get(i);
            int j = i - 1;

            // Desplazar elementos mayores que 'llave' una posición a la derecha
            // compareTo() > 0 significa: lista.get(j) > llave
            while (j >= 0 && lista.get(j).compareTo(llave) > 0) {
                lista.set(j + 1, lista.get(j));
                j--;
            }

            // Insertar 'llave' en su posición correcta
            lista.set(j + 1, llave);
        }
    }

    // ==================== DEMO ====================
    public static void main(String[] args) {

        // ---- Ejemplo 1: Ordenar Integer ----
        Integer[] numeros = {42, 15, 87, 23, 56, 91, 34, 12, 67, 45};
        System.out.println("=== BUCKET SORT ===");
        System.out.print("Antes (Integer):  ");
        imprimirArreglo(numeros);

        bucketSort(numeros);

        System.out.print("Después (Integer): ");
        imprimirArreglo(numeros);

        System.out.println();

        // ---- Ejemplo 2: Ordenar Double ----
        Double[] decimales = {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68};
        System.out.print("Antes (Double):   ");
        imprimirArreglo(decimales);

        bucketSort(decimales);

        System.out.print("Después (Double):  ");
        imprimirArreglo(decimales);

        System.out.println();

        // ---- Ejemplo 3: Especificar número de buckets ----
        Integer[] grande = {150, 23, 89, 12, 200, 45, 178, 67, 134, 99, 56, 180, 11, 73, 145};
        System.out.print("Antes (5 buckets):   ");
        imprimirArreglo(grande);

        bucketSort(grande, 5);  // Usamos 5 buckets

        System.out.print("Después (5 buckets):  ");
        imprimirArreglo(grande);

        System.out.println();

        // ---- Ejemplo 4: Verificar ESTABILIDAD con repetidos ----
        Integer[] conRepetidos = {5, 1, 4, 2, 5, 3, 2, 5};
        System.out.print("Antes (repetidos):  ");
        imprimirArreglo(conRepetidos);

        bucketSort(conRepetidos);

        System.out.print("Después (repetidos): ");
        imprimirArreglo(conRepetidos);

        System.out.println();

        // ---- Ejemplo 5: Float ----
        Float[] precios = {3.99f, 1.49f, 2.75f, 0.99f, 4.50f, 2.10f};
        System.out.print("Antes (Float):    ");
        imprimirArreglo(precios);

        bucketSort(precios);

        System.out.print("Después (Float):   ");
        imprimirArreglo(precios);
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
