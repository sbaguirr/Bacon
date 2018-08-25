package ec.edu.espol.model;

import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.Pelicula;

import java.util.List;

public class Oracle implements OracleAPI {

    private GraphLA<Actor> grafo;

    public Oracle(GraphLA<Actor> grafo) {
        this.grafo = grafo;
    }

    @Override
    public List<Pelicula> caminoBFS(Actor a, Actor b) {
        return null;
    }

    @Override
    public List<Pelicula> caminoDijkstra(Actor a, Actor b) {
        return null;
    }

}
