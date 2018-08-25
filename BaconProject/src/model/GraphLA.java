package model;

import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.Pelicula;

import java.util.List;
import java.util.Set;

public class GraphLA implements GraphAPI {

    private Set<Actor> actores;
    private Actor ultimoActorDijkstra;
    private boolean calculadoDijkstra;


    @Override
    public List<Pelicula> caminoBFS(Actor a, Actor b) {
        return null;
    }

    @Override
    public List<Pelicula> caminoDijkstra(Actor a, Actor b) {
        return null;
    }

    private List<Pelicula> caminoInternal(Actor actor, Actor actor1) {
        return null;
    }

    @Override
    public void addActor(Actor a) {

    }

    @Override
    public void addPeliculaComun(Actor a, Actor b, Pelicula p) {

    }
}
