package arboles_ejercicios;

/**
 * Ejercicio 2: Altura del Árbol
 * 
 * Encontrar la altura de un árbol binario.
 * La altura es el número máximo de niveles desde la raíz hasta una hoja.
 * 
 * Ejemplo:
 *         10
 *        /  \
 *       5    20
 *      /
 *     3
 * 
 * Salida: 3
 * 
 * Conceptos: Recursión simple, máximo entre ramas.
 * 
 * Algoritmo:
 *   1. Si el nodo es null, la altura es 0.
 *   2. Calculamos recursivamente la altura del subárbol izquierdo y derecho.
 *   3. La altura del nodo actual = 1 + máximo(alturaIzquierda, alturaDerecha).
 */
public class Ejercicio02_AlturaArbol {

    /**
     * Calcula la altura del árbol de forma recursiva.
     * 
     * Para cada nodo, la altura es 1 (el nodo actual) + la mayor altura
     * entre su subárbol izquierdo y derecho.
     * 
     * @param nodo El nodo actual.
     * @return La altura del subárbol con raíz en este nodo.
     */
    public static int calcularAltura(NodoEntero nodo) {
        // Caso base: si el nodo es null, no contribuye a la altura
        if (nodo == null) {
            return 0;
        }

        // Calculamos la altura de cada subárbol recursivamente
        int alturaIzquierda = calcularAltura(nodo.izquierdo);
        int alturaDerecha = calcularAltura(nodo.derecho);

        // La altura total es 1 (este nodo) + el mayor de los dos subárboles
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    public static void main(String[] args) {
        /*
         * Árbol:
         *         10
         *        /  \
         *       5    20
         *      /
         *     3
         */
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3);

        int altura = calcularAltura(raiz);
        System.out.println("Altura del árbol: " + altura);
        // Salida esperada: 3

        // Árbol de un solo nodo
        NodoEntero soloRaiz = new NodoEntero(42);
        System.out.println("Altura de un solo nodo: " + calcularAltura(soloRaiz));
        // Salida esperada: 1

        // Árbol nulo
        System.out.println("Altura de árbol vacío: " + calcularAltura(null));
        // Salida esperada: 0
    }
}
