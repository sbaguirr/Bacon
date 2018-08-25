package ec.edu.espol.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../vistas/vistaPrincipal.fxml"));
        primaryStage.setScene(new Scene(root,640,680));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
