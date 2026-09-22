package gestionAparcamientos;

public class ParkingJob extends Thread {
	
	
	private aparcamientos parking;
	
	// initialize the parking
	// object through the constructor
	public ParkingJob(aparcamientos parking1, String name) {
		super(name);
		Object parking;
		this.parking = parking1;
	}

	@Override
	public void run() {
		System.out.println("this is a parallel job");
		System.out.println(parking);
		
		
		
	}
	
	
	

}
