package ec.edu.espol.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Vertex<E> {
    private E data;
    private List<Edge<E>> edgeList;
    private Vertex<E> antecesor;
    private int distancia;
    private boolean visitado;

    public Vertex(E data) {
        this.data = data;
        this.edgeList = new LinkedList<>();
        this.antecesor = null;
        this.distancia = Integer.MAX_VALUE;
        this.visitado = false;
    }

    public E getData() {
        return data;
    }

    public List<Edge<E>> getEdgeList() {
        return edgeList;
    }


    public Vertex<E> getAntecesor() {
        return antecesor;
    }

    public void setAntecesor(Vertex<E> antecesor) {
        this.antecesor = antecesor;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public List<Vertex<E>> getAdj(){
        return this.edgeList.stream().map(Edge::getDestino).collect(Collectors.toList());
    }
}
