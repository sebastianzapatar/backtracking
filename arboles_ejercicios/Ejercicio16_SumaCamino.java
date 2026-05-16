package arboles_ejercicios;

/**
 * Ejercicio 16: Verificar si Existe un Camino con Suma X
 * 
 * Determinar si existe un camino desde raíz hasta hoja cuya suma sea X.
 * 
 * Ejemplo:
 *         10
 *        /  \
 *       5    20
 *      / \
 *     3   7
 * 
 * Buscar suma 18 → Camino: 10 + 5 + 3 = 18 → true
 */
public class Ejercicio16_SumaCamino {

    /**
     * Verifica si existe un camino raíz→hoja cuya suma sea igual a sumaObjetivo.
     * 
     * Estrategia: En cada paso restamos el valor del nodo actual.
     * Cuando llegamos a una hoja, verificamos si el residuo es 0.
     */
    public static boolean existeCaminoConSuma(NodoEntero nodo, int sumaObjetivo) {
        if (nodo == null) return false;

        // Restamos el valor del nodo actual
        int residuo = sumaObjetivo - nodo.valor;

        // Si es hoja y el residuo es 0, ¡encontramos el camino!
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return residuo == 0;
        }

        // Buscamos en ambos subárboles con el residuo
        return existeCaminoConSuma(nodo.izquierdo, residuo) ||
               existeCaminoConSuma(nodo.derecho, residuo);
    }

    public static void main(String[] args) {
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3, 7);

        System.out.println("Suma 18 (10+5+3): " + existeCaminoConSuma(raiz, 18));   // true
        System.out.println("Suma 22 (10+5+7): " + existeCaminoConSuma(raiz, 22));   // true
        System.out.println("Suma 30 (10+20):  " + existeCaminoConSuma(raiz, 30));   // true
        System.out.println("Suma 99:          " + existeCaminoConSuma(raiz, 99));   // false
    }
}
