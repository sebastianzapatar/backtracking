
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Solucionario de 25 ejercicios:
 * Recursión, ordenamiento y búsqueda binaria.
 *
 * Cada método está nombrado como problema01, problema02, etc.
 */
public class Solucionario25Ejercicios {

    public static void main(String[] args) {
        System.out.println("Problema 1: " + problema01("12abc34x5"));
        System.out.println("Problema 2: " + problema02(2, 2));

        int[] arr3 = {1, 2};
        System.out.println("Problema 3:");
        problema03(arr3);

        System.out.println("Problema 4: " + problema04("(())()"));

        int[] arr5 = {3, 7, 2, 9, 1};
        System.out.println("Problema 5: " + problema05(arr5));

        int[][] matriz6 = {
                {1, 2, 3},
                {2, 2, 4}
        };
        System.out.println("Problema 6: " + problema06(matriz6, 2));

        System.out.println("Problema 7:");
        problema07("ab");

        int[] arr8 = {3, 1, 4, 2};
        problema08(arr8);
        System.out.println("Problema 8: " + Arrays.toString(arr8));

        int[] arr9 = {3, -1, 4, -2};
        problema09(arr9);
        System.out.println("Problema 9: " + Arrays.toString(arr9));

        int[] arr10 = {4, 4, 1, 2, 2, 2, 3};
        problema10(arr10);
        System.out.println("Problema 10: " + Arrays.toString(arr10));

        int[] arr11 = {10, 5, 3, 9};
        problema11(arr11, 7);
        System.out.println("Problema 11: " + Arrays.toString(arr11));

        String[] arr12 = {"a", "abc", "ab"};
        problema12(arr12);
        System.out.println("Problema 12: " + Arrays.toString(arr12));

        int[] arr13 = {3, 2, 7, 4, 1};
        problema13(arr13);
        System.out.println("Problema 13: " + Arrays.toString(arr13));

        int[][] matriz14 = {
                {3, 1, 2},
                {9, 5, 6}
        };
        problema14(matriz14);
        System.out.println("Problema 14:");
        imprimirMatriz(matriz14);

        int[] arr15 = {1, 3, 5, 7};
        System.out.println("Problema 15: " + problema15(arr15, 4));

        int[] arr16 = {1, 3, 5, 7};
        System.out.println("Problema 16: " + problema16(arr16, 6));

        int[][] matriz17 = {
                {1, 3, 5},
                {7, 9, 11}
        };
        System.out.println("Problema 17: " + problema17(matriz17, 9));

        System.out.println("Problema 18: " + problema18(16));

        int[] arr19 = {1, 3, 5, 4, 2};
        System.out.println("Problema 19: " + problema19(arr19));

        int[] arr20 = {15, 18, 2, 3, 6, 12};
        System.out.println("Problema 20: " + problema20(arr20, 3));

        char[][] matriz21 = {
                {'A', 'A', 'B'},
                {'B', 'B', 'A'},
                {'A', 'B', 'A'}
        };
        System.out.println("Problema 21: " + problema21(matriz21));

        int[] arr22 = {7, 2, 9, 4, 1};
        problema22(arr22);
        System.out.println("Problema 22: " + Arrays.toString(arr22));

        int[] arr23 = {2, 2, 2, 2, 2};
        System.out.println("Problema 23: " + Arrays.toString(problema23(arr23, 2)));

        int[] arr24 = {9, 4, 7, 1, 3};
        problema24(arr24, 3);
        System.out.println("Problema 24: " + Arrays.toString(arr24));

        System.out.println("Problema 25: " + problema25(20));
    }

    // ============================================================
    // PROBLEMA 1
    // Sumar todos los números completos dentro de un String.
    // Entrada: "12abc34x5" -> Salida: 51
    // ============================================================
    public static int problema01(String texto) {
        return problema01Rec(texto, 0, 0, 0);
    }

    private static int problema01Rec(String texto, int index, int actual, int suma) {
        if (index == texto.length()) {
            return suma + actual;
        }

        char c = texto.charAt(index);

        if (Character.isDigit(c)) {
            int digito = c - '0';
            return problema01Rec(texto, index + 1, actual * 10 + digito, suma);
        }

        return problema01Rec(texto, index + 1, 0, suma + actual);
    }

    // ============================================================
    // PROBLEMA 2
    // Contar caminos en matriz n x m moviéndose solo derecha y abajo.
    // Entrada: n=2, m=2 -> Salida: 2
    // ============================================================
    public static int problema02(int n, int m) {
        return contarCaminosRec(0, 0, n, m);
    }

    private static int contarCaminosRec(int fila, int columna, int n, int m) {
        if (fila >= n || columna >= m) {
            return 0;
        }

        if (fila == n - 1 && columna == m - 1) {
            return 1;
        }

        return contarCaminosRec(fila + 1, columna, n, m)
                + contarCaminosRec(fila, columna + 1, n, m);
    }

    // ============================================================
    // PROBLEMA 3
    // Generar subconjuntos de un arreglo.
    // Entrada: [1,2] -> Salida: [], [2], [1], [1,2]
    // ============================================================
    public static void problema03(int[] arreglo) {
        generarSubconjuntos(arreglo, 0, "");
    }

    private static void generarSubconjuntos(int[] arreglo, int index, String actual) {
        if (index == arreglo.length) {
            System.out.println("[" + actual.trim() + "]");
            return;
        }

        generarSubconjuntos(arreglo, index + 1, actual);
        generarSubconjuntos(arreglo, index + 1, actual + arreglo[index] + " ");
    }

    // ============================================================
    // PROBLEMA 4
    // Validar paréntesis balanceados recursivamente.
    // Entrada: "(())()" -> Salida: true
    // ============================================================
    public static boolean problema04(String texto) {
        return validarParentesisRec(texto, 0, 0);
    }

    private static boolean validarParentesisRec(String texto, int index, int contador) {
        if (contador < 0) {
            return false;
        }

        if (index == texto.length()) {
            return contador == 0;
        }

        char c = texto.charAt(index);

        if (c == '(') {
            return validarParentesisRec(texto, index + 1, contador + 1);
        }

        if (c == ')') {
            return validarParentesisRec(texto, index + 1, contador - 1);
        }

        return validarParentesisRec(texto, index + 1, contador);
    }

    // ============================================================
    // PROBLEMA 5
    // Encontrar máximo recursivamente.
    // Entrada: [3,7,2,9,1] -> Salida: 9
    // ============================================================
    public static int problema05(int[] arreglo) {
        if (arreglo == null || arreglo.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede estar vacío.");
        }

        return maximoRec(arreglo, 0);
    }

    private static int maximoRec(int[] arreglo, int index) {
        if (index == arreglo.length - 1) {
            return arreglo[index];
        }

        int maxResto = maximoRec(arreglo, index + 1);
        return Math.max(arreglo[index], maxResto);
    }

    // ============================================================
    // PROBLEMA 6
    // Contar apariciones de un valor en matriz usando recursión.
    // ============================================================
    public static int problema06(int[][] matriz, int valor) {
        return contarMatrizRec(matriz, valor, 0, 0);
    }

    private static int contarMatrizRec(int[][] matriz, int valor, int fila, int columna) {
        if (fila == matriz.length) {
            return 0;
        }

        if (columna == matriz[fila].length) {
            return contarMatrizRec(matriz, valor, fila + 1, 0);
        }

        int suma = matriz[fila][columna] == valor ? 1 : 0;
        return suma + contarMatrizRec(matriz, valor, fila, columna + 1);
    }

    // ============================================================
    // PROBLEMA 7
    // Generar permutaciones de una cadena.
    // Entrada: "ab" -> Salida: ab, ba
    // ============================================================
    public static void problema07(String texto) {
        permutar("", texto);
    }

    private static void permutar(String prefijo, String restante) {
        if (restante.isEmpty()) {
            System.out.println(prefijo);
            return;
        }

        for (int i = 0; i < restante.length(); i++) {
            char c = restante.charAt(i);
            String nuevoRestante = restante.substring(0, i) + restante.substring(i + 1);
            permutar(prefijo + c, nuevoRestante);
        }
    }

    // ============================================================
    // PROBLEMA 8
    // QuickSort descendente.
    // ============================================================
    public static void problema08(int[] arreglo) {
        quickSortDesc(arreglo, 0, arreglo.length - 1);
    }

    private static void quickSortDesc(int[] arreglo, int bajo, int alto) {
        if (bajo < alto) {
            int pivote = particionarDesc(arreglo, bajo, alto);
            quickSortDesc(arreglo, bajo, pivote - 1);
            quickSortDesc(arreglo, pivote + 1, alto);
        }
    }

    private static int particionarDesc(int[] arreglo, int bajo, int alto) {
        int pivote = arreglo[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (arreglo[j] >= pivote) {
                i++;
                intercambiar(arreglo, i, j);
            }
        }

        intercambiar(arreglo, i + 1, alto);
        return i + 1;
    }

    // ============================================================
    // PROBLEMA 9
    // MergeSort con negativos primero y luego positivos, ambos ascendentes.
    // Entrada: [3,-1,4,-2] -> Salida: [-2,-1,3,4]
    // ============================================================
    public static void problema09(int[] arreglo) {
        mergeSortNormal(arreglo, 0, arreglo.length - 1);
    }

    // Como el orden natural ascendente ya deja negativos primero y positivos después.
    private static void mergeSortNormal(int[] arreglo, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }

        int medio = (inicio + fin) / 2;
        mergeSortNormal(arreglo, inicio, medio);
        mergeSortNormal(arreglo, medio + 1, fin);
        mergeNormal(arreglo, inicio, medio, fin);
    }

    private static void mergeNormal(int[] arreglo, int inicio, int medio, int fin) {
        int[] temp = new int[fin - inicio + 1];
        int i = inicio;
        int j = medio + 1;
        int k = 0;

        while (i <= medio && j <= fin) {
            if (arreglo[i] <= arreglo[j]) {
                temp[k++] = arreglo[i++];
            } else {
                temp[k++] = arreglo[j++];
            }
        }

        while (i <= medio) {
            temp[k++] = arreglo[i++];
        }

        while (j <= fin) {
            temp[k++] = arreglo[j++];
        }

        for (int p = 0; p < temp.length; p++) {
            arreglo[inicio + p] = temp[p];
        }
    }

    // ============================================================
    // PROBLEMA 10
    // Ordenar por frecuencia de mayor a menor.
    // Si tienen la misma frecuencia, ordena ascendentemente por valor.
    // ============================================================
    public static void problema10(int[] arreglo) {
        Map<Integer, Integer> frecuencias = new HashMap<>();

        for (int valor : arreglo) {
            frecuencias.put(valor, frecuencias.getOrDefault(valor, 0) + 1);
        }

        mergeSortFrecuencia(arreglo, 0, arreglo.length - 1, frecuencias);
    }

    private static void mergeSortFrecuencia(int[] arreglo, int inicio, int fin, Map<Integer, Integer> frecuencias) {
        if (inicio >= fin) {
            return;
        }

        int medio = (inicio + fin) / 2;
        mergeSortFrecuencia(arreglo, inicio, medio, frecuencias);
        mergeSortFrecuencia(arreglo, medio + 1, fin, frecuencias);
        mergeFrecuencia(arreglo, inicio, medio, fin, frecuencias);
    }

    private static void mergeFrecuencia(int[] arreglo, int inicio, int medio, int fin, Map<Integer, Integer> frecuencias) {
        int[] temp = new int[fin - inicio + 1];
        int i = inicio;
        int j = medio + 1;
        int k = 0;

        while (i <= medio && j <= fin) {
            if (compararFrecuencia(arreglo[i], arreglo[j], frecuencias) <= 0) {
                temp[k++] = arreglo[i++];
            } else {
                temp[k++] = arreglo[j++];
            }
        }

        while (i <= medio) {
            temp[k++] = arreglo[i++];
        }

        while (j <= fin) {
            temp[k++] = arreglo[j++];
        }

        for (int p = 0; p < temp.length; p++) {
            arreglo[inicio + p] = temp[p];
        }
    }

    private static int compararFrecuencia(int a, int b, Map<Integer, Integer> frecuencias) {
        int fa = frecuencias.get(a);
        int fb = frecuencias.get(b);

        if (fa != fb) {
            return fb - fa; // mayor frecuencia primero
        }

        return a - b; // menor valor primero
    }

    // ============================================================
    // PROBLEMA 11
    // QuickSort por cercanía a x.
    // Si dos números tienen la misma distancia, queda primero el menor.
    // ============================================================
    public static void problema11(int[] arreglo, int x) {
        quickSortCercania(arreglo, 0, arreglo.length - 1, x);
    }

    private static void quickSortCercania(int[] arreglo, int bajo, int alto, int x) {
        if (bajo < alto) {
            int pivote = particionarCercania(arreglo, bajo, alto, x);
            quickSortCercania(arreglo, bajo, pivote - 1, x);
            quickSortCercania(arreglo, pivote + 1, alto, x);
        }
    }

    private static int particionarCercania(int[] arreglo, int bajo, int alto, int x) {
        int pivote = arreglo[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (compararCercania(arreglo[j], pivote, x) <= 0) {
                i++;
                intercambiar(arreglo, i, j);
            }
        }

        intercambiar(arreglo, i + 1, alto);
        return i + 1;
    }

    private static int compararCercania(int a, int b, int x) {
        int da = Math.abs(a - x);
        int db = Math.abs(b - x);

        if (da != db) {
            return da - db;
        }

        return a - b;
    }

    // ============================================================
    // PROBLEMA 12
    // MergeSort para ordenar String por longitud.
    // Si tienen igual longitud, orden alfabético.
    // ============================================================
    public static void problema12(String[] arreglo) {
        mergeSortStringLongitud(arreglo, 0, arreglo.length - 1);
    }

    private static void mergeSortStringLongitud(String[] arreglo, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }

        int medio = (inicio + fin) / 2;
        mergeSortStringLongitud(arreglo, inicio, medio);
        mergeSortStringLongitud(arreglo, medio + 1, fin);
        mergeStringLongitud(arreglo, inicio, medio, fin);
    }

    private static void mergeStringLongitud(String[] arreglo, int inicio, int medio, int fin) {
        String[] temp = new String[fin - inicio + 1];
        int i = inicio;
        int j = medio + 1;
        int k = 0;

        while (i <= medio && j <= fin) {
            if (compararString(arreglo[i], arreglo[j]) <= 0) {
                temp[k++] = arreglo[i++];
            } else {
                temp[k++] = arreglo[j++];
            }
        }

        while (i <= medio) {
            temp[k++] = arreglo[i++];
        }

        while (j <= fin) {
            temp[k++] = arreglo[j++];
        }

        for (int p = 0; p < temp.length; p++) {
            arreglo[inicio + p] = temp[p];
        }
    }

    private static int compararString(String a, String b) {
        if (a.length() != b.length()) {
            return a.length() - b.length();
        }

        return a.compareTo(b);
    }

    // ============================================================
    // PROBLEMA 13
    // Reorganizar pares al inicio e impares al final.
    // No garantiza orden interno.
    // ============================================================
    public static void problema13(int[] arreglo) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda < derecha) {
            while (izquierda < derecha && arreglo[izquierda] % 2 == 0) {
                izquierda++;
            }

            while (izquierda < derecha && arreglo[derecha] % 2 != 0) {
                derecha--;
            }

            if (izquierda < derecha) {
                intercambiar(arreglo, izquierda, derecha);
            }
        }
    }

    // ============================================================
    // PROBLEMA 14
    // Ordenar cada fila de una matriz.
    // ============================================================
    public static void problema14(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            mergeSortNormal(matriz[i], 0, matriz[i].length - 1);
        }
    }

    // ============================================================
    // PROBLEMA 15
    // Primer elemento mayor o igual a x.
    // Entrada: [1,3,5,7], x=4 -> Salida: 5
    // Si no existe, retorna -1.
    // ============================================================
    public static int problema15(int[] arreglo, int x) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;
        int respuesta = -1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arreglo[medio] >= x) {
                respuesta = arreglo[medio];
                derecha = medio - 1;
            } else {
                izquierda = medio + 1;
            }
        }

        return respuesta;
    }

    // ============================================================
    // PROBLEMA 16
    // Último elemento menor o igual a x.
    // Entrada: [1,3,5,7], x=6 -> Salida: 5
    // Si no existe, retorna -1.
    // ============================================================
    public static int problema16(int[] arreglo, int x) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;
        int respuesta = -1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arreglo[medio] <= x) {
                respuesta = arreglo[medio];
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return respuesta;
    }

    // ============================================================
    // PROBLEMA 17
    // Buscar en matriz ordenada por filas.
    // Se asume que la matriz puede verse como un arreglo ordenado.
    // ============================================================
    public static boolean problema17(int[][] matriz, int x) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int izquierda = 0;
        int derecha = filas * columnas - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            int fila = medio / columnas;
            int columna = medio % columnas;

            if (matriz[fila][columna] == x) {
                return true;
            }

            if (matriz[fila][columna] < x) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return false;
    }

    // ============================================================
    // PROBLEMA 18
    // Raíz cuadrada entera usando búsqueda binaria.
    // Si es exacta, retorna la raíz. Si no, retorna piso.
    // ============================================================
    public static int problema18(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n no puede ser negativo.");
        }

        int izquierda = 0;
        int derecha = n;
        int respuesta = 0;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if ((long) medio * medio <= n) {
                respuesta = medio;
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return respuesta;
    }

    // ============================================================
    // PROBLEMA 19
    // Encontrar un elemento pico.
    // Pico: elemento mayor o igual que sus vecinos.
    // ============================================================
    public static int problema19(int[] arreglo) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda < derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arreglo[medio] < arreglo[medio + 1]) {
                izquierda = medio + 1;
            } else {
                derecha = medio;
            }
        }

        return arreglo[izquierda];
    }

    // ============================================================
    // PROBLEMA 20
    // Buscar en arreglo ordenado y rotado usando búsqueda binaria.
    // Si no existe, retorna -1.
    // ============================================================
    public static int problema20(int[] arreglo, int x) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arreglo[medio] == x) {
                return medio;
            }

            if (arreglo[izquierda] <= arreglo[medio]) {
                if (x >= arreglo[izquierda] && x < arreglo[medio]) {
                    derecha = medio - 1;
                } else {
                    izquierda = medio + 1;
                }
            } else {
                if (x > arreglo[medio] && x <= arreglo[derecha]) {
                    izquierda = medio + 1;
                } else {
                    derecha = medio - 1;
                }
            }
        }

        return -1;
    }

    // ============================================================
    // PROBLEMA 21
    // Camino en matriz A/B sin más de dos caracteres iguales consecutivos.
    // Movimientos permitidos: derecha y abajo.
    // ============================================================
    public static boolean problema21(char[][] matriz) {
        return caminoValidoRec(matriz, 0, 0, '\0', 0);
    }

    private static boolean caminoValidoRec(char[][] matriz, int fila, int columna, char anterior, int repetidos) {
        if (fila >= matriz.length || columna >= matriz[0].length) {
            return false;
        }

        char actual = matriz[fila][columna];

        int nuevosRepetidos;
        if (actual == anterior) {
            nuevosRepetidos = repetidos + 1;
        } else {
            nuevosRepetidos = 1;
        }

        if (nuevosRepetidos > 2) {
            return false;
        }

        if (fila == matriz.length - 1 && columna == matriz[0].length - 1) {
            return true;
        }

        return caminoValidoRec(matriz, fila + 1, columna, actual, nuevosRepetidos)
                || caminoValidoRec(matriz, fila, columna + 1, actual, nuevosRepetidos);
    }

    // ============================================================
    // PROBLEMA 22
    // MergeSort para ordenar:
    // 1. Pares en orden ascendente.
    // 2. Impares en orden descendente.
    // Entrada: [7,2,9,4,1] -> Salida: [2,4,9,7,1]
    // ============================================================
    public static void problema22(int[] arreglo) {
        mergeSortParImpar(arreglo, 0, arreglo.length - 1);
    }

    private static void mergeSortParImpar(int[] arreglo, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }

        int medio = (inicio + fin) / 2;
        mergeSortParImpar(arreglo, inicio, medio);
        mergeSortParImpar(arreglo, medio + 1, fin);
        mergeParImpar(arreglo, inicio, medio, fin);
    }

    private static void mergeParImpar(int[] arreglo, int inicio, int medio, int fin) {
        int[] temp = new int[fin - inicio + 1];
        int i = inicio;
        int j = medio + 1;
        int k = 0;

        while (i <= medio && j <= fin) {
            if (compararParImpar(arreglo[i], arreglo[j]) <= 0) {
                temp[k++] = arreglo[i++];
            } else {
                temp[k++] = arreglo[j++];
            }
        }

        while (i <= medio) {
            temp[k++] = arreglo[i++];
        }

        while (j <= fin) {
            temp[k++] = arreglo[j++];
        }

        for (int p = 0; p < temp.length; p++) {
            arreglo[inicio + p] = temp[p];
        }
    }

    private static int compararParImpar(int a, int b) {
        boolean aPar = a % 2 == 0;
        boolean bPar = b % 2 == 0;

        if (aPar && !bPar) {
            return -1;
        }

        if (!aPar && bPar) {
            return 1;
        }

        if (aPar) {
            return a - b; // pares ascendente
        }

        return b - a; // impares descendente
    }

    // ============================================================
    // PROBLEMA 23
    // Encontrar rango [primera, última] aparición con búsqueda binaria.
    // Si no existe, retorna [-1, -1].
    // ============================================================
    public static int[] problema23(int[] arreglo, int x) {
        int primera = primeraAparicion(arreglo, x);
        int ultima = ultimaAparicion(arreglo, x);
        return new int[]{primera, ultima};
    }

    private static int primeraAparicion(int[] arreglo, int x) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;
        int respuesta = -1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arreglo[medio] == x) {
                respuesta = medio;
                derecha = medio - 1;
            } else if (arreglo[medio] < x) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return respuesta;
    }

    private static int ultimaAparicion(int[] arreglo, int x) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;
        int respuesta = -1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arreglo[medio] == x) {
                respuesta = medio;
                izquierda = medio + 1;
            } else if (arreglo[medio] < x) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return respuesta;
    }

    // ============================================================
    // PROBLEMA 24
    // QuickSelect para dejar los k elementos más pequeños al inicio.
    // Luego se ordenan solo esas primeras k posiciones.
    // ============================================================
    public static void problema24(int[] arreglo, int k) {
        if (k <= 0 || k > arreglo.length) {
            throw new IllegalArgumentException("k inválido.");
        }

        quickSelect(arreglo, 0, arreglo.length - 1, k - 1);
        mergeSortRango(arreglo, 0, k - 1);
    }

    private static void quickSelect(int[] arreglo, int bajo, int alto, int kIndex) {
        if (bajo >= alto) {
            return;
        }

        int pivote = particionarAsc(arreglo, bajo, alto);

        if (pivote == kIndex) {
            return;
        }

        if (kIndex < pivote) {
            quickSelect(arreglo, bajo, pivote - 1, kIndex);
        } else {
            quickSelect(arreglo, pivote + 1, alto, kIndex);
        }
    }

    private static int particionarAsc(int[] arreglo, int bajo, int alto) {
        int pivote = arreglo[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (arreglo[j] <= pivote) {
                i++;
                intercambiar(arreglo, i, j);
            }
        }

        intercambiar(arreglo, i + 1, alto);
        return i + 1;
    }

    private static void mergeSortRango(int[] arreglo, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }

        int medio = (inicio + fin) / 2;
        mergeSortRango(arreglo, inicio, medio);
        mergeSortRango(arreglo, medio + 1, fin);
        mergeNormal(arreglo, inicio, medio, fin);
    }

    // ============================================================
    // PROBLEMA 25
    // Raíz cuadrada entera piso.
    // Entrada: 20 -> Salida: 4
    // ============================================================
    public static int problema25(int n) {
        return problema18(n);
    }

    // ============================================================
    // MÉTODOS AUXILIARES
    // ============================================================
    private static void intercambiar(int[] arreglo, int i, int j) {
        int temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

    private static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            System.out.println(Arrays.toString(fila));
        }
    }
}
