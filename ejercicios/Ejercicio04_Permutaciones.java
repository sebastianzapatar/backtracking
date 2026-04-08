/**
 * EJERCICIO 4: Generación de Permutaciones
 * =========================================
 * Nivel: Intermedio (⭐⭐⭐)
 *
 * Descripción:
 *   Dado un arreglo de enteros distintos, generar TODAS las permutaciones
 *   posibles de sus elementos.
 *
 * Concepto de Backtracking aplicado:
 *   En cada posición del arreglo, intentamos colocar cada uno de los elementos
 *   disponibles (mediante intercambio). Luego retrocedemos (swap inverso)
 *   para restaurar el estado original y probar el siguiente elemento.
 *
 * Ejemplo:
 *   Input: [1, 2, 3]
 *   Output: [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]
 *
 * Complejidad: O(n! * n) tiempo — hay n! permutaciones, cada una toma O(n) imprimir.
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio04_Permutaciones {

    private static List<int[]> todasLasPermutaciones = new ArrayList<>();

    /**
     * Genera todas las permutaciones del arreglo usando backtracking.
     *
     * @param arreglo El arreglo de enteros.
     * @param inicio  El índice actual (fijamos elementos de 0 a inicio-1).
     */
    public static void permutar(int[] arreglo, int inicio) {
        // Caso base: hemos fijado todos los elementos → permutación completa
        if (inicio == arreglo.length) {
            System.out.println("  ✅ Permutación: " + Arrays.toString(arreglo));
            todasLasPermutaciones.add(Arrays.copyOf(arreglo, arreglo.length));
            return;
        }

        // Intentar colocar cada elemento en la posición 'inicio'
        for (int i = inicio; i < arreglo.length; i++) {
            // Colocar arreglo[i] en la posición 'inicio'
            intercambiar(arreglo, inicio, i);
            System.out.println("  Fijando posición " + inicio + " = " + arreglo[inicio]
                    + " → " + Arrays.toString(arreglo));

            // Recurrir para las siguientes posiciones
            permutar(arreglo, inicio + 1);

            // Backtrack: restaurar el estado original (intercambio inverso)
            intercambiar(arreglo, inicio, i);
            System.out.println("  ↩ Backtrack en posición " + inicio + " → " + Arrays.toString(arreglo));
        }
    }

    /**
     * Intercambia dos elementos en el arreglo.
     */
    private static void intercambiar(int[] arreglo, int i, int j) {
        int temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 4: Permutaciones ===");

        int[] arreglo = {1, 2, 3};
        System.out.println("Input: " + Arrays.toString(arreglo));
        System.out.println("\nGenerando permutaciones:");

        permutar(arreglo, 0);

        System.out.println("\n📊 Resumen:");
        System.out.println("Total de permutaciones de " + arreglo.length
                + " elementos: " + todasLasPermutaciones.size());
        System.out.println("(Esperado: " + factorial(arreglo.length) + " = " + arreglo.length + "!)");

        // Ejemplo con 4 elementos (solo contar)
        System.out.println("\n--- Con 4 elementos [1,2,3,4] ---");
        todasLasPermutaciones.clear();
        int[] arreglo4 = {1, 2, 3, 4};
        permutar(arreglo4, 0);
        System.out.println("Total permutaciones: " + todasLasPermutaciones.size() + " (4! = 24)");
    }

    private static int factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }
}
