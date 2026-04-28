package listas;

// Clase que implementa una Lista Circular Doblemente Ligada genérica
public class ListaCircularDoble<T> {
    private NodoDoble<T> cabeza; // Puntero al nodo principal (cabeza) de la lista circular
    private int tamano;          // Mantiene el control de cuántos elementos tiene la lista

    // Constructor que inicializa la lista circular vacía
    public ListaCircularDoble() {
        this.cabeza = null; // Al iniciar, no hay cabeza
        this.tamano = 0;    // El tamaño es cero
    }

    // Método para insertar un elemento al final (antes de la cabeza)
    public void insertar(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato); // Creamos el nuevo nodo
        
        // Si la lista está vacía
        if (cabeza == null) {
            cabeza = nuevo;         // El nuevo nodo es la cabeza
            cabeza.next = cabeza;   // Su siguiente es él mismo (circular)
            cabeza.prev = cabeza;   // Su anterior es él mismo (circular)
        } else {
            // Si la lista ya tiene nodos
            NodoDoble<T> ultimo = cabeza.prev; // El último nodo siempre es el anterior a la cabeza
            
            nuevo.next = cabeza;  // El siguiente del nuevo apunta a la cabeza
            nuevo.prev = ultimo;  // El anterior del nuevo apunta al último
            
            ultimo.next = nuevo;  // El siguiente del viejo último ahora es el nuevo
            cabeza.prev = nuevo;  // El anterior de la cabeza ahora es el nuevo
        }
        tamano++; // Incrementamos el tamaño de la lista
    }

    // Método para insertar un elemento en una posición específica (índice basado en 0)
    public void insertarEnPosicion(int posicion, T dato) {
        // Validamos si la posición solicitada es válida
        if (posicion < 0 || posicion > tamano) {
            System.out.println("Error: Posición " + posicion + " fuera de rango.");
            return;
        }

        // Si la posición es 0 (insertar en la cabeza)
        if (posicion == 0) {
            insertar(dato); // Reutilizamos el insertar normal (lo coloca antes de la cabeza)
            cabeza = cabeza.prev; // Como insertar lo puso al "final", movemos la cabeza hacia atrás
            // (Otra forma de verlo: el nuevo insertado se convierte en la nueva cabeza)
            return;
        }

        // Si es al final, el comportamiento por defecto de insertar(dato) es suficiente
        if (posicion == tamano) {
            insertar(dato);
            return;
        }

        // Caso general: insertar en medio de la lista circular
        NodoDoble<T> nuevo = new NodoDoble<>(dato); // Creamos el nuevo nodo
        NodoDoble<T> actual = cabeza;               // Nodo iterador

        // Recorremos hasta el nodo que ocupa actualmente la posición deseada
        for (int i = 0; i < posicion; i++) {
            actual = actual.next;
        }

        // Hacemos las conexiones para colocar 'nuevo' justo antes de 'actual'
        NodoDoble<T> anterior = actual.prev; // Guardamos el nodo que estaba antes del actual
        
        nuevo.prev = anterior;  // El anterior del nuevo es el 'anterior'
        nuevo.next = actual;    // El siguiente del nuevo es el 'actual'
        
        anterior.next = nuevo;  // El siguiente del 'anterior' se actualiza al nuevo
        actual.prev = nuevo;    // El anterior del 'actual' se actualiza al nuevo

        tamano++; // Aumentamos el tamaño de la lista
    }

    // Método para buscar un dato dentro de la lista circular
    public boolean buscar(T dato) {
        if (cabeza == null) return false; // Si está vacía, no se encuentra el dato
        
        NodoDoble<T> actual = cabeza; // Iniciamos la búsqueda en la cabeza
        
        // Usamos do-while para garantizar al menos una iteración y detenernos al dar la vuelta
        do {
            if (actual.dato.equals(dato)) {
                return true; // Encontramos el elemento
            }
            actual = actual.next; // Pasamos al siguiente nodo
        } while (actual != cabeza); // Condición: detenernos al volver a la cabeza
        
        return false; // Retornamos falso si dimos la vuelta sin encontrarlo
    }

    // Método para eliminar la primera aparición de un dato en la lista
    public boolean eliminar(T dato) {
        if (cabeza == null) return false; // Lista vacía, no se puede eliminar

        NodoDoble<T> actual = cabeza; // Empezamos en la cabeza
        
        // Usamos do-while para recorrer el ciclo
        do {
            // Si el nodo actual contiene el dato a eliminar
            if (actual.dato.equals(dato)) {
                // Caso 1: Es el único nodo en la lista
                if (actual.next == actual) {
                    cabeza = null; // La lista queda completamente vacía
                } else {
                    // Caso 2: Hay más de un nodo
                    NodoDoble<T> anterior = actual.prev;   // Nodo previo al que vamos a eliminar
                    NodoDoble<T> siguiente = actual.next;  // Nodo posterior al que vamos a eliminar
                    
                    anterior.next = siguiente; // Saltamos el nodo actual en dirección adelante
                    siguiente.prev = anterior; // Saltamos el nodo actual en dirección atrás
                    
                    // Si el nodo a eliminar resultaba ser la cabeza de la lista
                    if (actual == cabeza) {
                        cabeza = siguiente; // Actualizamos la cabeza al siguiente nodo
                    }
                }
                tamano--; // Disminuimos el tamaño
                return true; // Eliminación exitosa
            }
            actual = actual.next; // Avanzamos al siguiente nodo
        } while (actual != cabeza); // Repetimos mientras no demos una vuelta completa

        return false; // El dato no fue encontrado
    }

    // Método para imprimir la lista circular desde la cabeza hacia adelante
    public void recorrerAdelante() {
        // Si no hay nodos, mostramos mensaje
        if (cabeza == null) {
            System.out.println("Lista Circular Vacía");
            return;
        }
        
        NodoDoble<T> actual = cabeza; // Iniciamos en la cabeza
        System.out.print("Lista Circular (Adelante): ");
        
        // do-while para imprimir y avanzar, deteniéndonos al regresar al inicio
        do {
            System.out.print(actual.dato + " <-> "); // Mostramos el dato
            actual = actual.next;                    // Pasamos al siguiente
        } while (actual != cabeza);
        
        // Indicamos visualmente que la lista se cierra
        System.out.println("(vuelve a " + cabeza.dato + ")");
    }

    // Método para imprimir la lista circular desde el final hacia atrás
    public void recorrerAtras() {
        // Si no hay nodos, mostramos mensaje
        if (cabeza == null) {
            System.out.println("Lista Circular Vacía");
            return;
        }
        
        NodoDoble<T> ultimo = cabeza.prev; // El último nodo es el que está antes de la cabeza
        NodoDoble<T> actual = ultimo;      // Empezamos desde el último
        
        System.out.print("Lista Circular (Atrás): ");
        
        // do-while para recorrer hacia atrás hasta dar toda la vuelta
        do {
            System.out.print(actual.dato + " <-> "); // Mostramos el dato
            actual = actual.prev;                    // Retrocedemos al nodo anterior
        } while (actual != ultimo);
        
        // Indicamos visualmente que la lista se cierra en el último
        System.out.println("(vuelve a " + ultimo.dato + ")");
    }

    // Método para obtener el tamaño actual de la lista circular
    public int getTamano() {
        return tamano; // Devuelve cuántos elementos hay almacenados
    }
}
