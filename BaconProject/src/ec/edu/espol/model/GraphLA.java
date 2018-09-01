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
    private Vertex<E> searchVertex(E data) {
        for (Vertex<E> c : actores) {
            if (c.getData().equals(data)) {
                return c;
            }
        }
        return null;
    }
    
     private boolean isEmpty() {
        return this.actores.isEmpty();
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
            if (actual.isVisitado()) {
                continue;
            }
            for (Edge<E> arco : actual.getEdgeList()) {
                Vertex<E> destino = arco.getDestino();
                int nueva_distancia = actual.getDistancia() + 1;
                if (destino.getDistancia() > nueva_distancia) {
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
    

    /**
     * BFS para obtener el camino minimo
     * creo que falta lo de limpiar vertices 
     * @param data 
     */
    private void bfs(E data) {
        Vertex<E> v = searchVertex(data);
        if (v != null && !this.isEmpty()) {
            Queue<Vertex<E>> cola = new LinkedList<>();
            v.setVisitado(true);
            v.setDistancia(0);
            cola.offer(v);
            while (!cola.isEmpty()) {
                Vertex<E> vi = cola.poll();
                for (Edge<E> e : vi.getEdgeList()) {
                    if (!e.getDestino().isVisitado()) {
                        e.getDestino().setVisitado(true);
                        e.getDestino().setDistancia(vi.getDistancia() + 1);
                        e.getDestino().setAntecesor(vi);
                        cola.offer(e.getDestino());
                    }
                }
            }
        }
    }

    /**
     * @param data, el actor que escriba el usuario
     * @param destino, kevin Bacon
     * @return el camino minimo desde el actor a kevin
     */
    public int caminoMinimo(E data, E destino) {
        this.bfs(data);
        int u = -1;
        for (Vertex<E> y : this.actores) {
            if (y.getData().equals(destino)) {
                u = y.getDistancia();
            }
        }
        return u;
    }

}
