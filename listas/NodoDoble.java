package listas;

// Clase genérica <T> que representa un nodo en una lista doblemente ligada
public class NodoDoble<T> {
    public T dato;           // El dato o valor que almacena el nodo
    public NodoDoble<T> prev; // Puntero al nodo anterior en la lista
    public NodoDoble<T> next; // Puntero al nodo siguiente en la lista

    // Constructor que inicializa el nodo con un dato específico
    public NodoDoble(T dato) {
        this.dato = dato;     // Asigna el dato pasado por parámetro al nodo
        this.prev = null;     // Inicializa el puntero anterior como nulo (sin conexión)
        this.next = null;     // Inicializa el puntero siguiente como nulo (sin conexión)
    }
}
