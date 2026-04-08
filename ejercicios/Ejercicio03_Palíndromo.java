/**
 * EJERCICIO 3: Partición de String en Palíndromos
 * ================================================
 * Nivel: Intermedio (⭐⭐⭐)
 *
 * Descripción:
 *   Dado un string, encontrar todas las formas posibles de particionarlo
 *   de manera que cada substring de la partición sea un palíndromo.
 *
 * Concepto de Backtracking aplicado:
 *   Intentamos todos los posibles cortes del string. En cada posición,
 *   verificamos si el substring desde el inicio hasta ese punto es palíndromo.
 *   Si no lo es → backtrack. Si sí lo es → continuamos con el resto del string.
 *
 * Ejemplo:
 *   Input: "aab"
 *   Output: [["a","a","b"], ["aa","b"]]
 *
 * Complejidad: O(n * 2^n) tiempo.
 */
import java.util.ArrayList;
import java.util.List;

public class Ejercicio03_Palíndromo {

    /**
     * Verifica si un substring es palíndromo.
     */
    private static boolean esPalindromo(String s, int inicio, int fin) {
        while (inicio < fin) {
            if (s.charAt(inicio) != s.charAt(fin)) {
                return false;
            }
            inicio++;
            fin--;
        }
        return true;
    }

    /**
     * Función de backtracking para encontrar todas las particiones.
     *
     * @param s           El string original.
     * @param inicio      El índice desde donde comenzamos a buscar.
     * @param particion   La partición actual que estamos construyendo.
     * @param resultado   Lista con todas las particiones válidas encontradas.
     */
    public static void particionar(String s, int inicio,
                                    List<String> particion,
                                    List<List<String>> resultado) {
        // Caso base: hemos procesado todo el string
        if (inicio == s.length()) {
            resultado.add(new ArrayList<>(particion)); // Guardar copia
            return;
        }

        // Intentar todos los posibles finales del substring
        for (int fin = inicio; fin < s.length(); fin++) {
            // ¿El substring s[inicio..fin] es palíndromo?
            if (esPalindromo(s, inicio, fin)) {
                String parte = s.substring(inicio, fin + 1);
                System.out.println("  Palíndromo encontrado: \"" + parte + "\"");

                // Agregar a la partición actual y continuar
                particion.add(parte);
                particionar(s, fin + 1, particion, resultado);

                // Backtrack: quitar el último elemento agregado
                particion.remove(particion.size() - 1);
                System.out.println("  Backtrack desde: \"" + parte + "\"");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 3: Partición en Palíndromos ===");

        String input = "aab";
        System.out.println("Input: \"" + input + "\"");
        System.out.println("\nExplorando particiones:");

        List<List<String>> resultado = new ArrayList<>();
        particionar(input, 0, new ArrayList<>(), resultado);

        System.out.println("\n✅ Todas las particiones palíndromas:");
        for (List<String> particion : resultado) {
            System.out.println("  " + particion);
        }

        // Segundo ejemplo
        System.out.println("\n--- Segundo ejemplo ---");
        String input2 = "racecar";
        System.out.println("Input: \"" + input2 + "\"");
        List<List<String>> resultado2 = new ArrayList<>();
        particionar(input2, 0, new ArrayList<>(), resultado2);

        System.out.println("\n✅ Todas las particiones palíndromas:");
        for (List<String> particion : resultado2) {
            System.out.println("  " + particion);
        }
        System.out.println("Total de particiones: " + resultado2.size());
    }
}
