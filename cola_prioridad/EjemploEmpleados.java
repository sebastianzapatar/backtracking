import java.util.PriorityQueue;

abstract class Empleado implements Comparable<Empleado> {
    private static int contadorLlegada = 0;
    
    protected String nombre;
    private int ordenLlegada;

    public Empleado(String nombre) {
        this.nombre = nombre;
        this.ordenLlegada = contadorLlegada++;
    }



    public String getNombre() {
        return nombre;
    }

    @Override
    public int compareTo(Empleado otro) {
        // 1. Si son exactamente del mismo cargo, desempatamos por orden de llegada
        if (this.getClass() == otro.getClass()) {
            return Integer.compare(this.ordenLlegada, otro.ordenLlegada);
        }
        
        // 2. Lógica directa con instanceof para determinar quién va primero (sin números mágicos)
        
        // Si yo soy de Aseo, le gano a quien sea (retorno negativo)
        if (this instanceof AseoGeneral) return -1;
        // Si el otro es de Aseo, él me gana a mí (retorno positivo)
        if (otro instanceof AseoGeneral) return 1;
        
        // Si llegamos a esta línea, ya sabemos que NINGUNO es de AseoGeneral.
        // Si yo soy Profesor, le gano al que quede (Directivo)
        if (this instanceof Profesor) return -1;
        // Si el otro es Profesor, él me gana a mí
        if (otro instanceof Profesor) return 1;
        
        return 0; // Por defecto
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (Turno llegada: %d)", 
                this.getClass().getSimpleName(), this.nombre, this.ordenLlegada);
    }
}

// ================= Clases Hijas =================

class AseoGeneral extends Empleado {
    public AseoGeneral(String nombre) { 
        super(nombre); 
    }
}

class Profesor extends Empleado {
    public Profesor(String nombre) { 
        super(nombre); 
    }
}

class Directivo extends Empleado {
    public Directivo(String nombre) { 
        super(nombre); 
    }
}

// ================= Main =================

public class EjemploEmpleados {
    public static void main(String[] args) {
        PriorityQueue<Empleado> colaServicio = new PriorityQueue<>();

        System.out.println("Registrando llegadas al servicio...");
        // Simulamos la llegada de empleados en orden aleatorio
        colaServicio.offer(new Directivo("Carlos (Rector)"));
        colaServicio.offer(new Profesor("Marta (Matemáticas)"));
        colaServicio.offer(new AseoGeneral("Doña Rosa"));
        colaServicio.offer(new Profesor("Jorge (Física)"));
        colaServicio.offer(new Directivo("Ana (Decana)"));
        colaServicio.offer(new AseoGeneral("Don Jairo"));

        System.out.println("\n=== ORDEN DE ATENCIÓN EN EL SERVICIO ===");
        while (!colaServicio.isEmpty()) {
            // Se atenderán de acuerdo a la prioridad configurada en getNivelPrioridad()
            System.out.println("Atendiendo a: " + colaServicio.poll());
        }
    }
}
