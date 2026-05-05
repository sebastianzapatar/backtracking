package pilascolas;

import java.util.Stack;

// Implementación genérica de una Pila (Stack) usando el API de Java (LIFO: Last In, First Out)
public class PilaAPI<T> {
    private Stack<T> pila;

    public PilaAPI() {
        this.pila = new Stack<>();
    }

    // Apilar un elemento (Push) - O(1)
    public void push(T dato) {
        pila.push(dato);
    }

    // Desapilar un elemento (Pop) - O(1)
    public T pop() {
        if (estaVacia()) {
            throw new RuntimeException("Error: La pila está vacía (Stack Underflow)");
        }
        return pila.pop();
    }

    // Ver el tope sin desapilar (Peek) - O(1)
    public T peek() {
        if (estaVacia()) {
            throw new RuntimeException("Error: La pila está vacía");
        }
        return pila.peek();
    }

    // Verifica si la pila está vacía - O(1)
    public boolean estaVacia() {
        return pila.isEmpty();
    }

    public int getTamano() {
        return pila.size();
    }
}
