package model;

import ec.edu.espol.tda.Actor;

public abstract class Edge<E,T> {

    private E origen,destino;
    private T data;


    public E getOrigen(){
        return origen;
    }
    public E getDestino(){
        return destino;
    }
    public  T getData(){
        return data;
    }


}
