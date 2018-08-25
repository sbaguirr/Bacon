package model;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GraphLA<E> {

    private Set<Vertex<E>> actores;
    private Vertex<E> last;
    private boolean calculadoDijkstra;

    public GraphLA() {
        actores = new HashSet<>();
        last = null;
        calculadoDijkstra = false;
    }

    public boolean addVertex(E data) {
        return false;
    }

    public boolean removeVertex(E data) {
        return false;
    }

    public boolean addEdge(E origen, E destino, Object data) {
        return false;
    }

    public boolean removeEdge(E origen, E destino) {
        return false;
    }

    private void dijkstra_internal(Vertex<E> origen) {
        desmarcarVertices();
        reiniciarDistancias();
        Deque<Vertex<E>> colaVertices = new LinkedList<>();
        colaVertices.offer(origen);
        while (!colaVertices.isEmpty()) {
            Vertex<E> actual = colaVertices.poll();
            for (Vertex<E> adj : actual.getAdj()) {
                adj.setDistancia(actual.getDistancia() + 1);
                adj.setAntecesor(actual);
                colaVertices.offer(adj);
            }
            actual.setVisitado(true);
        }
        desmarcarVertices();
    }

    private void desmarcarVertices() {
        actores.forEach((v) -> v.setVisitado(false));
    }

    private void reiniciarDistancias() {
        actores.forEach(v -> {
            v.setDistancia(Integer.MAX_VALUE);
            v.setAntecesor(null);
        });
    }


}
