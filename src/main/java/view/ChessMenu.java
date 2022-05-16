package view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ChessMenu extends VBox {

    private static ChessMenu instance;

    private Button buttonPlayAlone, buttonPlayFriend;

    private ChessMenu() {
        buttonPlayAlone = new Button("Play alone");
        buttonPlayFriend = new Button("Play with a friend");

        getChildren().addAll(buttonPlayAlone, buttonPlayFriend);
    }

    public static ChessMenu getInstance() {
        if (instance == null) {
            instance = new ChessMenu();
        }

        return instance;
    }

    public Button getButtonPlayAlone() {
        return buttonPlayAlone;
    }

    public Button getButtonPlayFriend() {
        return buttonPlayFriend;
    }
}
