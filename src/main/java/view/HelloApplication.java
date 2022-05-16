package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        HBox root = new HBox();
        TextField tf = new TextField();

        root.getChildren().add(tf);

        stage.setScene(new Scene(root, 500,500));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}