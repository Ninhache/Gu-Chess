package model.pieces;

import model.ImagesUtil;
import model.board.Board;

import java.util.ArrayList;

public class King extends Pieces {

    public King(Board board, int x, int y, boolean isWhite) {
        super(board, x, y, isWhite);

        this.letter = "K";
        this.value = 99;

        String color = isWhite ? "W" : "B";
        this.image = ImagesUtil.createImage(this, "images/" + color + "_KING.png");
    }

    @Override
    protected boolean canMove(Position destination) {
        if (!this.inBounds(destination)) {
            return false;
        }

        if (this.attackAllies(destination)) {
            return false;
        }

        return (Math.abs(getX() - destination.getX()) <= 1 && Math.abs(getY() - destination.getY()) <= 1);
    }

    @Override
    protected ArrayList<Position> generateMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        for (int i = -1 ; i < 2; i++) {
            for (int j = -1 ; j < 2; j++) {
                int x = getX() + i;
                int y = getY() + j;
                if (this.inBounds(x, y)) {
                    if (i != 0 || j != 0) {
                        if (!this.attackAllies(x, y)) {
                            moves.add(new Position(x,y));
                        }
                    }
                }
            }
        }

        return moves;
    }
}
