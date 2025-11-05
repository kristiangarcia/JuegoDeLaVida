import java.util.Random;

public class JuegoDeLaVida {
    private Celula[][] tablero;
    private int filas;
    private int columnas;
    private int generacion;
    private Random random;
    private static final double PROBABILIDAD_EVENTO_ALEATORIO = 0.1;

    public JuegoDeLaVida(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.generacion = 0;
        this.random = new Random();
        this.tablero = new Celula[filas][columnas];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = new Celula();
            }
        }
    }

    /**
     * Calcula el número de vecinos vivos en un mundo esférico (toroidal).
     * En un mundo esférico, los bordes están conectados, por lo que
     * todas las células tienen exactamente 8 vecinos.
     */
    private int contarVecinosVivos(int fila, int col) {
        int vecinos = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int vecinoFila = (fila + i + filas) % filas;
                int vecinoCol = (col + j + columnas) % columnas;

                if (tablero[vecinoFila][vecinoCol].estaViva()) {
                    vecinos++;
                }
            }
        }

        return vecinos;
    }

    /**
     * Aplica las reglas del juego de la vida:
     * - Nacimiento: posición vacía con 3 vecinos → nace célula
     * - Muerte por aislamiento: célula con < 2 vecinos → muere
     * - Muerte por sofocación: célula con > 4 vecinos → muere
     * - Supervivencia: célula con 2, 3 o 4 vecinos → sobrevive
     * - Eventos aleatorios: 10% de probabilidad de generación espontánea o muerte
     */
    public void evolucionar() {
        Celula[][] nuevoTablero = new Celula[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int vecinos = contarVecinosVivos(i, j);
                Celula celulaActual = tablero[i][j];
                nuevoTablero[i][j] = new Celula();

                if (celulaActual.estaViva()) {
                    if (vecinos >= 2 && vecinos <= 4) {
                        nuevoTablero[i][j].nacer();
                        for (int edad = 0; edad < celulaActual.getEdad(); edad++) {
                            nuevoTablero[i][j].envejecer();
                        }
                    }
                } else {
                    if (vecinos == 3) {
                        nuevoTablero[i][j].nacer();
                    }
                }

                if (random.nextDouble() < PROBABILIDAD_EVENTO_ALEATORIO) {
                    if (nuevoTablero[i][j].estaViva()) {
                        nuevoTablero[i][j].morir();
                    } else {
                        nuevoTablero[i][j].nacer();
                    }
                }
            }
        }

        tablero = nuevoTablero;
        generacion++;
    }

    /**
     * Establece una célula viva en una posición específica.
     */
    public void setCelulaViva(int fila, int col) {
        if (fila >= 0 && fila < filas && col >= 0 && col < columnas) {
            tablero[fila][col].nacer();
        }
    }

    /**
     * Inicializa el tablero con un patrón aleatorio.
     */
    public void inicializarAleatorio(double densidad) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (random.nextDouble() < densidad) {
                    tablero[i][j].nacer();
                }
            }
        }
    }

    /**
     * Muestra el tablero en la consola.
     */
    public void mostrar() {
        System.out.println("\n=".repeat(columnas * 2 + 2));
        System.out.println("Generación: " + generacion);
        System.out.println("=".repeat(columnas * 2 + 2));

        for (int i = 0; i < filas; i++) {
            System.out.print("║");
            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println("║");
        }

        System.out.println("=".repeat(columnas * 2 + 2));
    }

    public int getGeneracion() {
        return generacion;
    }

    public int contarCelulasVivas() {
        int vivas = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j].estaViva()) {
                    vivas++;
                }
            }
        }
        return vivas;
    }
}
