package ec.edu.espol.vistas;

import ec.edu.espol.model.Oracle;
import ec.edu.espol.model.OracleAPI;
import ec.edu.espol.utils.Utils;
import javafx.event.Event;
import javafx.scene.control.TextField;

public class VistaOraculo {

    public TextField actorB;
    private OracleAPI oraculo;
    private boolean isDijkstra;

    public VistaOraculo(boolean isDijkstra) {
        oraculo = new Oracle(Utils.generarOraculo("ARCHIVOS"));
        this.isDijkstra = isDijkstra;
    }


    public void handleBacon(Event event) {
        //TODO: calcular la distancia usando la API.
    }
}
