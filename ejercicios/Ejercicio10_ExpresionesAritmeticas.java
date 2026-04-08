/**
 * EJERCICIO 10: Generación de Expresiones con Operadores (Expression Add Operators)
 * ===================================================================================
 * Nivel: Experto (⭐⭐⭐⭐⭐)
 *
 * Descripción:
 *   Dado un string de dígitos y un número objetivo, insertar los operadores
 *   aritméticos +, -, * entre los dígitos para que la expresión evalúe al objetivo.
 *   Considera: precedencia de operadores (*, +, -), sin espacios en el input.
 *
 * Ejemplo:
 *   Input: "123", objetivo = 6
 *   Output: ["1+2+3", "1*2*3"]
 *
 *   Input: "232", objetivo = 8
 *   Output: ["2*3+2", "2+3*2"]
 *
 * Concepto de Backtracking aplicado:
 *   + En cada posición del string, decidimos cuántos dígitos forman el operando.
 *   + Entre cada par de operandos, probamos los operadores +, -, *.
 *   + Evaluamos la expresión respetando la precedencia de operadores.
 *   + PODA: evitar números con ceros a la izquierda + límite de valor absoluto.
 *
 * Complejidad: O(4^n) donde n es la longitud del string (4 decisiones por posición).
 */
import java.util.ArrayList;
import java.util.List;

public class Ejercicio10_ExpresionesAritmeticas {

    static List<String> resultados = new ArrayList<>();

    /**
     * Función de backtracking principal.
     *
     * @param digitos   El string de dígitos original.
     * @param objetivo  El valor que queremos alcanzar.
     * @param indice    La posición actual en el string.
     * @param expr      La expresión construida hasta ahora.
     * @param evalActual El valor de la expresión evaluada hasta ahora.
     * @param ultimoMult El valor del último multiplicando (para manejar precedencia de *).
     */
    public static void generar(String digitos, int objetivo, int indice,
                               StringBuilder expr, long evalActual, long ultimoMult) {

        // Caso base: hemos procesado todos los dígitos
        if (indice == digitos.length()) {
            if (evalActual == objetivo) {
                String expresion = expr.toString();
                resultados.add(expresion);
                System.out.println("  ✅ Expresión válida: " + expresion + " = " + objetivo);
            }
            return;
        }

        int longitudExpr = expr.length(); // Para restaurar el estado (backtrack)

        // Intentar todos los posibles operandos que empiezan en 'indice'
        for (int fin = indice + 1; fin <= digitos.length(); fin++) {
            String parte = digitos.substring(indice, fin);

            // PODA: No permitir números con ceros a la izquierda (e.g., "05" es inválido)
            if (parte.length() > 1 && parte.charAt(0) == '0') {
                break; // "0X..." siempre inválido → poda
            }

            long valorParte;
            try {
                valorParte = Long.parseLong(parte);
            } catch (NumberFormatException e) {
                break;
            }

            // PODA: Evitar overflow
            if (Math.abs(valorParte) > Integer.MAX_VALUE) break;

            System.out.println("  Considerando operando: \"" + parte + "\" en posición " + indice);

            if (indice == 0) {
                // Primer operando: no hay operador antes
                expr.append(parte);
                generar(digitos, objetivo, fin, expr, valorParte, valorParte);

                // Backtrack: restaurar la expresión
                expr.setLength(longitudExpr);
                System.out.println("  ↩ Backtrack: eliminando \"" + parte + "\"");

            } else {
                // Operador '+': evalActual + valorParte
                expr.append('+').append(parte);
                System.out.println("    Probando: " + expr);
                generar(digitos, objetivo, fin, expr,
                        evalActual + valorParte, valorParte);
                expr.setLength(longitudExpr); // Backtrack
                System.out.println("    ↩ Backtrack desde '+'");

                // Operador '-': evalActual - valorParte
                expr.append('-').append(parte);
                System.out.println("    Probando: " + expr);
                generar(digitos, objetivo, fin, expr,
                        evalActual - valorParte, -valorParte);
                expr.setLength(longitudExpr); // Backtrack
                System.out.println("    ↩ Backtrack desde '-'");

                // Operador '*': manejar precedencia
                // Debemos "deshacer" ultimoMult y reemplazarlo por la multiplicación
                // evalActual = (evalActual - ultimoMult) + (ultimoMult * valorParte)
                expr.append('*').append(parte);
                System.out.println("    Probando: " + expr);
                long nuevoMult = ultimoMult * valorParte;
                generar(digitos, objetivo, fin, expr,
                        evalActual - ultimoMult + nuevoMult, nuevoMult);
                expr.setLength(longitudExpr); // Backtrack
                System.out.println("    ↩ Backtrack desde '*'");
            }
        }
    }

    /**
     * Función pública para resolver el problema.
     */
    public static List<String> resolverExpresiones(String digitos, int objetivo) {
        resultados.clear();
        generar(digitos, objetivo, 0, new StringBuilder(), 0, 0);
        return resultados;
    }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 10: Expresiones Aritméticas con Backtracking ===");

        // --- Caso 1 ---
        String digitos1 = "123";
        int objetivo1 = 6;
        System.out.println("\n📌 Caso 1: \"" + digitos1 + "\", objetivo = " + objetivo1);
        System.out.println("Iniciando backtracking...\n");
        List<String> r1 = resolverExpresiones(digitos1, objetivo1);
        System.out.println("\nResultados: " + r1);

        // --- Caso 2 ---
        String digitos2 = "232";
        int objetivo2 = 8;
        resultados.clear();
        System.out.println("\n\n📌 Caso 2: \"" + digitos2 + "\", objetivo = " + objetivo2);
        System.out.println("Iniciando backtracking...\n");
        List<String> r2 = resolverExpresiones(digitos2, objetivo2);
        System.out.println("\nResultados: " + r2);

        // --- Caso 3: Sin solución ---
        String digitos3 = "99";
        int objetivo3 = 10;
        resultados.clear();
        System.out.println("\n\n📌 Caso 3: \"" + digitos3 + "\", objetivo = " + objetivo3);
        List<String> r3 = resolverExpresiones(digitos3, objetivo3);
        System.out.println("\nResultados: " + (r3.isEmpty() ? "❌ Sin solución" : r3));

        // --- Caso 4: Con cero ---
        String digitos4 = "105";
        int objetivo4 = 5;
        resultados.clear();
        System.out.println("\n\n📌 Caso 4: \"" + digitos4 + "\", objetivo = " + objetivo4);
        List<String> r4 = resolverExpresiones(digitos4, objetivo4);
        System.out.println("\nResultados: " + r4);

        System.out.println("\n\n📊 RESUMEN DE TODOS LOS CASOS:");
        System.out.println("┌──────────┬────────┬───────────────────────────────────┐");
        System.out.println("│  Dígitos │ Target │ Expresiones válidas                │");
        System.out.println("├──────────┼────────┼───────────────────────────────────┤");
        System.out.printf( "│  %-8s│   %3d  │ %-35s│%n", digitos1, objetivo1, r1.toString());
        System.out.printf( "│  %-8s│   %3d  │ %-35s│%n", digitos2, objetivo2, r2.toString());
        System.out.printf( "│  %-8s│   %3d  │ %-35s│%n", digitos3, objetivo3, r3.isEmpty() ? "Sin solución" : r3.toString());
        System.out.printf( "│  %-8s│   %3d  │ %-35s│%n", digitos4, objetivo4, r4.toString());
        System.out.println("└──────────┴────────┴───────────────────────────────────┘");
    }
}
