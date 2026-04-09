/**
 * ============================================================
 * ALGORITMO: ORDENAMIENTO RÁPIDO (Quick Sort)
 * ============================================================
 * COMPLEJIDAD TEMPORAL:
 *   - Peor caso:   O(n²) — pivote siempre es el mínimo o máximo
 *   - Caso medio:  O(n log n) — caso más frecuente en la práctica
 *   - Mejor caso:  O(n log n) — pivote siempre divide en mitades iguales
 *
 * COMPLEJIDAD ESPACIAL: O(log n) — pila de recursión en promedio
 *
 * ¿POR QUÉ ES O(n log n) EN PROMEDIO?
 *   Si el pivote divide el arreglo en mitades aproximadamente iguales,
 *   obtenemos log₂(n) niveles de recursión. En cada nivel se realizan
 *   O(n) comparaciones en total. → O(n log n).
 *
 * ¿POR QUÉ O(n²) EN EL PEOR CASO?
 *   Si el arreglo ya está ordenado y el pivote es siempre el primer
 *   o último elemento, cada partición produce subarreglos de tamaño
 *   0 y n-1 → n niveles de recursión con O(n) comparaciones cada uno.
 *
 * IDEA PRINCIPAL (Divide y Vencerás):
 *   1. PIVOTE: Elige un elemento como punto de referencia.
 *   2. PARTICIÓN: Reorganiza los elementos: menores que el pivote a la
 *      izquierda, mayores a la derecha. El pivote queda en su posición final.
 *   3. RECURSIÓN: Aplica Quick Sort a los sub-arreglos izquierdo y derecho.
 *
 * OPTIMIZACIÓN USADA AQUÍ:
 *   Pivote aleatorio (Random Pivot): elige el pivote aleatoriamente,
 *   lo que elimina el peor caso en datos ordenados o con patrones.
 *
 * USO DE Comparable<T>:
 *   Permite ordenar cualquier tipo con orden natural definido.
 * ============================================================
 */
import java.util.Random;

public class QuickSort {

    // Generador de números aleatorios para elegir el pivote
    private static final Random RAND = new Random();

    /**
     * Punto de entrada público. Inicia Quick Sort sobre todo el arreglo.
     *
     * @param <T>  Tipo de datos, debe implementar Comparable<T>
     * @param arr  El arreglo a ordenar (se modifica en sitio)
     */
    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        // Llamamos al método recursivo con el rango completo
        ordenar(arr, 0, arr.length - 1);
    }

    /**
     * Método recursivo interno. Aplica Quick Sort al subarreglo arr[bajo..alto].
     *
     * @param arr   Arreglo a ordenar
     * @param bajo  Índice inicial del subarreglo
     * @param alto  Índice final del subarreglo
     */
    private static <T extends Comparable<T>> void ordenar(T[] arr, int bajo, int alto) {

        // --- CASO BASE ---
        // Si el subarreglo tiene 0 ó 1 elementos, ya está ordenado.
        if (bajo >= alto) return;

        // --- OPTIMIZACIÓN: PIVOTE ALEATORIO ---
        // Elegimos una posición aleatoria dentro del rango [bajo, alto],
        // intercambiamos ese elemento con el último elemento (alto),
        // y luego usamos arr[alto] como pivote en la partición.
        // Esto evita el peor caso O(n²) con datos ordenados.
        int pivoteAleatorio = bajo + RAND.nextInt(alto - bajo + 1);
        intercambiar(arr, pivoteAleatorio, alto);

        // --- PARTICIÓN ---
        // Reorganiza arr[bajo..alto] alrededor del pivote y retorna
        // el ÍNDICE FINAL donde quedó el pivote.
        // Después de particionar:
        //   arr[bajo .. posicionPivote-1] <= pivote
        //   arr[posicionPivote]           == pivote (en su lugar definitivo)
        //   arr[posicionPivote+1 .. alto] >  pivote
        int posicionPivote = particionar(arr, bajo, alto);

        // --- RECURSIÓN ---
        // Ordenamos recursivamente la mitad izquierda (excluye el pivote)
        ordenar(arr, bajo, posicionPivote - 1);

        // Ordenamos recursivamente la mitad derecha (excluye el pivote)
        ordenar(arr, posicionPivote + 1, alto);
    }

    /**
     * Esquema de partición de Lomuto.
     *
     * Usa arr[alto] como pivote. Recorre el arreglo de izquierda a derecha
     * y coloca todos los elementos menores o iguales al pivote antes que él.
     *
     * Al final, intercambia el pivote a su posición correcta y retorna ese índice.
     *
     * @param arr   Arreglo a particionar
     * @param bajo  Índice inicial
     * @param alto  Índice final (donde está el pivote)
     * @return      Índice final del pivote después de la partición
     */
    private static <T extends Comparable<T>> int particionar(T[] arr, int bajo, int alto) {

        // El pivote es el elemento en la posición 'alto' (ya elegido antes)
        T pivote = arr[alto];

        // 'i' marca la frontera de la sección "menores que pivote".
        // Comienza en bajo-1 (vacío al inicio).
        int i = bajo - 1;

        // Recorremos todos los elementos excepto el pivote (alto)
        for (int j = bajo; j < alto; j++) {

            // compareTo() <= 0 significa: arr[j] <= pivote
            // → este elemento pertenece al lado izquierdo (menor o igual)
            if (arr[j].compareTo(pivote) <= 0) {

                // Expandimos la sección "menor o igual" hacia la derecha
                i++;

                // Intercambiamos arr[i] con arr[j] para poner arr[j]
                // en el lado izquierdo del pivote.
                intercambiar(arr, i, j);
            }
            // Si arr[j] > pivote, simplemente continuamos (j avanza solo)
        }

        // Colocamos el pivote en su posición definitiva: justo después
        // de todos los elementos menores o iguales (posición i+1).
        intercambiar(arr, i + 1, alto);

        // Retornamos el índice donde quedó el pivote
        return i + 1;
    }

    /**
     * Intercambia los elementos en las posiciones a y b del arreglo.
     */
    private static <T> void intercambiar(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // ==================== DEMO ====================
    public static void main(String[] args) {

        // ---- Ejemplo 1: Ordenar enteros ----
        Integer[] numeros = {10, 80, 30, 90, 40, 50, 70};
        System.out.println("=== QUICK SORT ===");
        System.out.print("Antes (Integer):  ");
        imprimirArreglo(numeros);

        quickSort(numeros);

        System.out.print("Después (Integer): ");
        imprimirArreglo(numeros);

        System.out.println();

        // ---- Ejemplo 2: Ordenar strings ----
        String[] lenguajes = {"Python", "Java", "C++", "JavaScript", "Go", "Rust"};
        System.out.print("Antes (String):   ");
        imprimirArreglo(lenguajes);

        quickSort(lenguajes);

        System.out.print("Después (String): ");
        imprimirArreglo(lenguajes);

        System.out.println();

        // ---- Ejemplo 3: Arreglo ya ordenado ----
        // Con pivote aleatorio, evita el peor caso que tendría un Quick Sort sin optimización.
        Integer[] yaOrdenado = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.print("Antes (ya ordenado):  ");
        imprimirArreglo(yaOrdenado);

        quickSort(yaOrdenado);

        System.out.print("Después (ya ordenado): ");
        imprimirArreglo(yaOrdenado);

        System.out.println();

        // ---- Ejemplo 4: Arreglo de doubles ----
        Double[] precios = {3.99, 1.49, 2.75, 0.99, 4.50, 2.10};
        System.out.print("Antes (Double):   ");
        imprimirArreglo(precios);

        quickSort(precios);

        System.out.print("Después (Double): ");
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
