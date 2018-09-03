package ec.edu.espol.tda;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
    
    public Pelicula(int id, String nombrePelicula) {
        this.id = id;
        this.nombrePelicula = nombrePelicula;
    }

    public Set<Actor> getActores() {
        return actores;
    }
   
    public Pelicula() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }
    
     public static Pelicula buscarPelicula(HashMap<Integer, String> mapaPelicula, Integer id) {
        String nombrePelicula = mapaPelicula.get(id);
        if (nombrePelicula != null) {
            return new Pelicula(id, nombrePelicula);
        }
        return null;
    }
    
    public static Pelicula buscarPeliculaList(List<Pelicula> listPeli, Integer id) {
        for(Pelicula a: listPeli){
        if(a.getId()==id){
        return new Pelicula(id, a.getNombrePelicula());
        }
        }
        return null;
    }
  
    @Override
    public String toString() {
        return this.id + "-" + this.nombrePelicula;
    }
    public static String buscarNombrePelicula(HashMap<Integer, String> mapaPelicula, Integer id) {
        String nombrePelicula = mapaPelicula.get(id);
        if (nombrePelicula != null) {
            return  nombrePelicula;
        }
        return null;
    }

}
