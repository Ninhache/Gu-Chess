package model.pieces;

import model.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Pieces{

    public Bishop(Board board, int x, int y, boolean isWhite) {
        super(board, x, y, isWhite);
        this.letter = "B";
        this.value = 3;
    }

    @Override
    protected boolean canMove(Position position) {
        if (!this.inBounds(position)) {
            return false;
        }

        if (!this.attackAllies(position)) {
            return false;
        }

        if (Math.abs(position.getX() - getX()) == Math.abs(position.getY() - getY())) {
            return moveThroughPieces(position);
        }

        return false;
    }

    @Override
    protected List<Position> generateMoves() {
        ArrayList<Position> moves = new ArrayList<>();

        for (int i = 0 ; i < 8 ; i++) {
            var x = i;
            var y = getY() - (getX() - i);

            if (x != getX()) {
                if (inBounds(x,y)) {
                    if (!attackAllies(x,y)) {
                        if (!this.moveThroughPieces(x,y)) {
                            moves.add(new Position(x,y));
                        }
                    }
                }
            }
        }

        for (int i = 0 ; i < 8 ; i++) {
            var x = getX() - (getY() - i);
            var y = i;

            if (x != getX()) {
                if (inBounds(x,y)) {
                    if (!attackAllies(x,y)) {
                        if (!this.moveThroughPieces(x,y)) {
                            moves.add(new Position(x,y));
                        }
                    }
                }
            }
        }

        return moves;
    }
}
