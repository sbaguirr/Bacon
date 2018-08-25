package model;

import java.util.List;

public class Vertex<E> {
    private E data;
    private List<Edge> edgeList;

    public Vertex(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

}
