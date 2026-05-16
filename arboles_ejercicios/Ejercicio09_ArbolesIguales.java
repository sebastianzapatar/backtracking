package arboles_ejercicios;

/**
 * Ejercicio 9: Verificar si Dos Árboles son Iguales
 * 
 * Determinar si dos árboles tienen exactamente la misma estructura y valores.
 * 
 * Ejemplo:
 *   Árbol A:        Árbol B:
 *       10              10
 *      /  \            /  \
 *     5   20          5   20
 * 
 * Salida: true
 */
public class Ejercicio09_ArbolesIguales {

    /**
     * Compara dos árboles recursivamente.
     * Dos árboles son iguales si:
     *   - Ambas raíces son null, O
     *   - Tienen el mismo valor Y sus subárboles izq/der también son iguales.
     */
    public static boolean sonIguales(NodoEntero nodo1, NodoEntero nodo2) {
        // Si ambos son null, son iguales (dos árboles vacíos)
        if (nodo1 == null && nodo2 == null) {
            return true;
        }

        // Si solo uno es null, son diferentes (estructura distinta)
        if (nodo1 == null || nodo2 == null) {
            return false;
        }

        // Comparamos: mismo valor + subárbol izquierdo igual + subárbol derecho igual
        return (nodo1.valor == nodo2.valor)
                && sonIguales(nodo1.izquierdo, nodo2.izquierdo)
                && sonIguales(nodo1.derecho, nodo2.derecho);
    }

    public static void main(String[] args) {
        NodoEntero arbolA = ArbolBase.construirBST(10, 5, 20);
        NodoEntero arbolB = ArbolBase.construirBST(10, 5, 20);
        NodoEntero arbolC = ArbolBase.construirBST(10, 5, 30);

        System.out.println("A == B: " + sonIguales(arbolA, arbolB)); // true
        System.out.println("A == C: " + sonIguales(arbolA, arbolC)); // false
    }
}
