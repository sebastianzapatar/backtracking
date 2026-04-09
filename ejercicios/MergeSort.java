/**
 * ============================================================
 * ALGORITMO: ORDENAMIENTO POR MEZCLA (Merge Sort)
 * ============================================================
 * COMPLEJIDAD TEMPORAL:
 *   - Peor caso:   O(n log n) — siempre el mismo rendimiento
 *   - Caso medio:  O(n log n)
 *   - Mejor caso:  O(n log n) — no se beneficia de datos ordenados
 *
 * COMPLEJIDAD ESPACIAL: O(n) — requiere arreglos auxiliares para la mezcla
 *
 * ¿POR QUÉ ES O(n log n)?
 *   La división del arreglo genera log₂(n) niveles de recursión.
 *   En cada nivel se realiza un trabajo de O(n) para mezclar.
 *   Total: O(n) × O(log n) = O(n log n).
 *
 * IDEA PRINCIPAL (Divide y Vencerás):
 *   1. DIVIDIR: parte el arreglo por la mitad hasta tener subarreglos de 1 elemento.
 *   2. CONQUISTAR: ordena cada mitad de forma recursiva.
 *   3. COMBINAR: mezcla dos mitades ordenadas en un arreglo ordenado más grande.
 *
 * VENTAJA CLAVE:
 *   Es un algoritmo ESTABLE: mantiene el orden relativo de elementos iguales.
 *   Rendimiento garantizado en TODOS los casos (no depende de los datos).
 *
 * USO DE Comparable<T>:
 *   Permite ordenar cualquier tipo que implemente compareTo():
 *   Integer, String, Double, o clases personalizadas.
 * ============================================================
 */
public class MergeSort {

    /**
     * Punto de entrada público. Inicia el proceso divide y vencerás.
     *
     * @param <T>  Tipo de datos, debe implementar Comparable<T>
     * @param arr  El arreglo a ordenar (se modifica en sitio)
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        // Caso base: un arreglo de 0 ó 1 elementos ya está ordenado
        if (arr.length <= 1) return;

        // Creamos un arreglo auxiliar del mismo tamaño para las mezclas
        // NOTA: La conversión (T[]) es necesaria por la limitación de tipos
        //       genéricos en Java (type erasure). Es segura en este contexto.
        T[] aux = (T[]) new Comparable[arr.length];

        // Iniciamos la recursión sobre todo el arreglo
        ordenar(arr, aux, 0, arr.length - 1);
    }

    /**
     * Método recursivo privado. Aplica Divide y Vencerás.
     *
     * @param arr  Arreglo original que se está ordenando
     * @param aux  Arreglo auxiliar para la fase de mezcla
     * @param ini  Índice inicial del subarreglo actual
     * @param fin  Índice final del subarreglo actual
     */
    private static <T extends Comparable<T>> void ordenar(T[] arr, T[] aux, int ini, int fin) {

        // --- CASO BASE ---
        // Si el subarreglo tiene 0 ó 1 elemento, ya está "ordenado".
        // La recursión se detiene aquí.
        if (ini >= fin) return;

        // --- DIVIDIR ---
        // Calculamos el índice del elemento central.
        // Usamos ini + (fin - ini) / 2 en vez de (ini + fin) / 2
        // para evitar desbordamiento de enteros con arreglos muy grandes.
        int mid = ini + (fin - ini) / 2;

        // Ordenamos RECURSIVAMENTE la mitad izquierda [ini .. mid]
        ordenar(arr, aux, ini, mid);

        // Ordenamos RECURSIVAMENTE la mitad derecha [mid+1 .. fin]
        ordenar(arr, aux, mid + 1, fin);

        // --- COMBINAR ---
        // Mezclamos las dos mitades ya ordenadas
        mezclar(arr, aux, ini, mid, fin);
    }

    /**
     * Mezcla dos subarreglos ya ordenados en uno solo ordenado.
     *
     * Subarreglo izquierdo: arr[ini .. mid]
     * Subarreglo derecho:   arr[mid+1 .. fin]
     *
     * Resultado: arr[ini .. fin] queda completamente ordenado.
     */
    private static <T extends Comparable<T>> void mezclar(T[] arr, T[] aux, int ini, int mid, int fin) {

        // Copiamos todos los elementos de arr[ini..fin] al auxiliar.
        // Necesitamos esta copia para poder leer los valores originales
        // mientras escribimos en arr[].
        for (int k = ini; k <= fin; k++) {
            aux[k] = arr[k];
        }

        // Puntero para la mitad izquierda (comienza en 'ini')
        int i = ini;

        // Puntero para la mitad derecha (comienza en 'mid+1')
        int j = mid + 1;

        // Puntero de escritura en el arreglo original (comienza en 'ini')
        int k = ini;

        // --- MEZCLA PRINCIPAL ---
        // Comparamos elemento a elemento de ambas mitades y
        // ponemos siempre el más pequeño en arr[k].
        while (i <= mid && j <= fin) {

            // compareTo() <= 0 significa: aux[i] es menor o igual a aux[j]
            // → tomamos el elemento de la izquierda (ESTABILIDAD: en igualdad, izquierda primero)
            if (aux[i].compareTo(aux[j]) <= 0) {
                arr[k] = aux[i];
                i++;  // Avanzamos puntero izquierdo
            } else {
                // aux[j] es menor → tomamos el elemento de la derecha
                arr[k] = aux[j];
                j++;  // Avanzamos puntero derecho
            }
            k++;  // Siempre avanzamos el puntero de escritura
        }

        // Si quedaron elementos en la mitad IZQUIERDA sin procesar, los copiamos.
        // (Los de la derecha ya están en su lugar en arr[], no hace falta copiarlos)
        while (i <= mid) {
            arr[k] = aux[i];
            i++;
            k++;
        }
        // NOTA: Si quedaron en la derecha (j <= fin), ya están en arr[] —
        //       aux[j..fin] = arr[j..fin] y no se movieron.
    }

    // ==================== DEMO ====================
    public static void main(String[] args) {

        // ---- Ejemplo 1: Ordenar enteros ----
        Integer[] numeros = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("=== MERGE SORT ===");
        System.out.print("Antes (Integer):  ");
        imprimirArreglo(numeros);

        mergeSort(numeros);

        System.out.print("Después (Integer): ");
        imprimirArreglo(numeros);

        System.out.println();

        // ---- Ejemplo 2: Ordenar strings ----
        String[] ciudades = {"Medellín", "Bogotá", "Cali", "Cartagena", "Barranquilla"};
        System.out.print("Antes (String):   ");
        imprimirArreglo(ciudades);

        mergeSort(ciudades);

        System.out.print("Después (String): ");
        imprimirArreglo(ciudades);

        System.out.println();

        // ---- Ejemplo 3: Verificar ESTABILIDAD ----
        // Merge Sort mantiene el orden relativo de iguales.
        // Aquí ponemos intencionalmente valores repetidos.
        Integer[] conRepetidos = {5, 1, 4, 2, 5, 3, 2, 5};
        System.out.print("Antes (repetidos):  ");
        imprimirArreglo(conRepetidos);

        mergeSort(conRepetidos);

        System.out.print("Después (repetidos): ");
        imprimirArreglo(conRepetidos);
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
