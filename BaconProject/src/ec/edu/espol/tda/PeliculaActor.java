/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.tda;

/**
 * @author Tiffy
 */
public class PeliculaActor {
    private int idPelicula;
    private int idActor;
    private Actor actor;
    private Pelicula pelicula;

    public PeliculaActor(int idp, int ipa) {
        this.idPelicula = idp;
        this.idActor = ipa;
        actor = null;
        pelicula = null;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public int getIdActor() {
        return idActor;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idPelicula;
        hash = 67 * hash + this.idActor;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof PeliculaActor)){
            return false;
        }   
        PeliculaActor e = (PeliculaActor)o;
        return ( this.idActor == e.getIdActor() &&
                 this.idPelicula == e.getIdPelicula() && 
                 this.actor.equals(e.getActor()) &&
                 this.pelicula.equals(e.getPelicula()));
    }

    @Override
    public String toString() {
        return idPelicula + "-" + idActor + " " + actor + "--" + pelicula;
    }
}
