package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GuChess extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        var mainStage = new MainStage();

        mainStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}