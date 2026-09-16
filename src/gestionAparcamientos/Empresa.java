package gestionAparcamientos;

import java.util.HashMap;

public class Empresa {

    private String nombre;

    private HashMap<String, aparcamientos> aparcamientos;

    public Empresa(String nombre) {

        this.nombre = nombre;

        aparcamientos = new HashMap<>();

    }

    public void registrarAparcamiento(aparcamientos a) {

        aparcamientos.put(a.getNombre(), a);

    }

    public void buscarCiudad(String ciudad) {

        for (aparcamientos a : aparcamientos.values()) {

            if (a.getCiudad().equalsIgnoreCase(ciudad)) {

                System.out.println(a.getNombre());

            }

        }

    }

    public void mostrarAparcamientos() {

        for (aparcamientos a : aparcamientos.values()) {

            System.out.println(
                a.getNombre() + " - Coches: " +
                a.getCochesAparcados()
            );

        }

    }

}