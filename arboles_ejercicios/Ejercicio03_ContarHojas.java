package arboles_ejercicios;

/**
 * Ejercicio 3: Contar Nodos Hoja
 * 
 * Contar cuántos nodos hoja tiene un árbol.
 * Nodo hoja: nodo sin hijos (izquierdo y derecho son null).
 * 
 * Ejemplo:
 *         10
 *        /  \
 *       5    20
 *      / \     \
 *     3   7     30
 * 
 * Hojas: 3, 7, 30
 * Salida: 3
 * 
 * Conceptos: Condición base, recursión acumulativa.
 * 
 * Algoritmo:
 *   1. Si el nodo es null, retornar 0.
 *   2. Si el nodo no tiene hijos (es hoja), retornar 1.
 *   3. Si tiene hijos, retornar la suma de hojas del subárbol izquierdo + derecho.
 */
public class Ejercicio03_ContarHojas {

    /**
     * Cuenta los nodos hoja del árbol recursivamente.
     * 
     * Un nodo hoja es aquel que no tiene hijo izquierdo ni derecho.
     * La estrategia es: si encuentro una hoja, cuento 1; si no, sumo
     * las hojas de ambos subárboles.
     * 
     * @param nodo El nodo actual.
     * @return Cantidad de nodos hoja en el subárbol.
     */
    public static int contarHojas(NodoEntero nodo) {
        // Caso base: nodo nulo no es hoja ni tiene hojas
        if (nodo == null) {
            return 0;
        }

        // Si no tiene hijo izquierdo NI derecho, ¡es una hoja!
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }

        // Si tiene al menos un hijo, contamos hojas en ambos subárboles
        return contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho);
    }

    public static void main(String[] args) {
        /*
         * Árbol:
         *         10
         *        /  \
         *       5    20
         *      / \     \
         *     3   7     30
         */
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3, 7, 30);

        int hojas = contarHojas(raiz);
        System.out.println("Cantidad de nodos hoja: " + hojas);
        // Salida esperada: 3 (hojas: 3, 7, 30)

        // Árbol de un solo nodo (la raíz misma es hoja)
        NodoEntero soloRaiz = new NodoEntero(42);
        System.out.println("Hojas en un solo nodo: " + contarHojas(soloRaiz));
        // Salida esperada: 1
    }
}
