package theadpractices;
 
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
 
public class ParallelSearching3 {
 
    static Random aleatorio = new Random();
 
    public static void main(String[] args) throws InterruptedException {
 
        // Paso 1: crear el array grande con números aleatorios
        int tamanoArray = 10_000_000;
        int[] numeros = new int[tamanoArray];
 
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = aleatorio.nextInt(1_000_000);
        }
 
        int num = 500_000;
 
        // Paso 2: consultar cuántos núcleos tiene la máquina
        int numProcesadores = Runtime.getRuntime().availableProcessors();
 
        // Si el array tiene menos elementos que núcleos, no tiene sentido
        // crear más hilos que elementos (y como mínimo, 1 hilo)
        int numHilos = Math.max(1, Math.min(numProcesadores, numeros.length));
 
        System.out.println("Ejecutando búsqueda paralela en " + numProcesadores
                + " núcleos con " + numHilos + " hilos...");
 
        // Flag atómico compartido entre todas las tareas
        AtomicBoolean encontrado = new AtomicBoolean(false);
 
        // Paso 3: calcular el tamaño de cada bloque
        int tamanoBloque = numeros.length / numHilos;
 
        long tiempoInicio = System.nanoTime();
 
        // Paso 4: en lugar de crear Thread a mano, usamos un pool de hilos reutilizables
        ExecutorService executor = Executors.newFixedThreadPool(numHilos);
 
        for (int h = 0; h < numHilos; h++) {
            int inicio = h * tamanoBloque;
 
            // El último bloque se queda con el resto si la división no es exacta
            int fin = (h == numHilos - 1) ? numeros.length : inicio + tamanoBloque;
 
            // Tarea ejecutable definida con función flecha (lambda)
            Runnable tarea = () -> buscarNumero(numeros, num, inicio, fin, encontrado);
 
            // El executor asigna la tarea a uno de los hilos del pool
            executor.execute(tarea);
        }
 
        // Paso 5: no se aceptan más tareas y esperamos a que terminen todas
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
 
        long tiempoFin = System.nanoTime();
 
        // Paso 6: resultado final
        if (!encontrado.get()) {
            System.out.println("El número " + num + " no se ha encontrado en el array.");
        }
 
        System.out.printf("Tiempo de búsqueda: %.3f ms%n", (tiempoFin - tiempoInicio) / 1_000_000.0);
    }
 
    // Cada tarea busca solo en su trozo [inicio, fin)
    public static void buscarNumero(int[] array, int num, int inicio, int fin, AtomicBoolean encontrado) {
        for (int i = inicio; i < fin; i++) {
 
            // Cancelación temprana: si otra tarea ya lo encontró, paramos
            if (encontrado.get()) {
                return;
            }
 
            if (array[i] == num) {
                // compareAndSet garantiza que solo UNA tarea informe del hallazgo
                if (encontrado.compareAndSet(false, true)) {
                    System.out.println(Thread.currentThread().getName()
                            + ": encontrado el " + num + " en la posición " + i);
                }
                return;
            }
        }
    }
}
 