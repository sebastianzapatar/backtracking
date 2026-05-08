import java.util.PriorityQueue;

abstract class Juego implements Comparable<Juego> {
    private static int contadorDescarga = 0;
    
    protected String titulo;
    private int ordenDescarga; // Funciona como el "orden de llegada"

    public Juego(String titulo) {
        this.titulo = titulo;
        this.ordenDescarga = contadorDescarga++;
    }

    /**
     * Método abstracto para que cada clase hija defina su nivel de prioridad explícito.
     * Entre menor sea el número, mayor prioridad tendrá para ejecutarse.
     */
    public abstract int getNivelPrioridad();

    @Override
    public int compareTo(Juego otro) {
        // 1. Criterio principal: Nivel de prioridad por género (Deportivo -> Aventura -> RPG)
        int comparacionGenero = Integer.compare(this.getNivelPrioridad(), otro.getNivelPrioridad());
        
        if (comparacionGenero != 0) {
            return comparacionGenero;
        }
        
        // 2. Criterio de desempate: Orden de descarga (el primero que se descargó se juega primero)
        return Integer.compare(this.ordenDescarga, otro.ordenDescarga);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (Descarga #%d)", 
                this.getClass().getSimpleName(), this.titulo, this.ordenDescarga);
    }
}

// ================= Clases Hijas =================

class JuegoDeportivo extends Juego {
    public JuegoDeportivo(String titulo) { 
        super(titulo); 
    }
    
    @Override
    public int getNivelPrioridad() {
        return 1; // Prioridad 1: Los más importantes, se juegan de primero
    }
}

class JuegoAventura extends Juego {
    public JuegoAventura(String titulo) { 
        super(titulo); 
    }
    
    @Override
    public int getNivelPrioridad() {
        return 2; // Prioridad 2: Se juegan después de los deportivos
    }
}

class JuegoRPG extends Juego {
    public JuegoRPG(String titulo) { 
        super(titulo); 
    }
    
    @Override
    public int getNivelPrioridad() {
        return 3; // Prioridad 3: Se juegan de último (requieren mucho tiempo)
    }
}

// ================= Main =================

public class EjemploJuegos {
    public static void main(String[] args) {
        PriorityQueue<Juego> listaReproduccion = new PriorityQueue<>();

        System.out.println("Agregando juegos a la biblioteca...");
        // Simulamos la descarga de juegos en orden aleatorio
        listaReproduccion.offer(new JuegoRPG("The Witcher 3"));
        listaReproduccion.offer(new JuegoAventura("The Legend of Zelda"));
        listaReproduccion.offer(new JuegoDeportivo("FIFA 24"));
        listaReproduccion.offer(new JuegoAventura("Tomb Raider"));
        listaReproduccion.offer(new JuegoRPG("Final Fantasy VII"));
        listaReproduccion.offer(new JuegoDeportivo("NBA 2K24"));

        System.out.println("\n=== ORDEN EN EL QUE SE JUGARÁN ===");
        while (!listaReproduccion.isEmpty()) {
            System.out.println("Jugando a: " + listaReproduccion.poll());
        }
    }
}
