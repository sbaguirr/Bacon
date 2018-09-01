/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

import ec.edu.espol.vistas.PaneMenuPrincipal;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ROSA
 */
public class MainBacon extends Application {
    public static Scene scene;
    @Override
    public void start(Stage primaryStage) {
        PaneMenuPrincipal mp = new PaneMenuPrincipal();
        scene = new Scene(new Group(),600,550);
        scene.setRoot(mp.getRoot());
        //scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Oraculo de Bacon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
}
