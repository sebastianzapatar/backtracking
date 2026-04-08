/**
 * EJERCICIO 9: Recorrido del Caballo de Ajedrez (Knight's Tour)
 * ==============================================================
 * Nivel: Muy Avanzado (⭐⭐⭐⭐⭐)
 *
 * Descripción:
 *   Dado un tablero N×N de ajedrez y una posición inicial del caballo,
 *   encontrar un recorrido tal que el caballo visite CADA celda exactamente
 *   UNA VEZ. Esto se llama "Recorrido del Caballo" o "Knight's Tour".
 *
 * Concepto de Backtracking aplicado:
 *   + En cada paso, el caballo puede moverse en hasta 8 posiciones diferentes.
 *   + Probamos cada movimiento válido. Si llega a un estado sin solución → backtrack.
 *   + OPTIMIZACIÓN: Regla de Warnsdorff — elegir el movimiento con MENOS
 *     continuaciones disponibles (heurística para reducir backtracking).
 *
 * Complejidad: O(8^(N²)) en el peor caso, pero con Warnsdorff es casi O(n²) en la práctica.
 */
import java.util.Arrays;

public class Ejercicio09_RecorridoCaballo {

    static int N = 6; // Tamaño del tablero (6×6 para demostración rápida)

    // Los 8 movimientos posibles del caballo en ajedrez
    static int[] movX = {2, 1, -1, -2, -2, -1,  1,  2};
    static int[] movY = {1, 2,  2,  1, -1, -2, -2, -1};

    static int[][] tablero = new int[N][N];
    static int pasosBacktrack = 0;

    /**
     * Verifica si el movimiento (x, y) es válido.
     * Condiciones: dentro del tablero y la celda no ha sido visitada.
     */
    private static boolean esMovimientoValido(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N && tablero[x][y] == -1;
    }

    /**
     * OPTIMIZACIÓN - Heurística de Warnsdorff:
     * Cuenta cuántos movimientos futuros son posibles desde (x, y).
     * Elegir el de menor grado reduce enormemente el backtracking.
     */
    private static int contarMovimientosDesde(int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + movX[i];
            int ny = y + movY[i];
            if (esMovimientoValido(nx, ny)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Función de backtracking para el recorrido del caballo.
     * Usa heurística de Warnsdorff para ordenar los movimientos.
     *
     * @param x    Fila actual del caballo.
     * @param y    Columna actual del caballo.
     * @param paso El número de paso actual (1-indexado).
     * @return true si se completó el recorrido.
     */
    public static boolean resolverRecorrido(int x, int y, int paso) {
        // Caso base: todos los pasos completados (N*N pasos en total)
        if (paso == N * N + 1) {
            return true; // ✅ Recorrido completo
        }

        // Generar y ordenar los 8 posibles movimientos por la heurística de Warnsdorff
        int[] ordenMovs = ordenarMovimientos(x, y);

        for (int i = 0; i < 8; i++) {
            int nx = x + movX[ordenMovs[i]];
            int ny = y + movY[ordenMovs[i]];

            if (esMovimientoValido(nx, ny)) {
                // Realizar el movimiento
                tablero[nx][ny] = paso;
                System.out.println("  Paso " + paso + ": (" + x + "," + y + ") → (" + nx + "," + ny + ")");

                // Recurrir para el siguiente movimiento
                if (resolverRecorrido(nx, ny, paso + 1)) {
                    return true; // Éxito
                }

                // Backtrack: deshacer el movimiento
                tablero[nx][ny] = -1;
                pasosBacktrack++;
                System.out.println("  ↩ Backtrack en paso " + paso + " desde (" + nx + "," + ny + ")");
            }
        }

        return false; // No hay movimiento válido desde aquí
    }

    /**
     * Ordena los 8 índices de movimiento usando la heurística de Warnsdorff.
     * (Ordenamiento por grado de accesibilidad ascendente)
     */
    private static int[] ordenarMovimientos(int x, int y) {
        Integer[] grados = new Integer[8];
        int[] indices = new int[8];

        for (int i = 0; i < 8; i++) {
            int nx = x + movX[i];
            int ny = y + movY[i];
            if (esMovimientoValido(nx, ny)) {
                grados[i] = contarMovimientosDesde(nx, ny);
            } else {
                grados[i] = Integer.MAX_VALUE; // Movimiento inválido → al final
            }
            indices[i] = i;
        }

        // Ordenar índices por grado ascendente (menor grado primero — Warnsdorff)
        Integer[] boxedIndices = new Integer[8];
        for (int i = 0; i < 8; i++) boxedIndices[i] = i;
        Arrays.sort(boxedIndices, (a, b) -> grados[a] - grados[b]);
        for (int i = 0; i < 8; i++) indices[i] = boxedIndices[i];

        return indices;
    }

    /**
     * Imprime el tablero con los números de paso.
     */
    private static void imprimirTablero(int[][] tab) {
        System.out.println("\n📋 Tablero (número = orden de visita):");
        System.out.print("    ");
        for (int j = 0; j < N; j++) System.out.printf("%-4d", j);
        System.out.println();
        System.out.println("  " + "+----".repeat(N) + "+");

        for (int i = 0; i < N; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < N; j++) {
                if (tab[i][j] == -1) {
                    System.out.printf("%-4s", "·");
                } else {
                    System.out.printf("%-4d", tab[i][j]);
                }
            }
            System.out.println("|");
        }
        System.out.println("  " + "+----".repeat(N) + "+");
    }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 9: Recorrido del Caballo ===");
        System.out.println("Tablero: " + N + "×" + N);
        System.out.println("Optimización: Heurística de Warnsdorff");
        System.out.println();

        // Inicializar el tablero con -1 (no visitado)
        for (int[] fila : tablero) Arrays.fill(fila, -1);

        // Posición inicial: (0, 0)
        int inicioX = 0, inicioY = 0;
        tablero[inicioX][inicioY] = 1; // Paso 1

        System.out.println("Inicio: (" + inicioX + "," + inicioY + ")");
        System.out.println("\nMovimientos del caballo (con traza):");

        long tiempoInicio = System.currentTimeMillis();
        boolean resuelto = resolverRecorrido(inicioX, inicioY, 2);
        long tiempoFin = System.currentTimeMillis();

        if (resuelto) {
            imprimirTablero(tablero);
            System.out.println("\n✅ ¡Recorrido completo encontrado!");
            System.out.println("📊 Estadísticas:");
            System.out.println("  → Celdas visitadas: " + (N * N));
            System.out.println("  → Pasos de backtrack: " + pasosBacktrack);
            System.out.println("  → Tiempo: " + (tiempoFin - tiempoInicio) + "ms");
        } else {
            System.out.println("❌ No se encontró un recorrido completo.");
        }
    }
}
