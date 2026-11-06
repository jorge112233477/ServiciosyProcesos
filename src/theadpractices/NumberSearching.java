package theadpractices;
 
import java.util.Arrays;
import java.util.Random;
 
public class NumberSearching {
 
    static Random aleatorio = new Random();
 
    public static void main(String[] args) {
 
        // create an array initialized with random numbers
        int[] numeros = new int[100];
 
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = aleatorio.nextInt(1, 150);
        }
 
        System.out.println(Arrays.toString(numeros));
 
        int num = 34;
 
        // create a thread
        Thread t = new Thread(new Runnable() {
 
            @Override
            public void run() {
                System.out.println("this is a new task " + num);
 
                boolean encontrado = false;
                
                for (int i = 0; i < numeros.length; i++) {
                	
                    if (numeros[i] == num) {
                        System.out.println("Encontrado el " + num + " en la posición " + i);
                        encontrado = true;
                        break; 
                    }
                }
 
                
                if (!encontrado) {
                    System.out.println("El " + num + " no está en el array");
                }
            }
        });
 
        t.start();
    }
}
	
	
	
