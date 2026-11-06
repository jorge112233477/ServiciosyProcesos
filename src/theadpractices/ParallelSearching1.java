package theadpractices;
 
import java.util.Arrays;
import java.util.Random;
 
public class ParallelSearching1 {
 
    static Random aleatorio = new Random();
 
    // Variable compartida: cuando un hilo encuentra el número la pone en true
    // y el otro hilo se detiene. "volatile" hace que ambos vean el cambio al instante.
    static volatile boolean encontrado = false;
 
    public static void main(String[] args) throws InterruptedException {
 
        // Paso 1: crear el array con números aleatorios
        int[] numeros = new int[100];
 
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = aleatorio.nextInt(1, 150);
        }
 
        System.out.println(Arrays.toString(numeros));
 
        int num = 34;
 
        // Paso 2: dividir el array en dos mitades (0-49 y 50-99)
        int mitad = numeros.length / 2;
 
        // Paso 3: hilo 1 busca en la primera mitad
        Thread t1 = new Thread(new Runnable() {
 
            @Override
            public void run() {
                for (int i = 0; i < mitad; i++) {
 
                    // Si el otro hilo ya lo encontró, se detiene
                    if (encontrado) {
                        System.out.println("Hilo 1 se detiene, el otro hilo ya lo encontró");
                        return;
                    }
 
                    // Si lo encuentra, avisa al otro hilo
                    if (numeros[i] == num) {
                        encontrado = true;
                        System.out.println("Hilo 1: encontrado el " + num + " en la posición " + i);
                        return;
                    }
                }
            }
        });
 
        // Paso 4: hilo 2 busca en la segunda mitad (igual que el hilo 1)
        Thread t2 = new Thread(new Runnable() {
 
            @Override
            public void run() {
                for (int i = mitad; i < numeros.length; i++) {
 
                    if (encontrado) {
                        System.out.println("Hilo 2 se detiene, el otro hilo ya lo encontró");
                        return;
                    }
 
                    if (numeros[i] == num) {
                        encontrado = true;
                        System.out.println("Hilo 2: encontrado el " + num + " en la posición " + i);
                        return;
                    }
                }
            }
        });
 
        // Paso 5: arrancar los dos hilos a la vez
        t1.start();
        t2.start();
 
        // Paso 6: esperar a que los dos terminen
        t1.join();
        t2.join();
 
        // Paso 7: si ninguno lo encontró, mostrar el mensaje
        if (!encontrado) {
            System.out.println("El " + num + " no está en el array");
        }
    }
}