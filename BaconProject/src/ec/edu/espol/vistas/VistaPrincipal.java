package ec.edu.espol.vistas;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class VistaPrincipal {
    private boolean usingDijkstra;

    @FXML
    public void initialize() {

    }

    public void handleAlgorithm(Event event) {
        final Button boton = (Button) event.getSource();
        if (boton.getText().equals("Dijkstra")) {
            usingDijkstra = true;
        } else {
            usingDijkstra = false;
        }
        //TODO: pasar a la siguente ventana.
    }

}
