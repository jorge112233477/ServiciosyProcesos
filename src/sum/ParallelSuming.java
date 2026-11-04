package sum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelSuming {
	
	
	public static void main(String[] args) {
		
		// create a thread pool object, the threads in the pool
		ExecutorService executor =
		Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		System.out.println("the number of processors: " + Runtime.getRuntime().availableProcessors());
		
		// define a runnable task for a thread
		Runnable task = () -> {
			
			System.out.println("thread name is : " + Thread.currentThread().getName());
			
			
		};
		
		//submit task tp the thread pool
		executor.submit(task);
		executor.submit(task);
		executor.submit(task);
		executor.submit(task);
		for (int i = 0; i < 20; i++) {
			executor.submit(task);
			
		}
		
		
		
		Thread thread;
	}

}
