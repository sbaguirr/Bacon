package ec.edu.espol.tda;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
  
    public List<Pelicula> CargarEntradasList(String filename) throws IOException{
        List<Pelicula> lista = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "ISO-8859-1"))) {
            String cadena = in.readLine();
            while ((cadena = in.readLine()) != null) {
                String[] p = cadena.split("\\|");
                lista.add(new Pelicula(Integer.valueOf(p[0]), p[1]));                
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
             System.out.println(ex.getMessage());
        }
        return lista;
    }
    
    public Map<String, Pelicula> CargarEntradasMap(String filename) throws IOException{
        Map<String, Pelicula> mapa = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "ISO-8859-1"))) {
            String cadena = in.readLine();
            while ((cadena = in.readLine()) != null) {
                String[] st = cadena.split("\\|");
                mapa.put(st[0], new Pelicula(Integer.valueOf(st[0]), st[1]));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
             System.out.println(ex.getMessage());
        }
        return mapa;
    }

    @Override
    public String toString() {
        return "{" + "id: " + this.id + ", título: " + this.nombrePelicula + '}';
    }

}
