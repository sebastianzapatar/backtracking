import java.util.*;

/**
 * Soluciones de referencia - Taller de pilas, colas y listas ligadas.
 *
 * Nota para clase:
 * - Este archivo contiene metodos independientes para revisar la logica de cada ejercicio.
 * - Se usan Stack/Deque/Queue de Java para pilas y colas.
 * - Para listas ligadas se implementa una estructura simple con nodos.
 */
public class SolucionesPilasColasListas_v2 {

    // =========================================================
    // 1. PILAS - Invertir una palabra
    // =========================================================
    public static String ejercicio01InvertirPalabra(String palabra) {
        Stack<Character> pila = new Stack<>();
        for (char c : palabra.toCharArray()) {
            pila.push(c);
        }

        StringBuilder resultado = new StringBuilder();
        while (!pila.isEmpty()) {
            resultado.append(pila.pop());
        }
        return resultado.toString();
    }

    // =========================================================
    // 2. PILAS - Verificar parentesis simples
    // =========================================================
    public static boolean ejercicio02ParentesisBalanceados(String texto) {
        Stack<Character> pila = new Stack<>();

        for (char c : texto.toCharArray()) {
            if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                if (pila.isEmpty()) {
                    return false;
                }
                pila.pop();
            }
        }
        return pila.isEmpty();
    }

    // =========================================================
    // 3. PILAS - Historial de paginas
    // =========================================================
    public static String ejercicio03Historial(String[] operaciones) {
        Stack<String> atras = new Stack<>();
        String paginaActual = null;

        for (String operacion : operaciones) {
            if (operacion.startsWith("VISIT ")) {
                if (paginaActual != null) {
                    atras.push(paginaActual);
                }
                paginaActual = operacion.substring(6);
            } else if (operacion.equals("BACK")) {
                if (!atras.isEmpty()) {
                    paginaActual = atras.pop();
                }
            }
        }
        return paginaActual;
    }

    // =========================================================
    // 4. PILAS - Eliminar duplicados consecutivos
    // =========================================================
    public static String ejercicio04EliminarDuplicados(String texto) {
        Stack<Character> pila = new Stack<>();

        for (char c : texto.toCharArray()) {
            if (!pila.isEmpty() && pila.peek() == c) {
                pila.pop();
            } else {
                pila.push(c);
            }
        }

        StringBuilder resultado = new StringBuilder();
        for (char c : pila) {
            resultado.append(c);
        }
        return resultado.toString();
    }

    // =========================================================
    // 5. PILAS - Evaluar expresion postfija
    // =========================================================
    public static int ejercicio05EvaluarPostfija(String expresion) {
        Stack<Integer> pila = new Stack<>();
        String[] tokens = expresion.split(" ");

        for (String token : tokens) {
            if (token.matches("-?\\d+")) {
                pila.push(Integer.parseInt(token));
            } else {
                int b = pila.pop();
                int a = pila.pop();
                switch (token) {
                    case "+" -> pila.push(a + b);
                    case "-" -> pila.push(a - b);
                    case "*" -> pila.push(a * b);
                    case "/" -> pila.push(a / b);
                    default -> throw new IllegalArgumentException("Operador no valido: " + token);
                }
            }
        }
        return pila.pop();
    }

    // =========================================================
    // 6. PILAS - Minimo en pila en O(1)
    // =========================================================
    static class PilaConMinimo {
        private final Stack<Integer> datos = new Stack<>();
        private final Stack<Integer> minimos = new Stack<>();

        public void push(int valor) {
            datos.push(valor);
            if (minimos.isEmpty() || valor <= minimos.peek()) {
                minimos.push(valor);
            }
        }

        public int pop() {
            if (datos.isEmpty()) {
                throw new EmptyStackException();
            }
            int valor = datos.pop();
            if (valor == minimos.peek()) {
                minimos.pop();
            }
            return valor;
        }

        public int top() {
            return datos.peek();
        }

        public int min() {
            return minimos.peek();
        }
    }

    // =========================================================
    // 7. PILAS - Editor con deshacer
    // =========================================================
    public static String ejercicio07Editor(String[] operaciones) {
        StringBuilder texto = new StringBuilder();
        Stack<String> historial = new Stack<>();

        for (String operacion : operaciones) {
            if (operacion.startsWith("ADD ")) {
                String agregado = operacion.substring(4);
                texto.append(agregado);
                historial.push(agregado);
            } else if (operacion.equals("UNDO") && !historial.isEmpty()) {
                String ultimo = historial.pop();
                texto.delete(texto.length() - ultimo.length(), texto.length());
            }
        }
        return texto.toString();
    }

    // =========================================================
    // 8. COLAS - Atencion en fila
    // =========================================================
    public static List<String> ejercicio08AtenderFila(List<String> nombres, int k) {
        Queue<String> cola = new LinkedList<>(nombres);
        List<String> atendidos = new ArrayList<>();

        for (int i = 0; i < k && !cola.isEmpty(); i++) {
            atendidos.add(cola.poll());
        }
        return atendidos;
    }

    // =========================================================
    // 9. COLAS - Rotar una cola
    // =========================================================
    public static Queue<Integer> ejercicio09RotarCola(Queue<Integer> cola, int k) {
        if (cola.isEmpty()) return cola;
        k = k % cola.size();

        for (int i = 0; i < k; i++) {
            cola.offer(cola.poll());
        }
        return cola;
    }

    // =========================================================
    // 10. COLAS - Intercalar dos colas
    // =========================================================
    public static Queue<String> ejercicio10Intercalar(Queue<String> a, Queue<String> b) {
        Queue<String> resultado = new LinkedList<>();

        while (!a.isEmpty() && !b.isEmpty()) {
            resultado.offer(a.poll());
            resultado.offer(b.poll());
        }
        return resultado;
    }

    // =========================================================
    // 11. COLAS - Prioridad simple
    // =========================================================
    static class Cliente {
        String nombre;
        boolean prioridad;

        Cliente(String nombre, boolean prioridad) {
            this.nombre = nombre;
            this.prioridad = prioridad;
        }
    }

    public static List<String> ejercicio11Prioridad(List<Cliente> clientes) {
        Queue<Cliente> prioridad = new LinkedList<>();
        Queue<Cliente> normal = new LinkedList<>();

        for (Cliente cliente : clientes) {
            if (cliente.prioridad) {
                prioridad.offer(cliente);
            } else {
                normal.offer(cliente);
            }
        }

        List<String> orden = new ArrayList<>();
        while (!prioridad.isEmpty()) {
            orden.add(prioridad.poll().nombre);
        }
        while (!normal.isEmpty()) {
            orden.add(normal.poll().nombre);
        }
        return orden;
    }

    // =========================================================
    // 12. COLAS - Primer caracter no repetido
    // =========================================================
    public static List<String> ejercicio12PrimerNoRepetido(String texto) {
        int[] frecuencia = new int[256];
        Queue<Character> cola = new LinkedList<>();
        List<String> respuesta = new ArrayList<>();

        for (char c : texto.toCharArray()) {
            frecuencia[c]++;
            cola.offer(c);

            while (!cola.isEmpty() && frecuencia[cola.peek()] > 1) {
                cola.poll();
            }

            respuesta.add(cola.isEmpty() ? "-" : String.valueOf(cola.peek()));
        }
        return respuesta;
    }

    // =========================================================
    // 13. COLAS - Simulador de impresora
    // =========================================================
    static class TrabajoImpresion {
        String nombre;
        int paginas;

        TrabajoImpresion(String nombre, int paginas) {
            this.nombre = nombre;
            this.paginas = paginas;
        }
    }

    public static Map<String, Integer> ejercicio13Impresora(List<TrabajoImpresion> trabajos, int segundosPorPagina) {
        Queue<TrabajoImpresion> cola = new LinkedList<>(trabajos);
        Map<String, Integer> tiemposFinalizacion = new LinkedHashMap<>();
        int tiempoActual = 0;

        while (!cola.isEmpty()) {
            TrabajoImpresion trabajo = cola.poll();
            tiempoActual += trabajo.paginas * segundosPorPagina;
            tiemposFinalizacion.put(trabajo.nombre, tiempoActual);
        }
        return tiemposFinalizacion;
    }

    // =========================================================
    // Clase base para ejercicios de listas ligadas
    // =========================================================
    static class Nodo {
        int valor;
        Nodo siguiente;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    static class ListaLigada {
        Nodo cabeza;

        // 14. LISTAS - Insertar al final
        public void insertarFinal(int valor) {
            Nodo nuevo = new Nodo(valor);
            if (cabeza == null) {
                cabeza = nuevo;
                return;
            }

            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }

        public String mostrar() {
            StringBuilder sb = new StringBuilder();
            Nodo actual = cabeza;
            while (actual != null) {
                sb.append(actual.valor);
                if (actual.siguiente != null) {
                    sb.append(" -> ");
                }
                actual = actual.siguiente;
            }
            return sb.toString();
        }

        // 15. LISTAS - Contar elementos pares
        public int contarPares() {
            int contador = 0;
            Nodo actual = cabeza;
            while (actual != null) {
                if (actual.valor % 2 == 0) {
                    contador++;
                }
                actual = actual.siguiente;
            }
            return contador;
        }

        // 16. LISTAS - Eliminar un valor
        public void eliminarValor(int x) {
            while (cabeza != null && cabeza.valor == x) {
                cabeza = cabeza.siguiente;
            }

            Nodo actual = cabeza;
            while (actual != null && actual.siguiente != null) {
                if (actual.siguiente.valor == x) {
                    actual.siguiente = actual.siguiente.siguiente;
                } else {
                    actual = actual.siguiente;
                }
            }
        }

        // 17. LISTAS - Invertir una lista
        public void invertir() {
            Nodo anterior = null;
            Nodo actual = cabeza;

            while (actual != null) {
                Nodo siguiente = actual.siguiente;
                actual.siguiente = anterior;
                anterior = actual;
                actual = siguiente;
            }
            cabeza = anterior;
        }

        // 18. LISTAS - Detectar ciclo
        public boolean tieneCiclo() {
            Nodo lento = cabeza;
            Nodo rapido = cabeza;

            while (rapido != null && rapido.siguiente != null) {
                lento = lento.siguiente;
                rapido = rapido.siguiente.siguiente;

                if (lento == rapido) {
                    return true;
                }
            }
            return false;
        }
    }

    // 19. LISTAS - Mezclar listas ordenadas
    public static Nodo ejercicio19MezclarOrdenadas(Nodo a, Nodo b) {
        Nodo dummy = new Nodo(0);
        Nodo actual = dummy;

        while (a != null && b != null) {
            if (a.valor <= b.valor) {
                actual.siguiente = a;
                a = a.siguiente;
            } else {
                actual.siguiente = b;
                b = b.siguiente;
            }
            actual = actual.siguiente;
        }

        actual.siguiente = (a != null) ? a : b;
        return dummy.siguiente;
    }

    // 20. LISTAS - Suma de numeros como listas
    public static Nodo ejercicio20SumarNumeros(Nodo a, Nodo b) {
        Nodo dummy = new Nodo(0);
        Nodo actual = dummy;
        int acarreo = 0;

        while (a != null || b != null || acarreo != 0) {
            int suma = acarreo;

            if (a != null) {
                suma += a.valor;
                a = a.siguiente;
            }
            if (b != null) {
                suma += b.valor;
                b = b.siguiente;
            }

            actual.siguiente = new Nodo(suma % 10);
            acarreo = suma / 10;
            actual = actual.siguiente;
        }
        return dummy.siguiente;
    }

    // Metodo auxiliar para crear listas rapidamente en pruebas
    public static Nodo crearLista(int... valores) {
        Nodo dummy = new Nodo(0);
        Nodo actual = dummy;
        for (int valor : valores) {
            actual.siguiente = new Nodo(valor);
            actual = actual.siguiente;
        }
        return dummy.siguiente;
    }

    // Metodo auxiliar para imprimir nodos
    public static String imprimir(Nodo cabeza) {
        StringBuilder sb = new StringBuilder();
        Nodo actual = cabeza;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.siguiente != null) {
                sb.append(" -> ");
            }
            actual = actual.siguiente;
        }
        return sb.toString();
    }

    // Pruebas rapidas
    public static void main(String[] args) {
        System.out.println("1) " + ejercicio01InvertirPalabra("hola"));
        System.out.println("2) " + ejercicio02ParentesisBalanceados("(a+b)*(c+d)"));
        System.out.println("3) " + ejercicio03Historial(new String[]{"VISIT a.com", "VISIT b.com", "BACK"}));
        System.out.println("4) " + ejercicio04EliminarDuplicados("abbaca"));
        System.out.println("5) " + ejercicio05EvaluarPostfija("5 3 + 2 *"));

        PilaConMinimo pila = new PilaConMinimo();
        pila.push(5);
        pila.push(2);
        System.out.println("6) Minimo: " + pila.min());
        pila.pop();
        System.out.println("6) Minimo: " + pila.min());

        System.out.println("7) " + ejercicio07Editor(new String[]{"ADD Hola", "ADD Mundo", "UNDO"}));
        System.out.println("8) " + ejercicio08AtenderFila(Arrays.asList("Ana", "Luis", "Sol"), 2));

        Queue<Integer> cola = new LinkedList<>(Arrays.asList(1, 2, 3, 4));
        System.out.println("9) " + ejercicio09RotarCola(cola, 2));

        Queue<String> a = new LinkedList<>(Arrays.asList("1", "2", "3"));
        Queue<String> b = new LinkedList<>(Arrays.asList("A", "B", "C"));
        System.out.println("10) " + ejercicio10Intercalar(a, b));

        List<Cliente> clientes = Arrays.asList(
                new Cliente("Ana", false),
                new Cliente("Luis", true),
                new Cliente("Sol", false)
        );
        System.out.println("11) " + ejercicio11Prioridad(clientes));
        System.out.println("12) " + ejercicio12PrimerNoRepetido("aabc"));

        List<TrabajoImpresion> trabajos = Arrays.asList(
                new TrabajoImpresion("A", 3),
                new TrabajoImpresion("B", 2)
        );
        System.out.println("13) " + ejercicio13Impresora(trabajos, 5));

        ListaLigada lista = new ListaLigada();
        lista.insertarFinal(1);
        lista.insertarFinal(2);
        lista.insertarFinal(4);
        lista.insertarFinal(7);
        System.out.println("14) " + lista.mostrar());
        System.out.println("15) Pares: " + lista.contarPares());
        lista.eliminarValor(2);
        System.out.println("16) " + lista.mostrar());
        lista.invertir();
        System.out.println("17) " + lista.mostrar());
        System.out.println("18) Ciclo: " + lista.tieneCiclo());

        Nodo mezclada = ejercicio19MezclarOrdenadas(crearLista(1, 3, 5), crearLista(2, 4, 6));
        System.out.println("19) " + imprimir(mezclada));

        Nodo suma = ejercicio20SumarNumeros(crearLista(2, 4, 3), crearLista(5, 6, 4));
        System.out.println("20) " + imprimir(suma));
    }
}
