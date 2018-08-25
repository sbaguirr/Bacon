package ec.edu.espol.model;

import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.Pelicula;

import java.util.List;

public interface OracleAPI {

    List<Pelicula> caminoBFS(Actor a, Actor b);
    List<Pelicula> caminoDijkstra(Actor a, Actor b);


}
