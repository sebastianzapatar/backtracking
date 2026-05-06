package arboles;

/**
 * Clase que representa un Nodo en el Árbol Binario de Búsqueda.
 * Utiliza genéricos (T extends Comparable<T>) para poder almacenar cualquier tipo de dato
 * que pueda ser comparado (necesario para saber si es mayor o menor).
 */
class Nodo<T extends Comparable<T>> {
    T valor;           // El valor o dato que almacena el nodo
    Nodo<T> izquierdo; // Referencia al hijo izquierdo (para valores menores)
    Nodo<T> derecho;   // Referencia al hijo derecho (para valores mayores)

    /**
     * Constructor del nodo.
     * @param valor El dato que se va a guardar en este nodo.
     */
    public Nodo(T valor) {
        this.valor = valor;    // Asignamos el valor recibido al atributo 'valor' del nodo
        this.izquierdo = null; // Inicialmente no tiene hijo izquierdo (es null)
        this.derecho = null;   // Inicialmente no tiene hijo derecho (es null)
    }
}

/**
 * Clase principal que representa el Árbol Binario de Búsqueda (BST por sus siglas en inglés).
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> {
    
    // Referencia al nodo principal o raíz del árbol
    private Nodo<T> raiz;

    /**
     * Constructor del Árbol Binario de Búsqueda.
     * Inicializa la raíz como nula, es decir, el árbol empieza completamente vacío.
     */
    public ArbolBinarioBusqueda() {
        this.raiz = null; // El árbol comienza sin ningún elemento
    }

    /**
     * Método público para insertar un nuevo valor en el árbol.
     * Llama a un método privado recursivo para encontrar la posición correcta.
     * @param valor El valor a insertar.
     */
    public void insertar(T valor) {
        // Llamamos al método recursivo y el resultado lo asignamos a la raíz.
        // Si la raíz era nula, ahora apuntará al nuevo nodo.
        this.raiz =  

    /**
     * Método privado recursivo que busca la posición correcta según las reglas del árbol
     * (menores a la izquierda, mayores a la derecha) y crea el nuevo nodo.
     * 
     * @param nodoActual El nodo que se está evaluando en este paso de la recursión.
     * @param valor El valor que queremos insertar.
     * @return El nodo actual para ir reconstruyendo o uniendo las ramas del árbol en la recursión.
     */
    private Nodo<T> insertarRecursivo(Nodo<T> nodoActual, T valor) {
        // 1. CASO BASE: Si llegamos a un lugar vacío (nulo), significa que encontramos
        // el lugar perfecto para insertar. Creamos el nodo y lo retornamos.
        if (nodoActual == null) {
            return new Nodo<>(valor); 
        }

        // 2. Comparamos el valor a insertar con el valor del nodo actual.
        // El método compareTo() devuelve un número menor a 0 si 'valor' es menor, 
        // un número mayor a 0 si es mayor, y 0 si son iguales.
        if (valor.compareTo(nodoActual.valor) < 0) {
            // REGLA 1: Si el valor es MENOR, avanzamos hacia la rama IZQUIERDA.
            // Hacemos la llamada recursiva pasando el hijo izquierdo.
            nodoActual.izquierdo = insertarRecursivo(nodoActual.izquierdo, valor);
            
        } else if (valor.compareTo(nodoActual.valor) > 0) {
            // REGLA 2: Si el valor es MAYOR, avanzamos hacia la rama DERECHA.
            // Hacemos la llamada recursiva pasando el hijo derecho.
            nodoActual.derecho = insertarRecursivo(nodoActual.derecho, valor);
        }
        
        // Nota: Si el valor es IGUAL (compareTo == 0), no hacemos nada,
        // ya que en un Árbol Binario de Búsqueda tradicional no se permiten duplicados.

        // 3. Retornamos el nodo actual (ya con sus hijos actualizados)
        // para que las conexiones anteriores no se pierdan.
        return nodoActual;
    }

    /**
     * Método público para realizar el recorrido INORDEN (Izquierda -> Raíz -> Derecha).
     * En un Árbol Binario de Búsqueda, ¡este recorrido visita los nodos en orden ascendente!
     */
    public void recorridoInorden() {
        System.out.print("Inorden: ");
        inordenRecursivo(this.raiz); // Iniciamos el proceso recursivo desde la raíz
        System.out.println();        // Salto de línea al terminar para que se vea bien
    }

    /**
     * Método privado recursivo para el recorrido Inorden.
     */
    private void inordenRecursivo(Nodo<T> nodo) {
        // Solo procesamos el nodo si no es nulo
        if (nodo != null) {
            // 1. Visitamos TODO el subárbol IZQUIERDO recursivamente
            inordenRecursivo(nodo.izquierdo);
            
            // 2. Visitamos (imprimimos) el valor del nodo ACTUAL (la raíz en este paso)
            System.out.print(nodo.valor + " ");
            
            // 3. Visitamos TODO el subárbol DERECHO recursivamente
            inordenRecursivo(nodo.derecho);
        }
    }

    /**
     * Método público para realizar el recorrido PREORDEN (Raíz -> Izquierda -> Derecha).
     * Es muy útil para hacer una copia exacta de la estructura del árbol.
     */
    public void recorridoPreorden() {
        System.out.print("Preorden: ");
        preordenRecursivo(this.raiz); // Iniciamos el proceso recursivo desde la raíz
        System.out.println();         // Salto de línea al terminar
    }

    /**
     * Método privado recursivo para el recorrido Preorden.
     */
    private void preordenRecursivo(Nodo<T> nodo) {
        // Solo procesamos el nodo si no es nulo
        if (nodo != null) {
            // 1. Visitamos (imprimimos) PRIMERO el nodo ACTUAL (la raíz)
            System.out.print(nodo.valor + " ");
            
            // 2. Visitamos TODO el subárbol IZQUIERDO recursivamente
            preordenRecursivo(nodo.izquierdo);
            
            // 3. Visitamos TODO el subárbol DERECHO recursivamente
            preordenRecursivo(nodo.derecho);
        }
    }

    /**
     * Método público para realizar el recorrido POSTORDEN (Izquierda -> Derecha -> Raíz).
     * Muy útil para eliminar nodos del árbol, ya que procesa los hijos antes que los padres.
     */
    public void recorridoPostorden() {
        System.out.print("Postorden: ");
        postordenRecursivo(this.raiz); // Iniciamos el proceso recursivo desde la raíz
        System.out.println();          // Salto de línea al terminar
    }

    /**
     * Método privado recursivo para el recorrido Postorden.
     */
    private void postordenRecursivo(Nodo<T> nodo) {
        // Solo procesamos el nodo si no es nulo
        if (nodo != null) {
            // 1. Visitamos TODO el subárbol IZQUIERDO recursivamente
            postordenRecursivo(nodo.izquierdo);
            
            // 2. Visitamos TODO el subárbol DERECHO recursivamente
            postordenRecursivo(nodo.derecho);
            
            // 3. Finalmente, visitamos (imprimimos) el nodo ACTUAL (la raíz)
            System.out.print(nodo.valor + " ");
        }
    }

    // =========================================================================
    // MÉTODO MAIN PARA PROBAR NUESTRO ÁRBOL BINARIO DE BÚSQUEDA
    // =========================================================================
    public static void main(String[] args) {
        // Creamos una instancia de nuestro árbol, indicando que almacenará números enteros (Integer)
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();

        /*
         * Vamos a crear el siguiente árbol paso a paso:
         * 
         *        50
         *      /    \
         *    30      70
         *   /  \    /  \
         * 20   40  60   80
         */
        
        arbol.insertar(50); // Primer valor insertado, se convierte en la RAÍZ.
        arbol.insertar(30); // 30 es < 50, se va a la IZQUIERDA de 50.
        arbol.insertar(20); // 20 es < 50 y < 30, se va a la IZQUIERDA de 30.
        arbol.insertar(40); // 40 es < 50 pero > 30, se va a la DERECHA de 30.
        arbol.insertar(70); // 70 es > 50, se va a la DERECHA de 50.
        arbol.insertar(60); // 60 es > 50 pero < 70, se va a la IZQUIERDA de 70.
        arbol.insertar(80); // 80 es > 50 y > 70, se va a la DERECHA de 70.

        System.out.println("--- Recorridos del Árbol Binario de Búsqueda ---");
        
        // El recorrido inorden siempre imprimirá los números ordenados de menor a mayor
        // Salida esperada: 20 30 40 50 60 70 80
        arbol.recorridoInorden();   
        
        // El recorrido preorden empieza por la raíz
        // Salida esperada: 50 30 20 40 70 60 80
        arbol.recorridoPreorden();  
        
        // El recorrido postorden termina en la raíz
        // Salida esperada: 20 40 30 60 80 70 50
        arbol.recorridoPostorden(); 
    }
}
