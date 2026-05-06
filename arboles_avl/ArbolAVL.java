package arboles_avl;

/**
 * Clase que representa un Nodo en el Árbol AVL.
 * A diferencia del BST normal, este nodo guarda su altura para calcular el factor de balance.
 */
class NodoAVL<T extends Comparable<T>> {
    T valor;               // El dato almacenado en el nodo
    int altura;            // La altura actual de este nodo en el árbol
    NodoAVL<T> izquierdo;  // Referencia al hijo izquierdo (valores menores)
    NodoAVL<T> derecho;    // Referencia al hijo derecho (valores mayores)

    /**
     * Constructor del nodo AVL.
     */
    public NodoAVL(T valor) {
        this.valor = valor;    // Asignamos el valor al nodo
        this.altura = 1;       // Los nodos nuevos se insertan como hojas, por lo que su altura inicial es 1
        this.izquierdo = null; // Aún no tiene hijo izquierdo
        this.derecho = null;   // Aún no tiene hijo derecho
    }
}

/**
 * Clase principal que representa el Árbol AVL (Árbol Binario de Búsqueda Auto-Balanceable).
 */
public class ArbolAVL<T extends Comparable<T>> {
    
    private NodoAVL<T> raiz; // La raíz del árbol

    /**
     * Constructor del Árbol AVL. Inicia vacío.
     */
    public ArbolAVL() {
        this.raiz = null;
    }

    /**
     * Método auxiliar para obtener la altura de un nodo de forma segura.
     * Si el nodo es null, su altura es 0.
     */
    private int obtenerAltura(NodoAVL<T> nodo) {
        if (nodo == null) {
            return 0; // Un nodo inexistente tiene altura 0
        }
        return nodo.altura; // Retorna la altura almacenada en el nodo
    }

    /**
     * Método auxiliar para calcular el factor de balance de un nodo.
     * El balance = Altura(Hijo Izquierdo) - Altura(Hijo Derecho).
     * Si el balance está entre -1 y 1, el nodo está balanceado.
     */
    private int obtenerBalance(NodoAVL<T> nodo) {
        if (nodo == null) {
            return 0; // Un nodo inexistente está perfectamente balanceado (0)
        }
        // Restamos la altura de la izquierda menos la derecha
        return obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
    }

    /**
     * Método auxiliar para obtener el número máximo entre dos enteros.
     * Útil para recalcular la altura del nodo padre después de insertar o rotar.
     */
    private int max(int a, int b) {
        return (a > b) ? a : b; // Operador ternario: si 'a' es mayor, retorna 'a', sino retorna 'b'
    }

    /**
     * ROTACIÓN SIMPLE A LA DERECHA (Right Rotation).
     * Se usa cuando el subárbol izquierdo es demasiado alto (desbalance "en línea" hacia la izquierda).
     *
     *       y                               x
     *      / \                            /   \
     *     x   T3      ------->           T1    y
     *    / \                                  / \
     *   T1  T2                               T2  T3
     */
    private NodoAVL<T> rotacionDerecha(NodoAVL<T> y) {
        NodoAVL<T> x = y.izquierdo;  // 'x' será la nueva raíz de este subárbol
        NodoAVL<T> T2 = x.derecho;   // 'T2' es el subárbol derecho de 'x' que cambiará de padre

        // Realizamos la rotación intercambiando punteros
        x.derecho = y;       // 'y' baja a ser el hijo derecho de 'x'
        y.izquierdo = T2;    // 'T2' pasa a ser el hijo izquierdo de 'y'

        // Actualizamos las alturas, primero el hijo ('y') y luego el nuevo padre ('x')
        y.altura = max(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho)) + 1;
        x.altura = max(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho)) + 1;

        // Retornamos la nueva raíz de este subárbol (que ahora es 'x')
        return x;
    }

    /**
     * ROTACIÓN SIMPLE A LA IZQUIERDA (Left Rotation).
     * Se usa cuando el subárbol derecho es demasiado alto (desbalance "en línea" hacia la derecha).
     *
     *       x                               y
     *      / \                            /   \
     *     T1  y       ------->           x    T3
     *        / \                        / \
     *       T2  T3                     T1  T2
     */
    private NodoAVL<T> rotacionIzquierda(NodoAVL<T> x) {
        NodoAVL<T> y = x.derecho;    // 'y' será la nueva raíz de este subárbol
        NodoAVL<T> T2 = y.izquierdo; // 'T2' es el subárbol izquierdo de 'y' que cambiará de padre

        // Realizamos la rotación intercambiando punteros
        y.izquierdo = x;     // 'x' baja a ser el hijo izquierdo de 'y'
        x.derecho = T2;      // 'T2' pasa a ser el hijo derecho de 'x'

        // Actualizamos las alturas, primero el hijo ('x') y luego el nuevo padre ('y')
        x.altura = max(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho)) + 1;
        y.altura = max(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho)) + 1;

        // Retornamos la nueva raíz de este subárbol (que ahora es 'y')
        return y;
    }

    /**
     * Método público para insertar un valor en el árbol AVL.
     */
    public void insertar(T valor) {
        // Llamamos al método recursivo para insertar y balancear, guardando el resultado en la raíz global
        this.raiz = insertarRecursivo(this.raiz, valor);
    }

    /**
     * Método recursivo que inserta un valor y luego balancea el árbol en su camino de regreso ("backtracking").
     */
    private NodoAVL<T> insertarRecursivo(NodoAVL<T> nodo, T valor) {
        // PASO 1: Inserción normal de un Árbol Binario de Búsqueda (BST)
        if (nodo == null) {
            return new NodoAVL<>(valor); // Encontramos el lugar vacío, creamos el nodo y lo devolvemos
        }

        // Decidimos hacia qué rama ir según si es menor o mayor
        if (valor.compareTo(nodo.valor) < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, valor); // Vamos por la izquierda
        } else if (valor.compareTo(nodo.valor) > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, valor);     // Vamos por la derecha
        } else {
            // Si el valor ya existe (compareTo == 0), no se permiten duplicados en este diseño
            return nodo; 
        }

        // PASO 2: Actualizar la altura del nodo actual o "ancestro" tras la inserción
        // Su altura es la altura máxima entre sus dos hijos, más 1 (él mismo)
        nodo.altura = 1 + max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));

        // PASO 3: Obtener el factor de balance del nodo actual para verificar si se desbalanceó
        int balance = obtenerBalance(nodo);

        // PASO 4: Reestructuración si el nodo se desbalanceó (balance > 1 o balance < -1). 
        // Tenemos 4 casos de desbalanceo posibles:

        // Caso 1: Izquierda Izquierda (El nuevo nodo se insertó a la izquierda del hijo izquierdo)
        // Se soluciona con una Rotación Derecha simple.
        if (balance > 1 && valor.compareTo(nodo.izquierdo.valor) < 0) {
            return rotacionDerecha(nodo);
        }

        // Caso 2: Derecha Derecha (El nuevo nodo se insertó a la derecha del hijo derecho)
        // Se soluciona con una Rotación Izquierda simple.
        if (balance < -1 && valor.compareTo(nodo.derecho.valor) > 0) {
            return rotacionIzquierda(nodo);
        }

        // Caso 3: Izquierda Derecha (El nuevo nodo se insertó a la derecha del hijo izquierdo)
        // Forma un "zig-zag". Se soluciona con Rotación Izquierda en el hijo y luego Rotación Derecha en el padre.
        if (balance > 1 && valor.compareTo(nodo.izquierdo.valor) > 0) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo); // Enderezamos el zig-zag
            return rotacionDerecha(nodo);                       // Aplicamos Rotación Derecha global
        }

        // Caso 4: Derecha Izquierda (El nuevo nodo se insertó a la izquierda del hijo derecho)
        // Forma un "zig-zag". Se soluciona con Rotación Derecha en el hijo y luego Rotación Izquierda en el padre.
        if (balance < -1 && valor.compareTo(nodo.derecho.valor) < 0) {
            nodo.derecho = rotacionDerecha(nodo.derecho); // Enderezamos el zig-zag
            return rotacionIzquierda(nodo);               // Aplicamos Rotación Izquierda global
        }

        // Si no está desbalanceado (el balance está entre -1 y 1), simplemente retornamos el nodo
        return nodo;
    }

    /**
     * Método para imprimir el recorrido Pre-Orden (Raíz, Izquierda, Derecha).
     * Ideal para visualizar cómo quedó la jerarquía (quién quedó en la raíz después del balanceo).
     */
    public void recorridoPreorden() {
        System.out.print("Preorden AVL: ");
        preordenRecursivo(this.raiz);
        System.out.println();
    }

    private void preordenRecursivo(NodoAVL<T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " "); // Imprimimos Raíz
            preordenRecursivo(nodo.izquierdo);  // Visitamos subárbol izquierdo
            preordenRecursivo(nodo.derecho);    // Visitamos subárbol derecho
        }
    }

    // =========================================================================
    // MÉTODO MAIN PARA PROBAR EL ÁRBOL AVL Y SU AUTO-BALANCEO
    // =========================================================================
    public static void main(String[] args) {
        ArbolAVL<Integer> arbol = new ArbolAVL<>();

        /*
         * Vamos a intentar insertar datos en orden ascendente (10, 20, 30, 40, 50, 25).
         * En un ABB normal esto crearía una línea recta hacia la derecha (peor caso O(n)).
         * ¡Pero en un AVL se auto-balanceará!
         */
        
        System.out.println("Insertando 10...");
        arbol.insertar(10);
        
        System.out.println("Insertando 20...");
        arbol.insertar(20);
        
        System.out.println("Insertando 30... (¡Aquí ocurre una rotación izquierda para balancear 10-20-30!)");
        arbol.insertar(30);
        
        System.out.println("Insertando 40...");
        arbol.insertar(40);
        
        System.out.println("Insertando 50... (¡Aquí ocurre otra rotación izquierda!)");
        arbol.insertar(50);
        
        System.out.println("Insertando 25... (¡Aquí ocurre rotación doble Derecha-Izquierda!)");
        arbol.insertar(25);

        System.out.println("\nEstructura final comprobada con recorrido Preorden:");
        // Salida esperada: 30 20 10 25 40 50 (el 30 "subió" hasta convertirse en la raíz global por el balanceo)
        arbol.recorridoPreorden();
    }
}
