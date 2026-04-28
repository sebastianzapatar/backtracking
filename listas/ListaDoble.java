package listas;

// Clase que implementa una Lista Doblemente Ligada genérica
public class ListaDoble<T> {
    private NodoDoble<T> cabeza; // Puntero al primer nodo de la lista
    private NodoDoble<T> cola;   // Puntero al último nodo de la lista (optimiza inserciones al final)
    private int tamano;          // Variable para llevar el control de la cantidad de elementos

    // Constructor que inicializa la lista como vacía
    public ListaDoble() {
        this.cabeza = null; // Al inicio, no hay primer nodo
        this.cola = null;   // Al inicio, no hay último nodo
        this.tamano = 0;    // El tamaño inicial es cero
    }

    // Método para insertar un elemento al inicio de la lista
    public void insertarInicio(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato); // Crea un nuevo nodo con el dato
        if (cabeza == null) {
            // Si la lista está vacía, la cabeza y la cola apuntan al nuevo nodo
            cabeza = cola = nuevo; 
        } else {
            // Si no está vacía, el siguiente del nuevo será la actual cabeza
            nuevo.next = cabeza; 
            // El anterior de la actual cabeza será el nuevo nodo
            cabeza.prev = nuevo; 
            // Actualizamos la cabeza para que sea el nuevo nodo
            cabeza = nuevo; 
        }
        tamano++; // Aumentamos el tamaño de la lista
    }

    // Método para insertar un elemento al final de la lista
    public void insertarFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato); // Crea un nuevo nodo con el dato
        if (cola == null) {
            // Si la lista está vacía, la cabeza y la cola apuntan al nuevo nodo
            cabeza = cola = nuevo; 
        } else {
            // Si no está vacía, el siguiente de la cola actual será el nuevo nodo
            cola.next = nuevo; 
            // El anterior del nuevo será la cola actual
            nuevo.prev = cola; 
            // Actualizamos la cola para que sea el nuevo nodo
            cola = nuevo; 
        }
        tamano++; // Aumentamos el tamaño de la lista
    }

    // Método para insertar un elemento en una posición específica (índice basado en 0)
    public void insertarEnPosicion(int posicion, T dato) {
        // Validación: si la posición es menor a 0 o mayor al tamaño, es inválida
        if (posicion < 0 || posicion > tamano) {
            System.out.println("Error: Posición " + posicion + " fuera de rango.");
            return;
        }

        // Si la posición es 0, usamos el método insertarInicio
        if (posicion == 0) {
            insertarInicio(dato);
            return;
        }

        // Si la posición es igual al tamaño, usamos el método insertarFinal
        if (posicion == tamano) {
            insertarFinal(dato);
            return;
        }

        // Caso general: insertar en el medio
        NodoDoble<T> nuevo = new NodoDoble<>(dato); // Creamos el nuevo nodo
        NodoDoble<T> actual = cabeza;               // Nodo iterador empezando por la cabeza

        // Recorremos la lista hasta llegar al nodo que actualmente ocupa esa posición
        for (int i = 0; i < posicion; i++) {
            actual = actual.next; 
        }

        // Ajustamos los punteros para insertar el nuevo nodo antes del 'actual'
        nuevo.prev = actual.prev; // El anterior del nuevo apunta al que era anterior del actual
        nuevo.next = actual;      // El siguiente del nuevo apunta al actual
        actual.prev.next = nuevo; // El siguiente del anterior del actual ahora apunta al nuevo
        actual.prev = nuevo;      // El anterior del actual ahora apunta al nuevo
        
        tamano++; // Aumentamos el tamaño de la lista
    }

    // Método para buscar si un elemento existe en la lista
    public boolean buscar(T dato) {
        NodoDoble<T> actual = cabeza; // Empezamos a buscar desde la cabeza
        
        // Recorremos la lista hasta llegar al final (null)
        while (actual != null) {
            // Si el dato del nodo actual coincide con el buscado
            if (actual.dato.equals(dato)) {
                return true; // Retornamos verdadero, el elemento fue encontrado
            }
            actual = actual.next; // Avanzamos al siguiente nodo
        }
        return false; // Retornamos falso si llegamos al final sin encontrarlo
    }

    // Método para eliminar la primera aparición de un dato en la lista
    public boolean eliminar(T dato) {
        NodoDoble<T> actual = cabeza; // Empezamos desde la cabeza
        
        // Recorremos la lista
        while (actual != null) {
            // Si encontramos el nodo con el dato a eliminar
            if (actual.dato.equals(dato)) {
                // Caso 1: El nodo a eliminar es la cabeza
                if (actual == cabeza) {
                    cabeza = actual.next; // La nueva cabeza es el siguiente nodo
                    if (cabeza != null) {
                        cabeza.prev = null; // Desenlazamos el anterior de la nueva cabeza
                    } else {
                        cola = null; // Si la lista quedó vacía, la cola también es null
                    }
                } 
                // Caso 2: El nodo a eliminar es la cola
                else if (actual == cola) {
                    cola = actual.prev; // La nueva cola es el nodo anterior
                    cola.next = null;   // Desenlazamos el siguiente de la nueva cola
                } 
                // Caso 3: El nodo a eliminar está en el medio de la lista
                else {
                    actual.prev.next = actual.next; // El siguiente del nodo anterior se salta al actual
                    actual.next.prev = actual.prev; // El anterior del nodo siguiente se salta al actual
                }
                tamano--; // Disminuimos el tamaño de la lista
                return true; // Elemento eliminado exitosamente
            }
            actual = actual.next; // Avanzamos si no es el nodo buscado
        }
        return false; // Retornamos falso si no encontramos el elemento
    }

    // Método para imprimir la lista de principio a fin
    public void recorrerAdelante() {
        NodoDoble<T> actual = cabeza; // Comenzamos en la cabeza
        System.out.print("Lista (Adelante): ");
        // Recorremos hasta que no haya más nodos
        while (actual != null) {
            System.out.print(actual.dato + " <-> "); // Imprimimos el dato
            actual = actual.next; // Avanzamos al siguiente
        }
        System.out.println("NULL"); // Indicamos el final de la lista
    }

    // Método para imprimir la lista de fin a principio
    public void recorrerAtras() {
        NodoDoble<T> actual = cola; // Comenzamos en la cola
        System.out.print("Lista (Atrás): ");
        // Recorremos hacia atrás hasta llegar a null (antes de la cabeza)
        while (actual != null) {
            System.out.print(actual.dato + " <-> "); // Imprimimos el dato
            actual = actual.prev; // Retrocedemos al nodo anterior
        }
        System.out.println("NULL"); // Indicamos el inicio de la lista
    }

    // Método para obtener el tamaño actual de la lista
    public int getTamano() {
        return tamano; // Retorna la cantidad de elementos insertados
    }
}
