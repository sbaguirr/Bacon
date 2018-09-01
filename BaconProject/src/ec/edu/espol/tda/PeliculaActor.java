/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.tda;

/**
 *
 * @author Tiffy
 */
public class PeliculaActor {
    private int idPelicula;
    private int idActor;
    
    public PeliculaActor(int idp, int ipa){
    this.idPelicula=idp;
    this.idActor=ipa;
    }

    public int getIdPelicula() {
        return idPelicula;
    }
    
    public int getIdActor() {
        return idActor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idPelicula;
        hash = 67 * hash + this.idActor;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PeliculaActor other = (PeliculaActor) obj;
        if (this.idPelicula != other.idPelicula) {
            return false;
        }
        if (this.idActor != other.idActor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idPelicula + "-" + idActor;
    }  
    
}
