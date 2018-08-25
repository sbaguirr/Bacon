package model;

import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.Pelicula;

import java.util.List;

public interface GraphAPI {

    List<Edge> caminoBFS(Actor a, Actor b);
    List<Edge> caminoDijkstra(Actor a, Actor b);
    void addActor(Actor a);
    void addPeliculaComun(Actor a, Actor b, Pelicula p);

}
