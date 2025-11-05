public class Main {
    public static void main(String[] args) {
        System.out.println("=== JUEGO DE LA VIDA DE CONWAY ===\n");
        System.out.println("Reglas del juego:");
        System.out.println("- Nacimiento: posición vacía con 3 vecinos → nace célula");
        System.out.println("- Muerte por aislamiento: célula con < 2 vecinos → muere");
        System.out.println("- Muerte por sofocación: célula con > 4 vecinos → muere");
        System.out.println("- Supervivencia: célula con 2, 3 o 4 vecinos → sobrevive");
        System.out.println("- Eventos aleatorios: 10% de probabilidad de generación espontánea o muerte");
        System.out.println("- Mundo esférico: los bordes están conectados\n");
        System.out.println("Los números indican la edad de la célula, * indica edad >= 10\n");
        System.out.println("Presiona ENTER para comenzar la simulación...");

        try {
            System.in.read();
        } catch (Exception e) {
            System.err.println("Error al leer entrada");
        }

        JuegoDeLaVida juego = new JuegoDeLaVida(6, 6);
        juego.inicializarAleatorio(0.3);
        juego.mostrar();

        try {
            for (int i = 0; i < 20; i++) {
                Thread.sleep(1000);
                juego.evolucionar();
                juego.mostrar();
                System.out.println("Células vivas: " + juego.contarCelulasVivas());
            }
        } catch (InterruptedException e) {
            System.err.println("Simulación interrumpida");
        }

        System.out.println("\n=== FIN DE LA SIMULACIÓN ===");
    }
}