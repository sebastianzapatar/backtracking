package listas;

// Clase principal para probar las implementaciones de listas
public class Main {
    public static void main(String[] args) {
        // --- PRUEBAS PARA LA LISTA DOBLEMENTE LIGADA ---
        System.out.println("=== PRUEBA DE LISTA DOBLEMENTE LIGADA (TIPO STRING) ===");
        ListaDoble<String> listaDoble = new ListaDoble<>();
        
        // Probamos inserciones normales al inicio y final
        listaDoble.insertarFinal("Java");      // Índice 0
        listaDoble.insertarFinal("Python");    // Índice 1
        listaDoble.insertarInicio("C++");      // El índice 0 pasa a ser C++, luego Java(1), luego Python(2)
        
        // Probamos la nueva inserción por posición
        System.out.println("Insertando 'JavaScript' en la posición 1...");
        listaDoble.insertarEnPosicion(1, "JavaScript");
        
        // Recorridos para validar la estructura
        // Resultado esperado: C++ <-> JavaScript <-> Java <-> Python <-> NULL
        listaDoble.recorrerAdelante(); 
        listaDoble.recorrerAtras();    
        
        // Búsquedas
        System.out.println("¿Existe 'Java'? " + listaDoble.buscar("Java"));
        System.out.println("¿Existe 'Ruby'? " + listaDoble.buscar("Ruby"));
        
        // Eliminación
        System.out.println("Eliminando 'Java'...");
        listaDoble.eliminar("Java");
        listaDoble.recorrerAdelante();

        System.out.println("\n--------------------------------------------------\n");

        // --- PRUEBAS PARA LA LISTA CIRCULAR DOBLEMENTE LIGADA ---
        System.out.println("=== PRUEBA DE LISTA CIRCULAR DOBLEMENTE LIGADA (TIPO INTEGER) ===");
        ListaCircularDoble<Integer> listaCircular = new ListaCircularDoble<>();
        
        // Insertar elementos base
        listaCircular.insertar(10); // Índice 0
        listaCircular.insertar(20); // Índice 1
        listaCircular.insertar(30); // Índice 2
        
        // Probamos la nueva inserción por posición
        System.out.println("Insertando 15 en la posición 1...");
        listaCircular.insertarEnPosicion(1, 15);
        
        System.out.println("Insertando 5 en la posición 0 (nueva cabeza)...");
        listaCircular.insertarEnPosicion(0, 5);
        
        // Recorridos para validar el ciclo
        // Esperado: 5 <-> 10 <-> 15 <-> 20 <-> 30 <-> (vuelve a 5)
        listaCircular.recorrerAdelante(); 
        listaCircular.recorrerAtras();    
        
        // Búsqueda
        System.out.println("¿Existe el 15? " + listaCircular.buscar(15));
        
        // Eliminaciones
        System.out.println("Eliminando 5 (la cabeza)...");
        listaCircular.eliminar(5);
        listaCircular.recorrerAdelante();
        
        System.out.println("Eliminando 30 (el final)...");
        listaCircular.eliminar(30);
        listaCircular.recorrerAdelante();
    }
}
