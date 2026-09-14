package gestionAparcamientos;

import java.util.ArrayList;

public class Empresa {

    private String nombre;
    private ArrayList<aparcamientos> aparcamientos;

    public Empresa(String nombre) {
        this.nombre = nombre;
        aparcamientos = new ArrayList<>();
    }

    public void registrarAparcamiento(aparcamientos a) {
        aparcamientos.add(a);
    }

    public void buscarCiudad(String ciudad) {

        for (aparcamientos a : aparcamientos) {
            if (a.getCiudad().equalsIgnoreCase(ciudad)) {
                System.out.println(a.getNombre());
            }
        }
    }

    public void mostrarAparcamientos() {

        for (aparcamientos a : aparcamientos) {
            System.out.println(
                a.getNombre() + " - Coches: " +
                a.getCochesAparcados()
            );
        }
    }
}
