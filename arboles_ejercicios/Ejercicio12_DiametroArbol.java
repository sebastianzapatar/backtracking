package arboles_ejercicios;

/**
 * Ejercicio 12: Diámetro del Árbol
 * 
 * Encontrar el camino más largo entre dos nodos (en número de nodos).
 * 
 * Ejemplo:
 *         10
 *        /  \
 *       5    20
 *      /
 *     3
 * 
 * Camino más largo: 3 → 5 → 10 → 20
 * Salida: 4 (nodos en el camino)
 */
public class Ejercicio12_DiametroArbol {

    // Variable de clase para almacenar el diámetro máximo encontrado
    static int diametroMaximo;

    /**
     * Calcula el diámetro del árbol.
     * El diámetro es el camino más largo entre dos nodos cualesquiera.
     * No necesariamente pasa por la raíz.
     */
    public static int calcularDiametro(NodoEntero raiz) {
        diametroMaximo = 0;
        calcularAltura(raiz);
        return diametroMaximo;
    }

    /**
     * Calcula la altura y al mismo tiempo actualiza el diámetro.
     * Para cada nodo, el camino que pasa por él tiene longitud:
     *   alturaIzq + alturaDer + 1 (en nodos)
     */
    private static int calcularAltura(NodoEntero nodo) {
        if (nodo == null) {
            return 0;
        }

        int alturaIzq = calcularAltura(nodo.izquierdo);
        int alturaDer = calcularAltura(nodo.derecho);

        // El camino que pasa por este nodo tiene esta cantidad de nodos
        int caminoActual = alturaIzq + alturaDer + 1;

        // Actualizamos el diámetro si encontramos un camino más largo
        diametroMaximo = Math.max(diametroMaximo, caminoActual);

        // Retornamos la altura de este subárbol
        return 1 + Math.max(alturaIzq, alturaDer);
    }

    public static void main(String[] args) {
        NodoEntero raiz = ArbolBase.construirBST(10, 5, 20, 3);

        System.out.println("Diámetro del árbol: " + calcularDiametro(raiz));
        // Salida: 4 (camino 3 → 5 → 10 → 20)
    }
}
