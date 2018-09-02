package ec.edu.espol.tda;

import com.fxgraph.cells.AbstractCell;
import com.fxgraph.graph.Graph;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class ActorCell extends AbstractCell {

    private StackPane graphic;

    public ActorCell(String name) {
        graphic = new StackPane();
        graphic.getChildren().addAll(new Label(name));
    }

    @Override
    public Region getGraphic(Graph graph) {
        return graphic;
    }
}
