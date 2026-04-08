# 🧩 Algoritmos de Backtracking en Java

Este repositorio contiene una colección estructurada de **10 ejercicios clásicos de Backtracking** (Vuelta Atrás) implementados en **Java**, ordenados progresivamente por nivel de dificultad. Todo el código resalta visualmente el "árbol de decisiones" de la consola, mostrando cómo el algoritmo busca, encuentra, se equivoca y retrocede (hace *backtrack*).

## 🚀 ¿Qué es el Backtracking?
El **Backtracking** es una técnica algorítmica para encontrar soluciones de forma iterativa y recursiva, construyendo un candidato a solución paso por paso y **abandonándolo** inmediatamente en el momento en que se identifica que no puede conducir a un resultado válido. 

Se puede ver como una Búsqueda en Profundidad (DFS) en un Árbol de Espacio de Estados.

---

## 📂 Contenido del Repositorio

La carpeta principal de trabajo es `ejercicios/`, donde encontrarás las implementaciones puras en Java sin librerías de terceros (ideal para resolver en consolas competitivas de LeetCode o HackerRank).

### 🌱 Nivel Principiante (Conceptos Básicos)
1. **`Ejercicio01_BuscarElemento.java`**
   - Una primera inmersión en la recursividad, buscando en un arreglo mediante decisiones de estado.
2. **`Ejercicio02_SumaSubconjunto.java`** (`Subset Sum`)
   - El clásico problema de tomar la decisión binaria *"¿Incluyo o Excluyo este elemento?"*.

### 🪴 Nivel Intermedio (Desarrollo del Árbol y Reversiones)
3. **`Ejercicio03_Palíndromo.java`**
   - Particionando cadenas en substrings donde cada fragmento es obligado a leerse igual al derecho y al revés.
4. **`Ejercicio04_Permutaciones.java`**
   - Generación de todos los reordenamientos numéricos posibles en un arreglo (Exploración $N!$).
5. **`Ejercicio05_Laberinto.java`**
   - Movimiento en matríz en 4 ejes cardinales y descarte de caminos bloqueados por paredes o repeticiones visitadas.

### 🌳 Nivel Avanzado (Restricciones Compuestas e Introducción a Podas)
6. **`Ejercicio06_NReinas.java`**
   - El legendario problema del ajedrez. Validando espacios limpios en horizontal, vertical y las dos diagonales matemáticas simultáneamente.
7. **`Ejercicio07_ColoracionGrafos.java`** (`M-Coloring Problem`)
   - Asignar hasta M colores a un set de nodos. Si un nodo vecino tiene el mismo color, se descarta y retrocedemos.
8. **`Ejercicio08_Sudoku.java`**
   - Solucionador universal determinista validando por sector $3\times3$, junto a ejes verticales y horizontales simultáneos.

### 🌲 Nivel Experto (Optimización y Restricción Lógica Dinámica)
9. **`Ejercicio09_RecorridoCaballo.java`** (`Knight's Tour Problem`)
   - Pisando celda por celda sin repetirse, el problema aplica la **Heurística de Warnsdorff** para reducir abismalmente los intentos erróneos y no caer en la pesadilla de $8^{N^2}$ de coste de tiempo exponencial puro.
10. **`Ejercicio10_ExpresionesAritmeticas.java`**
    - Intercalar los operadores matemáticos algebraicos (`+`, `-`, `*`) en un string numérico contiguo cuidando la jerarquía de operadores para evaluar de izquierda a derecha sin romperse en el proceso.

---

## 🛠 Instalación y Ejecución

Al ser clases unitarias independientes (con métodos `main`), puedes compilar y ejecutar cualquier clase directamente desde la terminar:

```bash
# Compilar un ejercicio (Ej: Sudoku)
javac ejercicios/Ejercicio08_Sudoku.java

# Ejecutarlo
java ejercicios.Ejercicio08_Sudoku
```

*Nota:* Si compilas desde el interior de la carpeta `ejercicios`, sólo deberás ejecutar `java Ejercicio08_Sudoku` (según tu ubicación de la terminal).

## 🎓 Material Didáctico
Aunque se ignora del repositorio público, se facilitó internamente una presentación `Presentacion_Backtracking_Mejorada.pptx` detallando la teoría, ventajas, desventajas sobre memoria (límite del Stack Overflow), y ejemplos gráficos de los comportamientos del Laberinto y de las Reinas.

---

> Hecho con dedicatoria por y para apasionados de la lógica computacional.
