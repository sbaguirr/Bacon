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
import static ec.edu.espol.main.MainBacon.SCENE;
import ec.edu.espol.model.GraphLA;
import ec.edu.espol.utils.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author ROSA
 */
public class PaneOraculo {
    private final BorderPane root;
    private Label titulo, numeroBacon, n;
    private Button calcular, regresar;
    private TextField actor;
    private Boolean dijkstra;
    private GraphLA grafo;
    private final HashMap<Integer, String> peliculas;
    private final HashMap<Integer, String> actores;
    private final HashMap<Integer, List<Integer>> peliculaActor;
    
    public Pane getRoot() {
        return root;
    }
    
    public PaneOraculo(Boolean di) {
        root = new BorderPane();
        this.dijkstra=di;
        peliculas = Utils.cargarPeliculasMap();
        actores = Utils.cargarActoresMap();
        peliculaActor = Utils.cargarPeliActoresMapNew();
        grafo = Utils.generarGrafo(actores, peliculas, peliculaActor);
        inicializarObjetos();
        llamarMetodos();
    }  
     
    private void llamarMetodos() {
        crearSeccionTitulo();
        crearSeccionOpciones();
        seccionNumeroBacon();
        calcularBacon();
    }
     
    private void crearSeccionTitulo() {
        Label titleLbl = new Label("Calcular el número de Bacon de alguien");
        titleLbl.setPrefSize(600, 100);
        titleLbl.setStyle("-fx-font: 25 Verdana; -fx-background-color: #21034B; -fx-text-fill: #F5F5F5;");
        titleLbl.setAlignment(Pos.CENTER);
        titleLbl.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(titleLbl);
    }

    private void inicializarObjetos() {
        titulo = new Label("Distancia de Kevin Bacon hasta: ");
        titulo.setStyle("-fx-font: 18 Verdana;");
        n = new Label("El n° Bacon es: ");
        numeroBacon = new Label();
        n.setStyle("-fx-font: 18 Verdana; -fx-background-color: #D500FF; -fx-text-fill: #F5F5F5;");
        n.setPrefSize(200, 50);
        n.setAlignment(Pos.CENTER);
        numeroBacon.setStyle("-fx-font: 18 Verdana; -fx-background-color: #006CFF; -fx-text-fill: #F5F5F5;");
        numeroBacon.setPrefSize(75, 50);
        numeroBacon.setAlignment(Pos.CENTER);
        actor = new TextField();
        actor.setPrefSize(300, 35);
        calcular = new Button("Calcular");
        calcular.setStyle("-fx-font: 18 Verdana; -fx-font-weight: bold; -fx-base: #A6FF00; -fx-text-fill: #F5F5F5;");
        calcular.setPrefSize(150, 75);
        regresar = new Button("Volver");
        regresar.setStyle("-fx-font: 18 Verdana; -fx-font-weight: bold; -fx-base: #FFDC00; -fx-text-fill: #F5F5F5;");
        regresar.setPrefSize(150, 75);
        regresar.setOnAction(e->{
            PaneMenuPrincipal mp = new PaneMenuPrincipal();
            SCENE.setRoot(mp.getRoot());
        });
    }

    private void crearSeccionOpciones() {
        VBox vbox = new VBox();
        HBox h = new HBox();
        HBox hbox = new HBox();
        h.getChildren().addAll(calcular, regresar);
        h.setSpacing(25);
        h.setPadding(new Insets(0, 10, 0, 10));
        h.setAlignment(Pos.CENTER);
        hbox.getChildren().add(actor);
        hbox.setPadding(new Insets(0, 10, 30, 10));
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(titulo, hbox, h);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        root.setCenter(vbox);
    }
    
    private void seccionNumeroBacon(){
        HBox hn = new HBox();
        hn.setSpacing(15);
        hn.getChildren().addAll(this.n, this.numeroBacon);
        hn.setAlignment(Pos.CENTER);
        hn.setPadding(new Insets(0, 10, 30, 10));
        root.setBottom(hn);
    }
    
    private void ventanaProblemasTecnicos() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Lo sentimos, estamos teniendo inconvenientes técnicos");
        alert.showAndWait();
    }

    private void dialogoAdvertencia() {
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle("Error");
        advertencia.setContentText("Debe asegurarse de llenar todos los campos");
        advertencia.setHeaderText(null);
        advertencia.initStyle(StageStyle.UTILITY);
        advertencia.showAndWait();
    }

    private void actorYaExiste() {
        Alert advertencia = new Alert(Alert.AlertType.ERROR);
        advertencia.setTitle(" :(  ");
        advertencia.setContentText("El actor buscado no existe");
        advertencia.setHeaderText(null);
        advertencia.initStyle(StageStyle.UTILITY);
        advertencia.showAndWait();
    }
    
    public void calcularBacon() {
        calcular.setOnAction(e->{
            String act = this.actor.getText();
            int id = searchIdActor(act);
            if(!act.equals("") || id != 0){
                if(this.dijkstra){
                    String d = String.valueOf(calcularDijkstra(id));
                    this.numeroBacon.setText(d);
                }else{
                    String b = String.valueOf(calcularBFS(id));
                    this.numeroBacon.setText(b);
                }
            }else{
                this.actorYaExiste();
            }        
       });
    }
    
    private int searchIdActor(String actor){
        int id = 0;
        Map<Integer, String> mapa = Utils.cargarActoresMap();
        for(Map.Entry<Integer, String> m : mapa.entrySet()){
            if(m.getValue().equals(actor)){
                return m.getKey();
            }   
        }
        return id;
    }
    
    private int calcularDijkstra(int id){
        int idKB = searchIdActor("Kevin Bacon");
        return this.grafo.caminoMinimoDijkstra(id, idKB);
    }
    
    private int calcularBFS(int id){
        int idKB = searchIdActor("Kevin Bacon");
        return this.grafo.caminoMinimo(id, idKB);   
    }
}
