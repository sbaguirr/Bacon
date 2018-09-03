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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import static ec.edu.espol.main.MainBacon.SCENE;

/**
 *
 * @author ROSA
 */
public class PaneMenuPrincipal {
    private final BorderPane root;
    private Label titulo;
    private Button btnDijkstra;
    private Button btnBfs;
    private PaneOraculo o;

    public Pane getRoot() {
        return root;
    }
    
    public PaneMenuPrincipal() {
        root = new BorderPane();
        inicializarObjetos();
        llamarMetodos();
    }  
     
    private void llamarMetodos() {
        crearSeccionTitulo();
        crearSeccionOpciones();
    }
     
    private void crearSeccionTitulo() {
        Label titleLbl = new Label("Bienvenido al juego del 'Número de Bacon'");
        titleLbl.setPrefSize(610, 100);
        titleLbl.setStyle("-fx-font: 25 Verdana; -fx-background-color: #21034B; -fx-text-fill: #F5F5F5;");
        titleLbl.setAlignment(Pos.CENTER);
        titleLbl.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(titleLbl);
    }

    private void inicializarObjetos() {
        titulo = new Label("¿Qué método de búsqueda desea utilizar?");
        titulo.setStyle("-fx-font: 20 Verdana;");
        
        btnDijkstra = new Button("Dijsktra");
        btnDijkstra.setStyle("-fx-font: 18 Verdana; -fx-font-weight: bold; -fx-base: #EC00FF; -fx-text-fill: #F5F5F5;");
        btnDijkstra.setPrefSize(150, 75);
        btnDijkstra.setOnAction(e->{
             o = new PaneOraculo(true);
            SCENE.setRoot(o.getRoot());
        });
        
        btnBfs = new Button("BFS");
        btnBfs.setStyle("-fx-font: 18 Verdana; -fx-font-weight: bold; -fx-base: #00B6FF; -fx-text-fill: #F5F5F5;");
        btnBfs.setPrefSize(150, 75);
        btnBfs.setOnAction(e->{
             o = new PaneOraculo(false);
            SCENE.setRoot(o.getRoot());
        });
    }

    private void crearSeccionOpciones() {
        VBox vbox = new VBox();
        HBox h = new HBox();
        h.getChildren().addAll(btnDijkstra, btnBfs);
        h.setSpacing(25);
        h.setPadding(new Insets(0, 10, 0, 10));
        h.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(titulo, h);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        root.setCenter(vbox);
    }
}
