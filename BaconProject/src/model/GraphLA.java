package model;

import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.Pelicula;

import java.util.*;

public class GraphLA<E,T> {

    private Set<Vertex<E>> actores;
    private Vertex<E> last;
    private boolean calculadoDijkstra;

    public GraphLA() {
        actores = new HashSet<>();
        last = null;
        calculadoDijkstra = false;
    }


}
