package ec.edu.espol.model;

import java.util.*;

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
        PriorityQueue<Vertex<E>> colaVertices = new PriorityQueue<>();
        colaVertices.offer(origen);
        while (!colaVertices.isEmpty()) {
            Vertex<E> actual = colaVertices.poll();
            if (actual.isVisitado())
                continue;
            for (Edge<E> arco: actual.getEdgeList()) {
                Vertex<E> destino = arco.getDestino();
                int nueva_distancia = actual.getDistancia() + 1;
                if(destino.getDistancia() > nueva_distancia){
                    destino.setDistancia(nueva_distancia);
                    destino.setAntecesor(actual);
                }
                colaVertices.offer(destino);
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
