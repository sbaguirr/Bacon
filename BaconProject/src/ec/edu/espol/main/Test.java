/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.Pelicula;
import ec.edu.espol.tda.PeliculaActor;
import ec.edu.espol.utils.Utils;
import java.util.ArrayList;

/**
 *
 * @author Tiffy
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //BORRAR ESTA CLASE LUEGO
        /* si vale
        HashMap<Integer, String> actores = Utils.cargarActoresMap();
        HashMap<Integer, String> peliculas = Utils.CargarPeliculasMap();
        HashMap<Integer, List<PeliculaActor>> peliculasActor = Utils.cargarPeliActoresMap();
        PeliculaActor.vincularPeliculaActor(peliculasActor, actores, peliculas);
        System.out.println(peliculasActor);
        */
        
        ArrayList<Actor> a = Utils.cargarActoresArrayList();
        ArrayList<Pelicula> p = Utils.CargarPeliculasArrayList();
        ArrayList<PeliculaActor> apa = Utils.cargarPeliActorArrayList();
        Utils.vincularPeliculaActorList(apa, a, p);
        System.out.println(apa);
    }
    
}