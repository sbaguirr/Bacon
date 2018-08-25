package model;

public class Edge<E> {

    private Vertex<E> origen, destino;
    private Object data;

    public Edge(Vertex<E> origen, Vertex<E> destino, Object data) {
        this.origen = origen;
        this.destino = destino;
        this.data = data;
    }

    public Vertex<E> getOrigen() {
        return origen;
    }

    public Vertex<E> getDestino() {
        return destino;
    }

    public Object getData() {
        return data;
    }


}
