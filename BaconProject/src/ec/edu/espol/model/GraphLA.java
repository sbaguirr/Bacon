package ec.edu.espol.model;

import java.util.*;

public class GraphLA<E> {

    private Set<Vertex<E>> actores;
    private Vertex<E> last;
    private boolean calculadoDijkstra;
    private boolean dirigido;

    public Set<Vertex<E>> getActores() {
        return actores;
    }

    public void setActores(Set<Vertex<E>> actores) {
        this.actores = actores;
    }

    public Vertex<E> getLast() {
        return last;
    }

    public void setLast(Vertex<E> last) {
        this.last = last;
    }

    public boolean isCalculadoDijkstra() {
        return calculadoDijkstra;
    }

    public void setCalculadoDijkstra(boolean calculadoDijkstra) {
        this.calculadoDijkstra = calculadoDijkstra;
    }

    public boolean isDirigido() {
        return dirigido;
    }

    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }

    
    public GraphLA(boolean dirigido) {
        this.actores = new HashSet<>();
        this.last = null;
        this.calculadoDijkstra = false;
        this.dirigido = dirigido;
    }

    public boolean addVertex(E data) {
        if (data == null || this.contains(data)) {
            return false;
        }
        return actores.add(new Vertex<>(data));
    }

    public boolean contains(E data) {
        if (data != null) {
            for (Vertex c : actores) {
                if (c.getData().equals(data)) {
                    return true;
                }
            }
        }
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

    public boolean addEdge(E origen, E destino, Object data) {
        Vertex<E> vo = searchVertex(origen);
        Vertex<E> vd = searchVertex(destino);
        if (vo == null || vd == null) {
            return false;
        }
        Edge<E> a = new Edge<>(vo, vd, data);
        if (vo.getEdgeList().contains(a)) {
            return false;
        }
        vo.getEdgeList().add(a);
        if (!this.dirigido) {
            Edge<E> b = new Edge<>(vd, vo, data);
            vd.getEdgeList().add(b);
        }
        return true;
    }


    private void dijkstraInternal(Vertex<E> origen) {
        desmarcarVertices();
        reiniciarDistancias();
        origen.setDistancia(0);
        PriorityQueue<Vertex<E>> colaVertices = new PriorityQueue<>((Vertex<E> p1, Vertex<E> p2) -> p1.getDistancia() - p2.getDistancia());
        colaVertices.offer(origen);
        while (!colaVertices.isEmpty()) {
            Vertex<E> actual = colaVertices.poll();
            if (actual.isVisitado()) {
                continue;
            }
            for (Edge<E> arco : actual.getEdgeList()) {
                Vertex<E> destino = arco.getDestino();
                int nuevaDistancia = actual.getDistancia() + 1;
                if (destino.getDistancia() > nuevaDistancia) {
                    destino.setDistancia(nuevaDistancia);
                    destino.setAntecesor(actual);
                    colaVertices.offer(destino);
                }

            }
            actual.setVisitado(true);
        }
        desmarcarVertices();
        this.setLast(origen);
        this.setCalculadoDijkstra(true);
    }

    private void desmarcarVertices() {
        actores.forEach(v -> v.setVisitado(false));
    }

    private void reiniciarDistancias() {
        actores.forEach(v -> {
            v.setDistancia(Integer.MAX_VALUE);
            v.setAntecesor(null);
        });
    }

    /**
     * BFS para obtener el camino minimo creo que falta lo de limpiar vertices
     *
     * @param data
     */
    private void bfs(E data) {
        Vertex<E> v = searchVertex(data);
        if (v != null && !this.isEmpty()) {
            desmarcarVertices();
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

    @Override
    public String toString() {
        StringBuilder g = new StringBuilder();
        g.append("Vertices \n");
        for (Vertex<E> t : this.actores) {
            g.append(t.toString());
            g.append("\n -------");

            for (Edge<E> y : t.getEdgeList()) {
                g.append("\n Arcos \n");
                g.append(y.toString()).append("\n");
            }
            g.append("\n");
        }
        return g.toString();
    }

    public int caminoMinimoDijkstra(E origen, E destino) {
        final Vertex<E> o = searchVertex(origen);
        final Vertex<E> d = searchVertex(destino);
        if (o == null || d == null) {
            return -1;
        } else {
            this.dijkstraInternal(o);
            return caminoMinimoDijkstra(o, d);
        }

    }

    private int caminoMinimoDijkstra(Vertex<E> origen, Vertex<E> destino) {
        if (origen.equals(destino)) {
            return 0;
        } else {
            return 1 + caminoMinimoDijkstra(origen, destino.getAntecesor());
        }
    }

    public List<Edge<E>> recorridoCaminoMinimo(E origen, E destino,Boolean dijsktra) {
        List<Vertex<E>> l = new LinkedList<>();
        List<Edge<E>> lista = new LinkedList<>();
        Vertex<E> vo = this.searchVertex(origen);
        Vertex<E> vd = this.searchVertex(destino);
        if (vo == null || vd == null) {
            return lista;
        }
        metodo(dijsktra, vo);
        Vertex<E> tmp = vd;
        Deque<Vertex<E>> pila = new LinkedList<>();
        while ((tmp != null)) {
            pila.push(tmp);
            tmp = tmp.getAntecesor();
        }
        while (!pila.isEmpty()) {
            l.add(pila.pop());
        }
        for (Vertex<E> a : l) {
            for (Edge<E> s : a.getEdgeList()) {
                lista.add(s);
            }
        }
        ListIterator<Edge<E>> i = lista.listIterator();
        while (i.hasNext()) {
            if (!l.contains(i.next().getDestino())) {
                i.remove();
            }
        }
        return lista;
    }
     private void metodo(Boolean dijkstra, Vertex<E> vo) {
        if (dijkstra) {
            dijkstraInternal(vo);
        } else {
            this.bfs(vo.getData());
        }
    }
}
