package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.board.Board;

public class ChessCanvas extends Canvas {

    private Board board;

    public ChessCanvas(int width, int height) {
        super();
        board = new Board();
    }

    public void draw() {
        GraphicsContext g = getGraphicsContext2D();
        g.setFill(Color.BLUE);
        g.fillRect(0,0, getWidth(), getHeight());
    }

}
