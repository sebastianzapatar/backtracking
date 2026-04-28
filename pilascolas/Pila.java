package pilascolas;

// Implementación genérica de una Pila (Stack) usando Nodos (LIFO: Last In, First Out)
public class Pila<T> {
    private Nodo<T> tope; // Apunta al elemento en la cima de la pila
    private int tamano;   // Mantiene el número de elementos apilados

    // Clase interna para los Nodos de la pila (simples)
    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public Pila() {
        this.tope = null;
        this.tamano = 0;
    }

    // Apilar un elemento (Push) - O(1)
    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato); // Se crea el nuevo nodo
        nuevo.siguiente = tope;           // El siguiente del nuevo será el tope antiguo
        tope = nuevo;                     // El nuevo nodo se convierte en el tope absoluto
        tamano++;
    }

    // Desapilar un elemento (Pop) - O(1)
    public T pop() {
        if (estaVacia()) {
            throw new RuntimeException("Error: La pila está vacía (Stack Underflow)");
        }
        T dato = tope.dato;       // Guardamos el dato a retornar
        tope = tope.siguiente;    // El tope desciende al nodo de abajo
        tamano--;
        return dato;
    }

    // Ver el tope sin desapilar (Peek) - O(1)
    public T peek() {
        if (estaVacia()) {
            throw new RuntimeException("Error: La pila está vacía");
        }
        return tope.dato;
    }

    // Verifica si la pila está vacía - O(1)
    public boolean estaVacia() {
        return tope == null;
    }

    public int getTamano() {
        return tamano;
    }
}
