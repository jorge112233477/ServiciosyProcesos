package theadpractices;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelSearching2 {

    static Random aleatorio = new Random();

    public static void main(String[] args) throws InterruptedException {

        // Paso 1: crear el array grande con números aleatorios
        int tamanoArray = 10_000_000;
        int[] numeros = new int[tamanoArray];

        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = aleatorio.nextInt(1_000_000);
        }

        int num = 500_000;

        // Paso 2 (TODO 1): consultar cuántos núcleos tiene la máquina
        int numProcesadores = Runtime.getRuntime().availableProcessors();

        // Si el array tiene menos elementos que núcleos, no tiene sentido
        // crear más hilos que elementos (y como mínimo, 1 hilo)
        int numHilos = Math.max(1, Math.min(numProcesadores, numeros.length));

        System.out.println("Ejecutando búsqueda paralela en " + numProcesadores
                + " núcleos con " + numHilos + " hilos...");

        // Flag atómico compartido: sustituye al "static volatile boolean"
        AtomicBoolean encontrado = new AtomicBoolean(false);

        // Paso 3 (TODO 2): calcular el tamaño de cada bloque
        int tamanoBloque = numeros.length / numHilos;

        long tiempoInicio = System.nanoTime();

        // Paso 4 (TODO 3): crear y arrancar los hilos en un bucle
        Thread[] hilos = new Thread[numHilos];

        for (int h = 0; h < numHilos; h++) {
            int inicio = h * tamanoBloque;

            // El último hilo se queda con el resto si la división no es exacta
            int fin = (h == numHilos - 1) ? numeros.length : inicio + tamanoBloque;

            hilos[h] = new Thread(
                    () -> buscarNumero(numeros, num, inicio, fin, encontrado),
                    "Hilo " + (h + 1));
            hilos[h].start();
        }

        // Paso 5 (TODO 4): esperar a que terminen todos
        for (Thread hilo : hilos) {
            hilo.join();
        }

        long tiempoFin = System.nanoTime();

        // Paso 6: resultado final
        if (!encontrado.get()) {
            System.out.println("El número " + num + " no se ha encontrado en el array.");
        }

        System.out.printf("Tiempo de búsqueda: %.3f ms%n", (tiempoFin - tiempoInicio) / 1_000_000.0);
    }

    // Cada hilo busca solo en su trozo [inicio, fin)
    public static void buscarNumero(int[] array, int num, int inicio, int fin, AtomicBoolean encontrado) {
        for (int i = inicio; i < fin; i++) {

            // Cancelación temprana: si otro hilo ya lo encontró, paramos
            if (encontrado.get()) {
                return;
            }

            if (array[i] == num) {
                // compareAndSet garantiza que solo UN hilo informe del hallazgo
                if (encontrado.compareAndSet(false, true)) {
                    System.out.println(Thread.currentThread().getName()
                            + ": encontrado el " + num + " en la posición " + i);
                }
                return;
            }
        }
    }
}
