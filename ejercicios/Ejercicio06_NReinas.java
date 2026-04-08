/**
 * EJERCICIO 6: Problema de las N-Reinas
 * =======================================
 * Nivel: Avanzado (⭐⭐⭐⭐)
 *
 * Descripción:
 *   Colocar N reinas en un tablero de ajedrez N×N de forma que ninguna
 *   ataque a otra. Una reina ataca en filas, columnas y diagonales.
 *
 * Concepto de Backtracking aplicado:
 *   Colocamos reinas fila por fila. En cada fila probamos todas las columnas.
 *   Si la columna actual provoca un conflicto → backtrack.
 *   La función de PODA es: verificar conflictos antes de continuar.
 *
 * Complejidad: O(n!) con podas significativas en la práctica.
 */
public class Ejercicio06_NReinas {

    static int n; // Tamaño del tablero
    static int[] columnas; // columnas[i] = columna donde está la reina en la fila i
    static int totalSoluciones = 0;

    /**
     * Verifica si es seguro colocar una reina en (fila, col).
     *
     * @param fila La fila donde queremos colocar la reina.
     * @param col  La columna donde queremos colocar la reina.
     * @return true si es seguro colocarla.
     */
    private static boolean esSeguiro(int fila, int col) {
        for (int f = 0; f < fila; f++) {
            // Verificar misma columna
            if (columnas[f] == col) {
                return false;
            }
            // Verificar diagonales (|fila - f| == |col - columnas[f]|)
            if (Math.abs(fila - f) == Math.abs(col - columnas[f])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Función de backtracking para colocar reinas fila por fila.
     *
     * @param fila La fila actual donde intentamos colocar una reina.
     */
    public static void resolver(int fila) {
        // Caso base: todas las reinas fueron colocadas exitosamente
        if (fila == n) {
            totalSoluciones++;
            System.out.println("\n✅ Solución #" + totalSoluciones + ":");
            imprimirTablero();
            return;
        }

        // Probar colocar la reina en cada columna de la fila actual
        for (int col = 0; col < n; col++) {
            System.out.println("  Fila " + fila + ": probando columna " + col + "...");

            if (esSeguiro(fila, col)) {
                // Colocar la reina
                columnas[fila] = col;
                System.out.println("  ✔ Reina colocada en (" + fila + "," + col + ")");

                // Recurrir para la siguiente fila
                resolver(fila + 1);

                // Backtrack: quitar la reina y probar siguiente columna
                columnas[fila] = -1;
                System.out.println("  ↩ Backtrack: quitando reina de fila " + fila);
            } else {
                System.out.println("  ✗ Conflicto en (" + fila + "," + col + ") → PODA");
            }
        }
    }

    /**
     * Imprime el tablero con las reinas colocadas.
     */
    private static void imprimirTablero() {
        StringBuilder sb = new StringBuilder();
        sb.append("  +");
        for (int i = 0; i < n; i++) sb.append("---+");
        sb.append("\n");

        for (int fila = 0; fila < n; fila++) {
            sb.append("  | ");
            for (int col = 0; col < n; col++) {
                sb.append(columnas[fila] == col ? "♛" : "·");
                sb.append(" | ");
            }
            sb.append("\n  +");
            for (int i = 0; i < n; i++) sb.append("---+");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // Resolver para N=4 (con trazabilidad)
        System.out.println("=== Ejercicio 6: N-Reinas (N=4) ===");
        n = 4;
        columnas = new int[n];
        java.util.Arrays.fill(columnas, -1);

        System.out.println("Tablero: " + n + "×" + n);
        System.out.println("♛ = Reina | · = Celda vacía");
        System.out.println("\nIniciando backtracking:");

        resolver(0);

        System.out.println("\n📊 Total de soluciones para N=" + n + ": " + totalSoluciones);

        // Contar soluciones para N=8 (sin imprimir traza)
        System.out.println("\n--- Contando soluciones para N=8 ---");
        n = 8;
        columnas = new int[n];
        java.util.Arrays.fill(columnas, -1);
        totalSoluciones = 0;

        resolverSilencioso(0);
        System.out.println("✅ Total de soluciones para N=8: " + totalSoluciones + " (esperado: 92)");
    }

    // Versión silenciosa sin prints de traza, para N grande
    private static void resolverSilencioso(int fila) {
        if (fila == n) {
            totalSoluciones++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (esSeguiro(fila, col)) {
                columnas[fila] = col;
                resolverSilencioso(fila + 1);
                columnas[fila] = -1;
            }
        }
    }
}
