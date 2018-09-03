/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ec.edu.espol.model.Edge;
import ec.edu.espol.model.GraphLA;
import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.Pelicula;
import ec.edu.espol.utils.Utils;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.StageStyle;

/**
 *
 * @author ROSA
 */
public class PaneOraculo {

    private final BorderPane root;
    private Label titulo;
    private Label numeroBacon;
    private Label n;
    private Button calcular;
    private Button regresar;
    private TextField actor;
    private boolean dijkstra;
    private GraphLA grafo;
    private final String nombreKBacon;
    private final Map<Integer, String> peliculas;
    private final Map<Integer, String> actores;
    private final Map<Integer, List<Integer>> peliculaActor;
    private TextArea v;

    public Pane getRoot() {
        return root;
    }

    public PaneOraculo(Boolean di) {
        root = new BorderPane();
        nombreKBacon = "kevin bacon";
        this.dijkstra = di;
        peliculas = Utils.cargarPeliculasMap();
        actores = Utils.cargarActoresMap();
        peliculaActor = Utils.cargarPeliActoresMapNew();
        grafo = Utils.generarGrafo(actores, peliculaActor);
        inicializarObjetos();
        llamarMetodos();
    }

    private void llamarMetodos() {
        crearSeccionTitulo();
        seccionNumeroBacon();
        calcularBacon();
    }

    private void crearSeccionTitulo() {
        Label titleLbl = new Label("Calcular el número de Bacon de alguien");
        titleLbl.setPrefSize(610, 100);
        titleLbl.setStyle("-fx-font: 25 Verdana; -fx-background-color: #21034B; -fx-text-fill: #F5F5F5;");
        titleLbl.setAlignment(Pos.CENTER);
        titleLbl.setPadding(new Insets(10, 10, 10, 10));
        VBox vbox = new VBox();
        HBox h = new HBox();
        HBox hbox = new HBox();
        h.getChildren().addAll(calcular, regresar);
        h.setSpacing(20);
        h.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(titulo, actor);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(titleLbl, hbox, h,v);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);
        root.setTop(vbox);
    }

    private void inicializarObjetos() {
        titulo = new Label("Distancia de Kevin Bacon hasta: ");
        titulo.setStyle("-fx-font: 14 Verdana;");
        n = new Label("El n° Bacon es: ");
        v = new TextArea();
        v.setEditable(false);
        numeroBacon = new Label();
        n.setStyle("-fx-font: 15 Verdana; -fx-background-color: #D500FF; -fx-text-fill: #F5F5F5;");
        n.setPrefSize(200, 50);
        n.setAlignment(Pos.CENTER);
        numeroBacon.setStyle("-fx-font: 15 Verdana; -fx-background-color: #006CFF; -fx-text-fill: #F5F5F5;");
        numeroBacon.setPrefSize(75, 50);
        numeroBacon.setAlignment(Pos.CENTER);
        actor = new TextField();
        actor.setPrefSize(250, 30);
        calcular = new Button("Calcular");
        calcular.setStyle("-fx-font: 14 Verdana; -fx-font-weight: bold; -fx-base: #A6FF00; -fx-text-fill: #F5F5F5;");
        calcular.setPrefSize(110, 40);
        regresar = new Button("Limpiar");
        regresar.setStyle("-fx-font: 14 Verdana; -fx-font-weight: bold; -fx-base: #FFDC00; -fx-text-fill: #F5F5F5;");
        regresar.setPrefSize(110, 40);
        regresar.setOnAction(e -> {
            this.actor.setText("");
            this.numeroBacon.setText("");
            this.v.setText("");
        });
    }

    private void seccionNumeroBacon() {
        HBox hn = new HBox();
        hn.setSpacing(15);
        hn.getChildren().addAll(this.n, this.numeroBacon);
        hn.setAlignment(Pos.CENTER);
        hn.setPadding(new Insets(0, 10, 30, 10));
        root.setBottom(hn);
    }

    private void dialogoAdvertencia() {
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle("Error");
        advertencia.setContentText("Debe asegurarse de llenar todos los campos");
        advertencia.setHeaderText(null);
        advertencia.initStyle(StageStyle.UTILITY);
        advertencia.showAndWait();
    }

    private void actorNoExiste() {
        Alert advertencia = new Alert(Alert.AlertType.ERROR);
        advertencia.setTitle(" :(  ");
        advertencia.setContentText("El actor buscado no existe");
        advertencia.setHeaderText(null);
        advertencia.initStyle(StageStyle.UTILITY);
        advertencia.showAndWait();
    }

    public void calcularBacon() {
        calcular.setOnAction(e -> {
            String act = this.actor.getText().toLowerCase();
            if (!act.equals("")) {
                int id = searchIdActor(act);
                if (id != 0) {
                    if (this.dijkstra) {
                        v.clear();
                        String d = String.valueOf(calcularDijkstra(id));
                        this.numeroBacon.setText(d);
                        mostrarCamino(true, id);
                    } else {
                        v.clear();
                        String b = String.valueOf(calcularBFS(id));
                        this.numeroBacon.setText(b);
                        mostrarCamino(false, id);
                    }
                } else {
                    this.actorNoExiste();
                }
            } else {
                dialogoAdvertencia();
            }
        });
    }

    private int searchIdActor(String actor) {
        int id = 0;
        Map<Integer, String> mapa = this.actores;
        for (Map.Entry<Integer, String> m : mapa.entrySet()) {
            if (m.getValue().equals(actor)) {
                return m.getKey();
            }
        }
        return id;
    }

    private int calcularDijkstra(int id) {
        int idKB = searchIdActor(nombreKBacon);
        return this.grafo.caminoMinimoDijkstra(id, idKB);
    }

    private int calcularBFS(int id) {
        int idKB = searchIdActor(nombreKBacon);
        return this.grafo.caminoMinimo(id, idKB);
    }

    private void mostrarCamino(Boolean metodo, Integer origen) {
        int idKB = searchIdActor(nombreKBacon);
        List<Edge<Integer>> y = grafo.recorridoCaminoMinimo(origen, idKB, metodo);
        for (Edge<Integer> t : y) {
            String nombreOrigen = Actor.buscarNombreActor(actores, t.getOrigen().getData());
            String pelicula = Pelicula.buscarNombrePelicula(peliculas, Integer.parseInt(t.getData().toString()));
            String nombreDestino = Actor.buscarNombreActor(actores, t.getDestino().getData());
            String g = nombreOrigen + " participó en " + pelicula + " con " + nombreDestino + "\n ";
            v.appendText(g);
        }
    }
}
