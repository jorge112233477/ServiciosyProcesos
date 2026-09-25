package gestionAparcamientos;

public class aparcamientos {

    private String ciudad;
    private String nombre;
    private int capacidad;
    private int coches;

    public aparcamientos(String ciudad, String nombre, int capacidad) {
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.capacidad = capacidad;
        coches = 0;
    }

    public String getCiudad() {
        return ciudad;
    }

    public synchronized void entrarCoche() {
        if (coches < capacidad) {
            coches++;
          //  System.out.println(coches); 
        } else {
            System.out.println("Aparcamiento lleno");
        }
    }

    public synchronized void salirCoche() {
        if (coches > 0) {
            coches--;
            System.out.println("Sale un coche. Coches: " + coches);
        } else {
            System.out.println("No hay coches");
        }
    }

    public int getCochesAparcados() {
        return coches;
    }

    public String getNombre() {
        return nombre;
    }
}


