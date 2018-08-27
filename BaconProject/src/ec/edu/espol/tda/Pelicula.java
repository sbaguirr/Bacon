package ec.edu.espol.tda;

import java.util.HashSet;
import java.util.Set;

public class Pelicula {

    private int id;
    private String nombrePelicula;
    private String anio;
    private Set<Actor> actores;

    public Pelicula(int id, String nombrePelicula, String anio) {
        this.id = id;
        this.nombrePelicula = nombrePelicula;
        this.anio = anio;
        this.actores = new HashSet<>();
    }


    public Set<Actor> getActores() {
        return actores;
    }

}
