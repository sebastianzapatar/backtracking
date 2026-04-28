package pilascolas;

// Implementación genérica de una Cola (Queue) usando Nodos (FIFO: First In, First Out)
public class Cola<T> {
    private Nodo<T> frente;    // Elemento al inicio de la cola (el próximo a salir)
    private Nodo<T> finalCola; // Elemento al final de la cola (el último en llegar)
    private int tamano;        // Cantidad de elementos en la cola

    // Clase interna Nodo simple
    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamano = 0;
    }

    // Encolar (Enqueue) - O(1)
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            // Si estaba vacía, el único nodo es tanto el frente como el final
            frente = finalCola = nuevo;
        } else {
            // Si ya hay elementos, el que estaba al final apunta al nuevo
            finalCola.siguiente = nuevo;
            // El final de la cola ahora es este nuevo nodo
            finalCola = nuevo; 
        }
        tamano++;
    }

    // Desencolar (Dequeue) - O(1)
    public T desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("Error: La cola está vacía");
        }
        T dato = frente.dato;         // Guardamos el dato del frente
        frente = frente.siguiente;    // El frente avanza al que seguía en la fila
        
        // Cuidado: Si la cola se vació al sacar este elemento
        if (frente == null) {
            finalCola = null; // El final también debe ser null
        }
        tamano--;
        return dato;
    }

    // Ver el frente sin sacarlo (Peek) - O(1)
    public T verFrente() {
        if (estaVacia()) {
            throw new RuntimeException("Error: La cola está vacía");
        }
        return frente.dato;
    }

    // Verifica si la cola está vacía - O(1)
    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamano() {
        return tamano;
    }
}
