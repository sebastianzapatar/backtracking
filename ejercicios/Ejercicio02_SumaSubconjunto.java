/**
 * EJERCICIO 2: Suma de Subconjunto (Subset Sum)
 * =============================================
 * Nivel: Principiante-Intermedio (⭐⭐)
 *
 * Descripción:
 *   Dado un conjunto de enteros y un valor objetivo, determinar si existe
 *   un subconjunto cuya suma sea igual al objetivo. Imprimir dicho subconjunto.
 *
 * Concepto de Backtracking aplicado:
 *   En cada paso decidimos si INCLUIR o EXCLUIR el elemento actual.
 *   Si incluirlo supera el objetivo → backtrack (no vale la pena seguir).
 *   Si excluirlo y no hay más elementos → backtrack.
 *
 * Complejidad: O(2^n) en el peor caso.
 */
import java.util.ArrayList;
import java.util.List;

public class Ejercicio02_SumaSubconjunto {

    /**
     * Función principal de backtracking.
     *
     * @param numeros   El conjunto de números disponibles.
     * @param objetivo  La suma objetivo a alcanzar.
     * @param indice    El índice actual que estamos considerando.
     * @param sumaActual La suma acumulada hasta ahora.
     * @param subconjunto Los elementos seleccionados hasta ahora.
     * @return true si se encontró un subconjunto válido.
     */
    public static boolean encontrarSubconjunto(int[] numeros, int objetivo,
                                               int indice, int sumaActual,
                                               List<Integer> subconjunto) {
        // Caso base exitoso: suma alcanzada
        if (sumaActual == objetivo) {
            System.out.println("✅ Subconjunto encontrado: " + subconjunto);
            return true;
        }

        // Caso base fallido: índice fuera de rango o suma excedida
        if (indice == numeros.length || sumaActual > objetivo) {
            return false; // Backtrack
        }

        // Decisión 1: INCLUIR el elemento actual
        subconjunto.add(numeros[indice]);
        System.out.println("  Probando incluir " + numeros[indice] + " → suma parcial: " + (sumaActual + numeros[indice]));

        if (encontrarSubconjunto(numeros, objetivo, indice + 1,
                sumaActual + numeros[indice], subconjunto)) {
            return true;
        }

        // Backtrack: EXCLUIR el elemento actual (deshacer la inclusión)
        subconjunto.remove(subconjunto.size() - 1);
        System.out.println("  Backtrack: excluir " + numeros[indice]);

        // Decisión 2: EXCLUIR el elemento actual
        return encontrarSubconjunto(numeros, objetivo, indice + 1,
                sumaActual, subconjunto);
    }

    public static void main(String[] args) {
        int[] numeros = {3, 1, 4, 2, 5};
        int objetivo = 9;

        System.out.println("=== Ejercicio 2: Suma de Subconjunto ===");
        System.out.println("Conjunto: [3, 1, 4, 2, 5]");
        System.out.println("Objetivo: " + objetivo);
        System.out.println("\nExplorando decisiones:");

        List<Integer> subconjunto = new ArrayList<>();
        boolean encontrado = encontrarSubconjunto(numeros, objetivo, 0, 0, subconjunto);

        if (!encontrado) {
            System.out.println("❌ No existe subconjunto con suma = " + objetivo);
        }

        // Caso sin solución
        System.out.println("\n--- Caso sin solución ---");
        int[] numeros2 = {2, 4, 6};
        int objetivo2 = 5;
        System.out.println("Conjunto: [2, 4, 6], Objetivo: " + objetivo2);
        boolean encontrado2 = encontrarSubconjunto(numeros2, objetivo2, 0, 0, new ArrayList<>());
        if (!encontrado2) {
            System.out.println("❌ No existe subconjunto con suma = " + objetivo2);
        }
    }
}
