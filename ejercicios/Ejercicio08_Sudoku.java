/**
 * EJERCICIO 8: Resolución del Sudoku
 * ====================================
 * Nivel: Avanzado (⭐⭐⭐⭐⭐)
 *
 * Descripción:
 *   Resolver un Sudoku estándar 9×9. Las reglas son:
 *   - Cada fila debe contener los dígitos 1-9 sin repetición.
 *   - Cada columna debe contener los dígitos 1-9 sin repetición.
 *   - Cada una de las 9 subcuadrículas 3×3 debe contener los dígitos 1-9.
 *
 * Concepto de Backtracking aplicado:
 *   Recorremos el tablero celda por celda. En cada celda vacía (0), probamos
 *   los dígitos 1-9. Si colocar un dígito viola alguna regla → backtrack.
 *   La función de PODA es la verificación de las 3 restricciones simultáneas.
 *
 * Complejidad: O(9^m) donde m = número de celdas vacías. En la práctica,
 *              el backtracking con podas lo hace muy eficiente.
 */
public class Ejercicio08_Sudoku {

    // 0 = celda vacía
    static int[][] tablero = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    static int intentos = 0; // Contador de intentos para demostrar potencia del backtracking

    /**
     * Verifica si 'num' puede colocarse en la posición (fila, col).
     * Verifica las 3 restricciones del Sudoku simultáneamente.
     */
    private static boolean esValido(int[][] tab, int fila, int col, int num) {
        // 1. Verificar la fila
        for (int j = 0; j < 9; j++) {
            if (tab[fila][j] == num) return false;
        }

        // 2. Verificar la columna
        for (int i = 0; i < 9; i++) {
            if (tab[i][col] == num) return false;
        }

        // 3. Verificar la subcuadrícula 3×3
        int inicioFila = (fila / 3) * 3;
        int inicioCol  = (col / 3) * 3;
        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioCol; j < inicioCol + 3; j++) {
                if (tab[i][j] == num) return false;
            }
        }

        return true; // Es válido
    }

    /**
     * Encuentra la siguiente celda vacía en el tablero.
     * Retorna {fila, col} o {-1, -1} si no hay celdas vacías.
     */
    private static int[] encontrarCeldaVacia(int[][] tab) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tab[i][j] == 0) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1}; // No hay celdas vacías
    }

    /**
     * Función principal de backtracking para resolver el Sudoku.
     *
     * @param tab El tablero de Sudoku.
     * @return true si se resolvió el Sudoku.
     */
    public static boolean resolver(int[][] tab) {
        // Encontrar la siguiente celda vacía
        int[] celda = encontrarCeldaVacia(tab);
        int fila = celda[0];
        int col  = celda[1];

        // Caso base: no hay celdas vacías → Sudoku resuelto
        if (fila == -1) {
            return true;
        }

        // Intentar colocar cada dígito del 1 al 9
        for (int num = 1; num <= 9; num++) {
            intentos++;

            if (esValido(tab, fila, col, num)) {
                // Colocar el número
                tab[fila][col] = num;

                // Recurrir para la siguiente celda
                if (resolver(tab)) {
                    return true; // ✅ Solución encontrada
                }

                // Backtrack: el número no llevó a una solución
                tab[fila][col] = 0;
            }
        }

        return false; // Ningún número funciona aquí → backtrack
    }

    /**
     * Imprime el tablero de Sudoku de forma formateada.
     */
    private static void imprimirTablero(int[][] tab, String titulo) {
        System.out.println("\n" + titulo);
        System.out.println("  ┌───────┬───────┬───────┐");
        for (int i = 0; i < 9; i++) {
            System.out.print("  │ ");
            for (int j = 0; j < 9; j++) {
                String val = tab[i][j] == 0 ? "·" : String.valueOf(tab[i][j]);
                System.out.print(val + " ");
                if (j == 2 || j == 5) System.out.print("│ ");
            }
            System.out.println("│");
            if (i == 2 || i == 5) {
                System.out.println("  ├───────┼───────┼───────┤");
            }
        }
        System.out.println("  └───────┴───────┴───────┘");
    }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 8: Resolución de Sudoku ===");

        imprimirTablero(tablero, "📋 Sudoku inicial (· = vacío):");

        System.out.println("\n⚙ Resolviendo con backtracking...");
        long inicio = System.currentTimeMillis();
        boolean resuelto = resolver(tablero);
        long tiempoMs = System.currentTimeMillis() - inicio;

        if (resuelto) {
            imprimirTablero(tablero, "✅ Sudoku resuelto:");
            System.out.println("\n📊 Estadísticas:");
            System.out.println("  → Intentos de colocación: " + intentos);
            System.out.println("  → Tiempo de resolución: " + tiempoMs + "ms");
        } else {
            System.out.println("❌ El Sudoku no tiene solución.");
        }
    }
}
