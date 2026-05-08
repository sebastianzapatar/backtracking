import java.util.PriorityQueue;

class Persona implements Comparable<Persona> {
    // Variable estática para llevar el conteo global de llegadas
    private static int contadorLlegada = 0;
    
    private String nombre;
    private int edad;
    private int gradoEnfermedad; // Asumiremos que un número mayor significa mayor gravedad
    private int ordenLlegada;    // Para desempatar si la gravedad y la edad son iguales

    public Persona(String nombre, int edad, int gradoEnfermedad) {
        this.nombre = nombre;
        this.edad = edad;
        this.gradoEnfermedad = gradoEnfermedad;
        this.ordenLlegada = contadorLlegada++; // Asignamos el turno y luego incrementamos
    }

    @Override
    public int compareTo(Persona otra) {
        // 1. Criterio principal: Grado de enfermedad (de mayor a menor gravedad)
        if (this.gradoEnfermedad != otra.gradoEnfermedad) {
            // Invertimos el orden para que el mayor grado quede primero (poll)
            return Integer.compare(otra.gradoEnfermedad, this.gradoEnfermedad);
        }
        
        // 2. Segundo criterio: Edad (de mayor a menor edad, priorizamos ancianos)
        if (this.edad != otra.edad) {
            return Integer.compare(otra.edad, this.edad);
        }
        
        // 3. Tercer criterio: Orden de llegada (de menor a mayor, el que llegó antes)
        return Integer.compare(this.ordenLlegada, otra.ordenLlegada);
    }

    @Override
    public String toString() {
        return String.format("Paciente: %-6s | Gravedad: %d | Edad: %d | Turno de llegada: %d", 
                             nombre, gradoEnfermedad, edad, ordenLlegada);
    }
}

public class EjemploUrgencias {
    public static void main(String[] args) {
        // Creamos nuestra cola de prioridades
        PriorityQueue<Persona> colaUrgencias = new PriorityQueue<>();

        // Simulamos la llegada de pacientes (el orden de los .offer es el orden de llegada)
        colaUrgencias.offer(new Persona("Ana", 30, 2));   // Llegó de 1ro
        colaUrgencias.offer(new Persona("Luis", 45, 5));  // Llegó de 2do (Gravedad alta)
        colaUrgencias.offer(new Persona("Pedro", 80, 2)); // Llegó de 3ro (Misma gravedad que Ana, pero muy mayor)
        colaUrgencias.offer(new Persona("María", 80, 2)); // Llegó de 4ta (Misma gravedad y edad que Pedro)
        colaUrgencias.offer(new Persona("Juan", 20, 5));  // Llegó de 5to (Misma gravedad que Luis, pero más joven)

        System.out.println("=== ORDEN DE ATENCIÓN EN URGENCIAS ===");
        while (!colaUrgencias.isEmpty()) {
            // poll() saca y retorna el elemento con mayor prioridad
            System.out.println(colaUrgencias.poll());
        }
    }
}
