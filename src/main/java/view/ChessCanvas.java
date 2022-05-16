package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.ImagesUtil;
import model.board.Board;
import model.pieces.Pieces;

public class ChessCanvas extends Canvas {

    private Board board;

    public ChessCanvas(int width, int height) {
        super(width, height);
        board = new Board();
    }

    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, getWidth(), getHeight());

        int width = (int) getWidth() / 8;
        int height = (int) getHeight() / 8;

        for (int i = 0; i < 8; i++) {
            for (int j = 0 ; j < 8 ; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.WHITE);
                } else {
                    gc.setFill(Color.BLACK);
                }
                gc.fillRect(width * i, height * j, width, height);
            }
        }

        board.getBlackPieces().forEach(pieces -> pieces.show(gc));
        board.getWhitePieces().forEach(pieces -> pieces.show(gc));

    }

}
