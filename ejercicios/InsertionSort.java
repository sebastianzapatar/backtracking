/**
 * ============================================================
 * ALGORITMO: ORDENAMIENTO POR INSERCIÓN (Insertion Sort)
 * ============================================================
 * COMPLEJIDAD TEMPORAL:
 *   - Peor caso:   O(n²) — arreglo en orden inverso
 *   - Caso medio:  O(n²)
 *   - Mejor caso:  O(n)  — arreglo casi o completamente ordenado
 *
 * COMPLEJIDAD ESPACIAL: O(1) — solo usa variables auxiliares
 *
 * ¿POR QUÉ ES O(n²)?
 *   El bucle externo recorre n-1 elementos. Por cada elemento,
 *   el bucle interno puede desplazar hasta todos los elementos
 *   ya ordenados en el peor caso.
 *   Total de desplazamientos ≈ 1 + 2 + ... + (n-1) = n(n-1)/2 → O(n²).
 *
 * IDEA PRINCIPAL:
 *   Funciona como ordenar cartas en la mano:
 *   Toma una carta nueva y la "inserta" en la posición correcta
 *   entre las cartas que ya están ordenadas, desplazando
 *   las demás hacia la derecha.
 *
 * USO DE Comparable<T>:
 *   Al declarar T extends Comparable<T>, el método acepta
 *   cualquier tipo que sepa compararse a sí mismo (Integer,
 *   String, Double, o tu clase personalizada).
 * ============================================================
 */
public class InsertionSort {

    /**
     * Ordena un arreglo genérico usando Inserción ascendente.
     *
     * @param <T>  Tipo de datos, debe implementar Comparable<T>
     * @param arr  El arreglo a ordenar (se modifica en sitio)
     */
    public static <T extends Comparable<T>> void insertionSort(T[] arr) {

        // Obtenemos la longitud del arreglo
        int n = arr.length;

        // --- BUCLE EXTERNO ---
        // Comienza desde el índice 1 porque el primer elemento (índice 0)
        // ya forma un "subarreglo ordenado" de 1 solo elemento.
        // En cada iteración i, el subarreglo arr[0..i-1] ya está ordenado.
        // Nuestra tarea: insertar arr[i] en la posición correcta dentro de ese subarreglo.
        for (int i = 1; i < n; i++) {

            // "Tomamos la carta" que vamos a insertar.
            // La guardamos en 'llave' porque el bucle interno
            // puede sobrescribir arr[i] con desplazamientos.
            T llave = arr[i];

            // Puntero j apunta al elemento justo a la izquierda de la 'llave'.
            // Empezamos desde i-1 e iremos retrocediendo hacia la izquierda.
            int j = i - 1;

            // --- BUCLE INTERNO ---
            // Condición de continuación (ambas deben cumplirse):
            //   1. j >= 0          → no salirnos por la izquierda del arreglo
            //   2. arr[j] > llave  → el elemento actual es mayor que la llave,
            //                        así que hay que desplazarlo a la derecha
            //
            // compareTo() > 0 significa: arr[j] es MAYOR que 'llave'
            // → arr[j] debe moverse una posición a la derecha (arr[j+1] = arr[j])
            while (j >= 0 && arr[j].compareTo(llave) > 0) {

                // DESPLAZAMIENTO: movemos el elemento una posición a la derecha.
                // Esto "abre un hueco" en el arreglo para insertar la llave.
                arr[j + 1] = arr[j];

                // Retrocedemos el puntero j hacia la izquierda
                // para comparar con el siguiente elemento del subarreglo.
                j--;
            }

            // Al salir del while, alguna de estas condiciones fue verdadera:
            //   → j < 0         : llegamos al inicio; la llave es el mínimo
            //   → arr[j] <= llave: encontramos el elemento que es menor o igual a la llave
            //
            // En ambos casos, 'j+1' es la posición correcta de la llave.
            // Insertamos la llave en esa posición.
            arr[j + 1] = llave;
        }
    }

    // ==================== DEMO ====================
    public static void main(String[] args) {

        // ---- Ejemplo 1: Ordenar enteros ----
        Integer[] numeros = {5, 2, 4, 6, 1, 3};
        System.out.println("=== INSERTION SORT ===");
        System.out.print("Antes (Integer):  ");
        imprimirArreglo(numeros);

        insertionSort(numeros);

        System.out.print("Después (Integer): ");
        imprimirArreglo(numeros);

        System.out.println();

        // ---- Ejemplo 2: Ordenar strings ----
        String[] frutas = {"uva", "pera", "kiwi", "fresa", "lima"};
        System.out.print("Antes (String):   ");
        imprimirArreglo(frutas);

        insertionSort(frutas);

        System.out.print("Después (String): ");
        imprimirArreglo(frutas);

        System.out.println();

        // ---- Ejemplo 3: Mejor caso (casi ordenado) ----
        // Insertion Sort brilla aquí → O(n) en la práctica
        Integer[] casiOrdenado = {1, 2, 3, 5, 4};
        System.out.print("Antes (casi ordenado):  ");
        imprimirArreglo(casiOrdenado);

        insertionSort(casiOrdenado);

        System.out.print("Después (casi ordenado): ");
        imprimirArreglo(casiOrdenado);

        System.out.println();

        // ---- Ejemplo 4: Clase personalizada ----
        // Demostramos que funciona con CUALQUIER clase que implemente Comparable
        Producto[] productos = {
            new Producto("Laptop",  1200.00),
            new Producto("Mouse",     25.99),
            new Producto("Monitor",  350.00),
            new Producto("Teclado",   79.99)
        };
        System.out.print("Antes (Producto por precio):   ");
        imprimirArreglo(productos);

        insertionSort(productos);

        System.out.print("Después (Producto por precio): ");
        imprimirArreglo(productos);
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

    // =====================================================
    // CLASE INTERNA DE EJEMPLO: Producto implementa Comparable
    // Esto demuestra que los algoritmos funcionan con objetos
    // propios, no solo con primitivos/wrappers de Java.
    // =====================================================
    static class Producto implements Comparable<Producto> {

        private String nombre;
        private double precio;

        public Producto(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        /**
         * Definimos el criterio de comparación: por precio ascendente.
         * compareTo:
         *   devuelve < 0 si this.precio <  otro.precio
         *   devuelve   0 si this.precio == otro.precio
         *   devuelve > 0 si this.precio >  otro.precio
         *
         * Double.compare es equivalente a (this.precio - otro.precio)
         * pero sin riesgo de desbordamiento con decimales.
         */
        @Override
        public int compareTo(Producto otro) {
            return Double.compare(this.precio, otro.precio);
        }

        // Representación textual para imprimir
        @Override
        public String toString() {
            return nombre + "($" + precio + ")";
        }
    }
}
