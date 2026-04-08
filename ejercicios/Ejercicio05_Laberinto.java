/**
 * EJERCICIO 5: Resolución de Laberinto
 * =====================================
 * Nivel: Intermedio-Avanzado (⭐⭐⭐⭐)
 *
 * Descripción:
 *   Dado un laberinto representado como una matriz de 0s y 1s, encontrar
 *   un camino desde la posición inicial (0,0) hasta la salida (n-1, m-1).
 *   - 0 = celda libre (puede pasar)
 *   - 1 = muro (no puede pasar)
 *
 * Concepto de Backtracking aplicado:
 *   Desde la posición actual, intentamos movernos en 4 direcciones (↑↓←→).
 *   Si un movimiento es inválido (muro, fuera de bounds, ya visitado) → backtrack.
 *   Marcamos las celdas visitadas para evitar ciclos.
 *
 * Complejidad: O(4^(n*m)) en el peor caso (aunque poda reduce mucho esto).
 */
public class Ejercicio05_Laberinto {

    // Marcadores en la matriz de solución
    static final int LIBRE = 0;
    static final int CAMINO = 2;
    static final int VISITADO = 3;

    // Laberinto: 0=libre, 1=muro
    static int[][] laberinto = {
        {0, 0, 1, 0, 0},
        {1, 0, 1, 0, 1},
        {0, 0, 0, 0, 1},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 1, 0}
    };

    // Matriz de solución (se construye durante el backtracking)
    static int[][] solucion = new int[5][5];

    // Movimientos posibles: abajo, derecha, arriba, izquierda
    static int[] filaDelta = {1, 0, -1, 0};
    static int[] colDelta  = {0, 1, 0, -1};
    static String[] nombreMovimiento = {"↓ Abajo", "→ Derecha", "↑ Arriba", "← Izquierda"};

    /**
     * Función de backtracking para resolver el laberinto.
     *
     * @param fila La fila actual.
     * @param col  La columna actual.
     * @param n    Número de filas.
     * @param m    Número de columnas.
     * @return true si se encontró un camino hacia la salida.
     */
    public static boolean resolver(int fila, int col, int n, int m) {
        // Caso base: ¿Llegamos a la salida?
        if (fila == n - 1 && col == m - 1) {
            solucion[fila][col] = CAMINO;
            System.out.println("  ✅ ¡Salida alcanzada en (" + fila + "," + col + ")!");
            return true;
        }

        // ¿Es válida la posición actual?
        if (!esValida(fila, col, n, m)) {
            return false; // Backtrack
        }

        // Marcar la celda actual como parte del camino
        solucion[fila][col] = CAMINO;
        System.out.println("  Visitando (" + fila + "," + col + ")");

        // Intentar los 4 movimientos posibles
        for (int dir = 0; dir < 4; dir++) {
            int nuevaFila = fila + filaDelta[dir];
            int nuevaCol  = col  + colDelta[dir];

            System.out.println("    Intentando " + nombreMovimiento[dir]
                    + " → (" + nuevaFila + "," + nuevaCol + ")");

            if (resolver(nuevaFila, nuevaCol, n, m)) {
                return true; // ✅ Camino encontrado
            }
        }

        // Backtrack: ningún movimiento funciona desde aquí
        solucion[fila][col] = VISITADO; // Marcar como muerto (sin salida)
        System.out.println("  ↩ Backtrack en (" + fila + "," + col + ") — sin salida");
        return false;
    }

    /**
     * Verifica si una celda es válida para moverse.
     */
    private static boolean esValida(int fila, int col, int n, int m) {
        return fila >= 0 && fila < n &&
               col >= 0 && col < m &&
               laberinto[fila][col] == LIBRE &&
               solucion[fila][col] == LIBRE; // No revisitada
    }

    /**
     * Imprime el laberinto y la solución en consola.
     */
    private static void imprimirLaberinto(int[][] mat, String titulo) {
        System.out.println("\n" + titulo);
        System.out.println("  +---+---+---+---+---+");
        for (int i = 0; i < mat.length; i++) {
            System.out.print("  | ");
            for (int j = 0; j < mat[i].length; j++) {
                String celda;
                if (mat == laberinto) {
                    celda = mat[i][j] == 1 ? "█" : " ";
                } else {
                    celda = mat[i][j] == CAMINO ? "●" : mat[i][j] == VISITADO ? "✗" : " ";
                }
                System.out.print(celda + " | ");
            }
            System.out.println("\n  +---+---+---+---+---+");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 5: Resolución de Laberinto ===");
        System.out.println("Inicio: (0,0) | Salida: (4,4)");
        System.out.println("█=Muro | ●=Camino | ✗=Explorado sin éxito");

        imprimirLaberinto(laberinto, "Laberinto:");

        System.out.println("\nIniciando backtracking...");
        boolean resuelto = resolver(0, 0, 5, 5);

        if (resuelto) {
            imprimirLaberinto(solucion, "\n✅ Solución encontrada:");
        } else {
            System.out.println("\n❌ El laberinto no tiene solución.");
        }
    }
}
