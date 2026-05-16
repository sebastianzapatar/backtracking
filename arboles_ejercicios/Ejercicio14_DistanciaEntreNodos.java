package arboles_ejercicios;

/**
 * Ejercicio 14: Distancia entre Dos Nodos
 * 
 * Calcular cuántas aristas hay entre dos nodos.
 * Usa el LCA (Ejercicio 1) como base.
 * 
 * Ejemplo:
 *         10
 *        /  \
 *       5    20
 *      / \
 *     3   7
 * 
 * Distancia entre 3 y 20:
 *   Camino: 3 → 5 → 10 → 20
 *   Salida: 3 (aristas)
 * 
 * Fórmula: dist(a,b) = dist(raíz,a) + dist(raíz,b) - 2*dist(raíz,LCA(a,b))
 * O equivalente: dist(LCA,a) + dist(LCA,b)
 */
public class Ejercicio14_DistanciaEntreNodos {

    /**
     * Calcula la distancia (en aristas) entre dos nodos.
     * 
     * Estrategia:
     *   1. Encontrar el LCA de ambos nodos.
     *   2. Calcular la distancia del LCA a cada nodo.
     *   3. Sumar ambas distancias.
     */
    public static int calcularDistancia(NodoEntero raiz, int valor1, int valor2) {
        // Paso 1: Encontrar el ancestro común más cercano
        NodoEntero lca = Ejercicio01_AncestroComun.encontrarLCA(raiz, valor1, valor2);

        if (lca == null) return -1;

        // Paso 2: Calcular distancia del LCA a cada nodo
        int dist1 = distanciaDesdeNodo(lca, valor1, 0);
        int dist2 = distanciaDesdeNodo(lca, valor2, 0);

        // Paso 3: La distancia total es la suma
        return dist1 + dist2;
    }

    /**
     * Calcula la distancia (en aristas) desde un nodo hasta un valor buscado.
     */
    private static int distanciaDesdeNodo(NodoEntero nodo, int valorBuscado, int distanciaActual) {
        if (nodo == null) return -1;

        if (nodo.valor == valorBuscado) return distanciaActual;

        int izq = distanciaDesdeNodo(nodo.izquierdo, valorBuscado, distanciaActual + 1);
        if (izq != -1) return izq;

        return distanciaDesdeNodo(nodo.derecho, valorBuscado, distanciaActual + 1);
    }

    public static void main(String[] args) {
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3, 7);

        System.out.println("Distancia entre 3 y 20: " + calcularDistancia(raiz, 3, 20));
        // Salida: 3

        System.out.println("Distancia entre 3 y 7: " + calcularDistancia(raiz, 3, 7));
        // Salida: 2

        System.out.println("Distancia entre 5 y 20: " + calcularDistancia(raiz, 5, 20));
        // Salida: 2
    }
}
