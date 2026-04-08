/**
 * EJERCICIO 7: Coloración de Grafos (Graph Coloring)
 * ====================================================
 * Nivel: Avanzado (⭐⭐⭐⭐)
 *
 * Descripción:
 *   Dado un grafo no dirigido y un número de colores m, asignar colores a
 *   cada vértice de forma que ningún par de vértices adyacentes compartan
 *   el mismo color. Encontrar si es posible y mostrar una asignación válida.
 *
 * Concepto de Backtracking aplicado:
 *   Asignamos colores vértice por vértice. Para cada vértice, probamos cada
 *   color disponible. Si genera conflicto con un vecino → backtrack.
 *   PODA: verificar restricciones antes de continuar.
 *
 * Complejidad: O(m^V) donde V es el número de vértices.
 */
public class Ejercicio07_ColoracionGrafos {

    static int V; // Número de vértices
    static int m; // Número de colores disponibles
    static int[][] grafo; // Matriz de adyacencia
    static int[] color; // color[i] = color asignado al vértice i (0 = sin color)
    static String[] nombreColor = {"", "🔴 Rojo", "🟢 Verde", "🔵 Azul", "🟡 Amarillo", "🟣 Violeta"};

    /**
     * Verifica si es válido asignar el color 'c' al vértice 'v'.
     */
    private static boolean esSeguro(int v, int c) {
        for (int u = 0; u < V; u++) {
            // Si (v, u) son vecinos y u tiene el mismo color → conflicto
            if (grafo[v][u] == 1 && color[u] == c) {
                return false;
            }
        }
        return true;
    }

    /**
     * Función de backtracking para colorear el grafo.
     *
     * @param v El vértice actual que queremos colorear.
     * @return true si se encontró una coloración válida.
     */
    public static boolean colorear(int v) {
        // Caso base: todos los vértices han sido coloreados
        if (v == V) {
            return true;
        }

        System.out.println("  Coloreando vértice " + v + ":");

        // Intentar cada color para el vértice v
        for (int c = 1; c <= m; c++) {
            System.out.print("    Probando " + nombreColor[c] + " en vértice " + v + " → ");

            if (esSeguro(v, c)) {
                color[v] = c; // Asignar color
                System.out.println("✔ Válido");

                // Recurrir para el siguiente vértice
                if (colorear(v + 1)) {
                    return true; // Solución encontrada
                }

                // Backtrack: desasignar el color
                System.out.println("    ↩ Backtrack en vértice " + v + ", quitando " + nombreColor[c]);
                color[v] = 0;
            } else {
                System.out.println("✗ Conflicto con vecino");
            }
        }

        return false; // Ningún color funciona → backtrack más arriba
    }

    /**
     * Imprime el grafo y la coloración resultante.
     */
    private static void imprimirColoracion() {
        System.out.println("\n✅ Coloración válida encontrada:");
        System.out.println("  Vértice | Color");
        System.out.println("  --------|-------");
        for (int i = 0; i < V; i++) {
            System.out.println("     " + i + "    | " + nombreColor[color[i]]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 7: Coloración de Grafos ===");

        /*
         * Grafo de ejemplo (Grafo de Petersen simplificado):
         *   0 -- 1 -- 2 -- 3
         *   |              |
         *   4 ----------- 3
         *
         * Representado como:
         *     0   1   2   3   4
         * 0 [ 0,  1,  0,  0,  1 ]
         * 1 [ 1,  0,  1,  0,  0 ]
         * 2 [ 0,  1,  0,  1,  0 ]
         * 3 [ 0,  0,  1,  0,  1 ]
         * 4 [ 1,  0,  0,  1,  0 ]
         */
        V = 5;
        m = 3; // Intentar con 3 colores

        grafo = new int[][] {
            {0, 1, 0, 0, 1},
            {1, 0, 1, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 1, 0, 1},
            {1, 0, 0, 1, 0}
        };

        color = new int[V];

        System.out.println("Vértices: " + V);
        System.out.println("Colores disponibles: " + m);
        System.out.println("Aristas: 0-1, 1-2, 2-3, 3-4, 0-4 (pentágono)");
        System.out.println("\n--- Adyacencia ---");
        System.out.println("  0-1, 0-4, 1-2, 2-3, 3-4");
        System.out.println("\nIniciando backtracking:");

        boolean resuelto = colorear(0);

        if (resuelto) {
            imprimirColoracion();
        } else {
            System.out.println("❌ No es posible colorear el grafo con " + m + " colores.");
        }

        // Caso imposible: grafo completo K4 con solo 2 colores
        System.out.println("\n--- Caso imposible: K4 con 2 colores ---");
        V = 4;
        m = 2;
        grafo = new int[][] {
            {0, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 0}
        };
        color = new int[V];
        boolean posible = colorear(0);
        if (!posible) {
            System.out.println("\n❌ No es posible colorear K4 con solo 2 colores (necesita 4).");
        }
    }
}
