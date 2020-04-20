package fr.feraud.secretofnina.mapeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MapEditor extends Application {

    @Override
    //where the application will be displayed
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(this.getClass().getResource("editor.fxml"));

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
