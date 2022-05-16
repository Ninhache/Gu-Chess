package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.board.Board;
import model.pieces.Pieces;

public class ChessCanvas extends Canvas {

    private Board board;

    public ChessCanvas(int width, int height) {
        super(width, height);
        board = new Board();
    }

    public void draw() {
        GraphicsContext g = getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0,0, getWidth(), getHeight());

        int width = (int) getWidth() / 8;
        int height = (int) getHeight() / 8;

        for (int i = 0; i < 8; i++) {
            for (int j = 0 ; j < 8 ; j++) {
                if ((i + j) % 2 == 0) {
                    g.setFill(Color.WHITE);
                } else {
                    g.setFill(Color.BLACK);
                }
                g.fillRect(width * i, height * j, width, height);
            }
        }

        board.getBlackPieces().forEach(Pieces::show);
    }

}
