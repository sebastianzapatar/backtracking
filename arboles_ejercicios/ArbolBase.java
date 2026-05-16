package arboles_ejercicios;

/**
 * Clase utilitaria que provee métodos para construir árboles de prueba.
 * Facilita la creación de árboles para cada ejercicio sin repetir código.
 */
public class ArbolBase {

    /**
     * Inserta un valor en un Árbol Binario de Búsqueda (BST).
     * Usa las reglas del BST: menores a la izquierda, mayores a la derecha.
     *
     * @param raiz La raíz actual del árbol.
     * @param valor El valor a insertar.
     * @return La raíz del árbol con el nuevo valor insertado.
     */
    public static NodoEntero insertarBST(NodoEntero raiz, int valor) {
        // Caso base: encontramos un espacio vacío, creamos el nodo
        if (raiz == null) {
            return new NodoEntero(valor);
        }

        // Si el valor es menor, vamos a la izquierda
        if (valor < raiz.valor) {
            raiz.izquierdo = insertarBST(raiz.izquierdo, valor);
        }
        // Si el valor es mayor, vamos a la derecha
        else if (valor > raiz.valor) {
            raiz.derecho = insertarBST(raiz.derecho, valor);
        }
        // Si es igual, no insertamos (sin duplicados en BST)

        return raiz;
    }

    /**
     * Construye un BST a partir de un arreglo de valores.
     * Los valores se insertan en el orden del arreglo.
     *
     * @param valores Arreglo con los valores a insertar.
     * @return La raíz del BST construido.
     */
    public static NodoEntero construirBST(int... valores) {
        NodoEntero raiz = null;
        for (int v : valores) {
            raiz = insertarBST(raiz, v);
        }
        return raiz;
    }
}
