package arboles_ejercicios;

/**
 * Ejercicio 10: Invertir el Árbol
 * 
 * Intercambiar todos los hijos izquierdos y derechos.
 * 
 * Antes:        Después:
 *       10          10
 *      /  \        /  \
 *     5    20    20    5
 */
public class Ejercicio10_InvertirArbol {

    /**
     * Invierte el árbol intercambiando los hijos de cada nodo.
     * Recorre todo el árbol y en cada nodo hace un "swap" de izquierdo y derecho.
     */
    public static NodoEntero invertir(NodoEntero nodo) {
        // Caso base: nodo nulo, nada que invertir
        if (nodo == null) {
            return null;
        }

        // Invertimos recursivamente los subárboles
        NodoEntero izqInvertido = invertir(nodo.izquierdo);
        NodoEntero derInvertido = invertir(nodo.derecho);

        // Intercambiamos (swap) los hijos
        nodo.izquierdo = derInvertido;
        nodo.derecho = izqInvertido;

        return nodo;
    }

    /** Método auxiliar para imprimir inorden */
    private static void imprimirInorden(NodoEntero nodo) {
        if (nodo != null) {
            imprimirInorden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            imprimirInorden(nodo.derecho);
        }
    }

    public static void main(String[] args) {
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3, 7, 15, 30);

        System.out.print("Antes de invertir (inorden):  ");
        imprimirInorden(raiz);
        System.out.println();
        // Salida: 3 5 7 10 15 20 30

        invertir(raiz);

        System.out.print("Después de invertir (inorden): ");
        imprimirInorden(raiz);
        System.out.println();
        // Salida: 30 20 15 10 7 5 3
    }
}
