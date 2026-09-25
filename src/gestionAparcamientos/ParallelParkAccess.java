package gestionAparcamientos;



public class ParallelParkAccess {

	
	public static void main(String[] args) {
		
		
		
		
		aparcamientos parking1 = new aparcamientos("park1", "100", 1000000000);
		aparcamientos parking2 = new aparcamientos("park1", "100", 1000000000);
		
		
		int numOfThread = 2000;
		Thread[] threads = new Thread[numOfThread];
		
		for (int i = 0; i < numOfThread; i++) {
			Thread t1 = 
					new ParkingJob(parking1, "Jorge" + i);
			threads[i] = t1;
		}
		
		
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
			
		}
		
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
		Thread t1 = new ParkingJob(parking1, "coche");
		Thread t2 = new ParkingJob(parking1, "coche1");
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t1.start();
		t2.start();
		
		Empresa empresa1 = new Empresa("Manolo");
		
		System.out.println(parking1.getCochesAparcados());
		

		
		
		
		
	}
	
}
