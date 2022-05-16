package model.pieces;

import model.ImagesUtil;
import model.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Pieces {

    public Rook(Board board, int x, int y, boolean isWhite) {
        super(board, x, y, isWhite);
        this.letter = "R";
        this.value = 5;

        String color = isWhite ? "W" : "B";
        this.image = ImagesUtil.createImage(this, "images/" + color + "_ROOK.png");
    }

    @Override
    protected boolean canMove(Position position) {
        if (!this.inBounds(position)) {
            return false;
        }
        if (this.attackAllies(position)) {
            return false;
        }

        if (position.getX() == getX() || position.getY() == getY()) {
            return this.moveThroughPieces(position);
        }

        return false;
    }

    @Override
    protected List<Position> generateMoves() {
        ArrayList<Position> moves = new ArrayList<>();

        // horizontal
        for (int i = 0; i < 8; i++) {
            int x = i;
            int y = getY();

            if (x != getX()) {
                if (!this.attackAllies(x, y)) {
                    if (!moveThroughPieces(x,y)) {
                        moves.add(new Position(x,y));
                    }
                }
            }
        }

        // vertical
        for (int i = 0; i < 8; i++) {
            int x = getX();
            int y = i;

            if (y != getY()) {
                if (!this.attackAllies(x, y)) {
                    if (!moveThroughPieces(x,y)) {
                        moves.add(new Position(x,y));
                    }
                }
            }
        }

        return moves;
    }
}
