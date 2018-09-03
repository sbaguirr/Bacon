package ec.edu.espol.vistas;

import com.fxgraph.graph.Graph;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.RandomLayout;
import ec.edu.espol.model.Edge;
import ec.edu.espol.model.Vertex;
import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.ActorCell;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.Consumer;

public class VistaCamino {

    private List<Edge<Actor>> camino;

    public VistaCamino(List<Edge<Actor>> camino) {
        this.camino = camino;
    }

    /**
     *  Metodo encargado de mostrar el camino encapsulado por la clase
     */
    public void displayCamino() {
        Graph g =  new Graph();
        Model m = g.getModel();
        g.beginUpdate();
        camino.forEach(new Consumer<Edge<Actor>>() {
            private ActorCell before = null;

            @Override
            public void accept(Edge<Actor> edge) {
                final Vertex<Actor> origen = edge.getOrigen();
                final Vertex<Actor> destino = edge.getDestino();
                if(before== null){
                    before = new ActorCell(origen.getData().getNombres());
                    m.addCell(before);
                } else {
                    ActorCell after = new ActorCell(destino.getData().getNombres());
                    m.addCell(after);
                    m.addEdge(before,after);
                    before = after;
                }

            }
        });
        g.endUpdate();
        g.layout(new RandomLayout());
        final Scene grafo = new Scene(g.getCanvas(),640,480);
        final Stage ventana = new Stage();
        ventana.setScene(grafo);
        ventana.show();

       
    }

}
