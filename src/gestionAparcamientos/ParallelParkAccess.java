package gestionAparcamientos;

import java.util.Iterator;

public class ParallelParkAccess {

	
	public static void main(String[] args) {
		
		
		
		
		aparcamientos parking1 = new aparcamientos("park1", "100", 0);
		aparcamientos parking2 = new aparcamientos("park1", "100", 0);
		
		Thread[] threads = new Thread[657];
		int numOfThread = 40;
		
		for (int i = 0; i < numOfThread; i++) {
			Thread t1 = 
					new ParkingJob(parking1, "Jorge" + i);
			threads[i] = t1;
		}
		
		
		
		
		Thread t1 = new ParkingJob(parking1, null);
		Thread t2 = new ParkingJob(parking2, null);
		
		t1.start();
		t2.start();
		
		Empresa empresa1 = new Empresa("Manolo");
		

		
		
		
		
	}
	
}
