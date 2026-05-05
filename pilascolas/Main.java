package pilascolas;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE PILA (Construida desde cero con Nodos) ===");
        Pila<String> historialNodos = new Pila<>();
        historialNodos.push("Página 1: Inicio");
        historialNodos.push("Página 2: Buscar Producto");
        historialNodos.push("Página 3: Confirmar Compra");
        System.out.println("Tope actual: " + historialNodos.peek());
        System.out.println("Presionando 'Atrás': " + historialNodos.pop());
        System.out.println("Página actual: " + historialNodos.peek());
        System.out.println("Páginas en historial: " + historialNodos.getTamano());

        System.out.println("\n=== PRUEBA DE PILA (Usando la API de Java) ===");
        PilaAPI<String> historialAPI = new PilaAPI<>();
        historialAPI.push("Página 1: Inicio");
        historialAPI.push("Página 2: Buscar Producto");
        historialAPI.push("Página 3: Confirmar Compra");
        System.out.println("Tope actual: " + historialAPI.peek());
        System.out.println("Presionando 'Atrás': " + historialAPI.pop());
        System.out.println("Página actual: " + historialAPI.peek());
        System.out.println("Páginas en historial: " + historialAPI.getTamano());

        System.out.println("\n----------------------------------------------------\n");

        System.out.println("=== PRUEBA DE COLA (Construida desde cero con Nodos) ===");
        Cola<String> impresionNodos = new Cola<>();
        impresionNodos.encolar("Documento_Tesis.pdf");
        impresionNodos.encolar("Foto_Familiar.png");
        impresionNodos.encolar("Factura_Mes.docx");
        System.out.println("Próximo a imprimir: " + impresionNodos.verFrente());
        System.out.println("Imprimiendo: " + impresionNodos.desencolar());
        System.out.println("Próximo a imprimir: " + impresionNodos.verFrente());
        System.out.println("Documentos en espera: " + impresionNodos.getTamano());

        System.out.println("\n=== PRUEBA DE COLA (Usando la API de Java) ===");
        ColaAPI<String> impresionAPI = new ColaAPI<>();
        impresionAPI.encolar("Documento_Tesis.pdf");
        impresionAPI.encolar("Foto_Familiar.png");
        impresionAPI.encolar("Factura_Mes.docx");
        System.out.println("Próximo a imprimir: " + impresionAPI.verFrente());
        System.out.println("Imprimiendo: " + impresionAPI.desencolar());
        System.out.println("Próximo a imprimir: " + impresionAPI.verFrente());
        System.out.println("Documentos en espera: " + impresionAPI.getTamano());
    }
}
