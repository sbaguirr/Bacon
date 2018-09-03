/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.tda;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Tiffy
 */
public class Actor {

    private int id;
    private String nombres;

    public Actor(int id, String nom) {
        this.id = id;
        this.nombres = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Actor)){
            return false;
        }   
        Actor e = (Actor)o;
        return ( this.id == e.getId() &&
                 this.nombres.equals(e.getNombres()));
    }

    @Override
    public String toString() {
        return id + "-" + nombres;
    }
    public static Actor buscarActor(Map<Integer, String> mapaActor, Integer id) {
        String nombreActor = mapaActor.get(id);
        if (nombreActor != null) {
            return new Actor(id, nombreActor);
        }
        return null;
    }
    
    public static Actor buscarActorList(List<Actor> listActor, Integer id) {
        for(Actor a: listActor){
            if(a.getId()==id){
                return new Actor(id, a.getNombres());
            }
        }
        return null;
    }
    
    public static String buscarNombreActor(Map<Integer, String> mapaActor, Integer id) {
        String nombreActor = mapaActor.get(id);
        if (nombreActor != null) {
            return nombreActor;
        }
        return null;
    }
    
}
