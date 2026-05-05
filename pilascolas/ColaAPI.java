package pilascolas;

import java.util.LinkedList;
import java.util.Queue;

// Implementación genérica de una Cola (Queue) usando el API de Java (FIFO: First In, First Out)
public class ColaAPI<T> {
    private Queue<T> cola;

    public ColaAPI() {
        this.cola = new LinkedList<>();
    }

    // Encolar (Enqueue) - O(1)
    public void encolar(T dato) {
        cola.offer(dato);
    }

    // Desencolar (Dequeue) - O(1)
    public T desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("Error: La cola está vacía");
        }
        return cola.poll();
    }

    // Ver el frente sin sacarlo (Peek) - O(1)
    public T verFrente() {
        if (estaVacia()) {
            throw new RuntimeException("Error: La cola está vacía");
        }
        return cola.peek();
    }

    // Verifica si la cola está vacía - O(1)
    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int getTamano() {
        return cola.size();
    }
}
