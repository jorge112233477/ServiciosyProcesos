package gestionAparcamientos;

public class ParallelParkAccess {

	
	public static void main(String[] args) {
		
		
		
		
		aparcamientos parking1 = new aparcamientos("park1", "100", 0);
		aparcamientos parking2 = new aparcamientos("park1", "100", 0);
		
		Thread t1 = new ParkingJob(parking1);
		Thread t2 = new ParkingJob(parking2);
		
		t1.start();
		t2.start();
		
		Empresa empresa1 = new Empresa("Manolo");
		

		
		
		
		
	}
	
}
