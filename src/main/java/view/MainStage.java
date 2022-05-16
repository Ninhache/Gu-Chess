package view;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainStage extends ExtendedStage {

    private ChessMenu menu;
    private ChessCanvas canvas;

    private Scene sceneMenu, sceneCanvas;

    public MainStage() {
        super();

        menu = ChessMenu.getInstance();
        canvas = new ChessCanvas(640, 640);

        Button buttonAlone = menu.getButtonPlayAlone();
        Button buttonFriends = menu.getButtonPlayFriend();

        sceneMenu = new Scene(menu, 640,640);
        sceneCanvas = new Scene(new VBox(canvas), 640,640);

        buttonAlone.setOnAction(this::switchToAlone);
        setScene(sceneMenu);
    }

    private void switchToAlone(ActionEvent actionEvent) {
        setScene(sceneCanvas);

        canvas.draw();
    }

}
