package pilascolas;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE PILA (LIFO) ===");
        Pila<String> historial = new Pila<>();
        
        historial.push("Página 1: Inicio");
        historial.push("Página 2: Buscar Producto");
        historial.push("Página 3: Confirmar Compra");
        
        System.out.println("Tope actual: " + historial.peek()); // Página 3
        System.out.println("Presionando 'Atrás': " + historial.pop()); // Saca Página 3
        System.out.println("Página actual: " + historial.peek()); // Página 2
        System.out.println("Páginas en historial: " + historial.getTamano());

        System.out.println("\n=== PRUEBA DE COLA (FIFO) ===");
        Cola<String> impresion = new Cola<>();
        
        impresion.encolar("Documento_Tesis.pdf");
        impresion.encolar("Foto_Familiar.png");
        impresion.encolar("Factura_Mes.docx");
        
        System.out.println("Próximo a imprimir: " + impresion.verFrente()); // Tesis
        System.out.println("Imprimiendo: " + impresion.desencolar());       // Saca Tesis
        System.out.println("Próximo a imprimir: " + impresion.verFrente()); // Foto
        System.out.println("Documentos en espera: " + impresion.getTamano());
    }
}
