package ec.edu.espol.model;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Vertice Origen: " + origen + " Destino: " + destino + " pelicula: " + data;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Edge)){
            return false;
        }   
        Edge<E> e = (Edge<E>)o;
        return ( this.origen.getData().equals(e.getDestino().getData()) &&
                 this.destino.getData().equals(e.getOrigen().getData()) &&
                 this.data.equals(e.getData()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.origen);
        hash = 79 * hash + Objects.hashCode(this.destino);
        hash = 79 * hash + Objects.hashCode(this.data);
        return hash;
    }
    
    
}
