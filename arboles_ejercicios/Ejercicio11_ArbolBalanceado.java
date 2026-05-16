package arboles_ejercicios;

/**
 * Ejercicio 11: Validar Árbol Balanceado
 * 
 * Determinar si el árbol está balanceado.
 * Definición: La diferencia de altura entre subárbol izquierdo y derecho no supera 1.
 * 
 * Balanceado:          No balanceado:
 *       10                  10
 *      /  \                /
 *     5    20             5
 *                        /
 *                       3
 */
public class Ejercicio11_ArbolBalanceado {

    /**
     * Verifica si el árbol está balanceado.
     * 
     * Usa un enfoque optimizado: retorna -1 si detecta desbalance,
     * o la altura real si está balanceado. Así evitamos recalcular alturas.
     */
    public static boolean esBalanceado(NodoEntero nodo) {
        return verificarAltura(nodo) != -1;
    }

    /**
     * Retorna la altura del subárbol si está balanceado, o -1 si no lo está.
     * Este enfoque es O(n) porque recorre cada nodo una sola vez.
     */
    private static int verificarAltura(NodoEntero nodo) {
        if (nodo == null) {
            return 0;
        }

        // Verificamos el subárbol izquierdo
        int alturaIzq = verificarAltura(nodo.izquierdo);
        if (alturaIzq == -1) return -1; // Ya desbalanceado

        // Verificamos el subárbol derecho
        int alturaDer = verificarAltura(nodo.derecho);
        if (alturaDer == -1) return -1; // Ya desbalanceado

        // Si la diferencia de alturas es mayor a 1, no está balanceado
        if (Math.abs(alturaIzq - alturaDer) > 1) {
            return -1;
        }

        // Retornamos la altura real
        return 1 + Math.max(alturaIzq, alturaDer);
    }

    public static void main(String[] args) {
        // Árbol balanceado
        NodoEntero balanceado = ArbolBase.construirBST(10, 5, 20);
        System.out.println("Balanceado: " + esBalanceado(balanceado)); // true

        // Árbol NO balanceado (línea hacia la izquierda)
        NodoEntero noBalanceado = ArbolBase.construirBST(10, 5, 3);
        System.out.println("No balanceado: " + esBalanceado(noBalanceado)); // false
    }
}
