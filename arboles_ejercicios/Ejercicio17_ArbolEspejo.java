package arboles_ejercicios;

/**
 * Ejercicio 17: Árbol Espejo
 * 
 * Determinar si dos árboles son reflejo uno del otro.
 * 
 * A:        B:
 *     10       10
 *    /  \     /  \
 *   5   20  20    5
 * 
 * Salida: true
 */
public class Ejercicio17_ArbolEspejo {

    /**
     * Verifica si dos árboles son espejo (reflejo) uno del otro.
     * 
     * Dos árboles son espejo si:
     *   - Ambos son null, O
     *   - Tienen el mismo valor en la raíz Y
     *   - El hijo izquierdo de A es espejo del hijo derecho de B y viceversa.
     */
    public static boolean sonEspejo(NodoEntero nodo1, NodoEntero nodo2) {
        // Ambos vacíos son espejo
        if (nodo1 == null && nodo2 == null) return true;

        // Solo uno vacío no son espejo
        if (nodo1 == null || nodo2 == null) return false;

        // Mismo valor + cruce: izq con der y der con izq
        return (nodo1.valor == nodo2.valor)
                && sonEspejo(nodo1.izquierdo, nodo2.derecho)
                && sonEspejo(nodo1.derecho, nodo2.izquierdo);
    }

    public static void main(String[] args) {
        // Árbol A:  10 → izq:5, der:20
        NodoEntero arbolA = ArbolBase.construirBST(10, 5, 20);

        // Árbol B (espejo): 10 → izq:20, der:5
        NodoEntero arbolB = new NodoEntero(10);
        arbolB.izquierdo = new NodoEntero(20);
        arbolB.derecho = new NodoEntero(5);

        System.out.println("Son espejo A y B: " + sonEspejo(arbolA, arbolB)); // true

        // Árbol C (no espejo): 10 → izq:5, der:20
        NodoEntero arbolC = ArbolBase.construirBST(10, 5, 20);
        System.out.println("Son espejo A y C: " + sonEspejo(arbolA, arbolC)); // false
    }
}
