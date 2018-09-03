package ec.edu.espol.tda;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Pelicula {

    private int id;
    private String nombrePelicula;
    
    public Pelicula(int id, String nombrePelicula) {
        this.id = id;
        this.nombrePelicula = nombrePelicula;
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

    public static Pelicula buscarPelicula(Map<Integer, String> mapaPelicula, Integer id) {
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
    
    public static String buscarNombrePelicula(Map<Integer, String> mapaPelicula, Integer id) {
        String nombrePelicula = mapaPelicula.get(id);
        if (nombrePelicula != null) {
            return  nombrePelicula;
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.nombrePelicula);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Pelicula)){
            return false;
        }   
        Pelicula e = (Pelicula)o;
        return ( this.id == e.getId() &&
                 this.nombrePelicula.equals(e.getNombrePelicula()));
    }
}