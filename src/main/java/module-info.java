module fr.chess.guchess {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.chess.guchess to javafx.fxml;
    exports fr.chess.guchess;
    exports view;
    opens view to javafx.fxml;
}