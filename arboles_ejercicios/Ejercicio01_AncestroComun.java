package arboles_ejercicios;

/**
 * Ejercicio 1: Ancestro Común Más Cercano (LCA - Lowest Common Ancestor)
 * 
 * Dado un árbol binario y dos nodos, encontrar el ancestro común más cercano.
 * 
 * Ejemplo:
 *           10
 *         /    \
 *        5      20
 *       / \    /  \
 *      3   7  15   30
 *         /
 *        6
 * 
 * Entrada: 6 y 30 → Salida: 10
 * 
 * Conceptos: Recursión, búsqueda en árbol, retorno de nodos.
 * 
 * Algoritmo:
 *   1. Si el nodo actual es null o coincide con alguno de los dos valores, lo retornamos.
 *   2. Buscamos recursivamente en el subárbol izquierdo y derecho.
 *   3. Si ambos subárboles retornan un resultado (no null), el nodo actual es el LCA.
 *   4. Si solo un lado retorna resultado, ese es el LCA (ambos nodos están en ese lado).
 */
public class Ejercicio01_AncestroComun {

    /**
     * Encuentra el Ancestro Común Más Cercano (LCA) de dos valores en el árbol.
     * 
     * La idea es recorrer el árbol de forma recursiva. Cuando encontramos uno de los
     * valores, lo retornamos hacia arriba. El primer nodo que recibe respuestas
     * no nulas de AMBOS lados es el ancestro común.
     * 
     * @param nodo El nodo actual en la recursión.
     * @param valor1 Primer valor a buscar.
     * @param valor2 Segundo valor a buscar.
     * @return El nodo que es ancestro común, o null si no se encontró.
     */
    public static NodoEntero encontrarLCA(NodoEntero nodo, int valor1, int valor2) {
        // Caso base 1: Si llegamos a un nodo nulo, no hay nada que retornar
        if (nodo == null) {
            return null;
        }

        // Caso base 2: Si el nodo actual tiene uno de los valores buscados,
        // retornamos este nodo (es candidato a ser el LCA o a propagarse hacia arriba)
        if (nodo.valor == valor1 || nodo.valor == valor2) {
            return nodo;
        }

        // Buscamos recursivamente en el subárbol IZQUIERDO
        NodoEntero resultadoIzquierdo = encontrarLCA(nodo.izquierdo, valor1, valor2);

        // Buscamos recursivamente en el subárbol DERECHO
        NodoEntero resultadoDerecho = encontrarLCA(nodo.derecho, valor1, valor2);

        // Si AMBOS lados retornaron un resultado (no null), significa que uno de los
        // valores está en el lado izquierdo y el otro en el derecho.
        // Por lo tanto, el nodo actual ES el Ancestro Común Más Cercano.
        if (resultadoIzquierdo != null && resultadoDerecho != null) {
            return nodo;
        }

        // Si solo un lado retornó resultado, lo propagamos hacia arriba.
        // Esto significa que ambos valores están en el mismo subárbol.
        return (resultadoIzquierdo != null) ? resultadoIzquierdo : resultadoDerecho;
    }

    public static void main(String[] args) {
        /*
         * Construimos el árbol del enunciado:
         *           10
         *         /    \
         *        5      20
         *       / \    /  \
         *      3   7  15   30
         *         /
         *        6
         */
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3, 7, 15, 30, 6);

        // Caso 1: LCA de 6 y 30 → esperamos 10
        NodoEntero lca1 = encontrarLCA(raiz, 6, 30);
        System.out.println("LCA de 6 y 30: " + (lca1 != null ? lca1.valor : "No encontrado"));
        // Salida: 10

        // Caso 2: LCA de 6 y 7 → esperamos 7
        NodoEntero lca2 = encontrarLCA(raiz, 6, 7);
        System.out.println("LCA de 6 y 7: " + (lca2 != null ? lca2.valor : "No encontrado"));
        // Salida: 7

        // Caso 3: LCA de 3 y 7 → esperamos 5
        NodoEntero lca3 = encontrarLCA(raiz, 3, 7);
        System.out.println("LCA de 3 y 7: " + (lca3 != null ? lca3.valor : "No encontrado"));
        // Salida: 5

        // Caso 4: LCA de 15 y 30 → esperamos 20
        NodoEntero lca4 = encontrarLCA(raiz, 15, 30);
        System.out.println("LCA de 15 y 30: " + (lca4 != null ? lca4.valor : "No encontrado"));
        // Salida: 20
    }
}
