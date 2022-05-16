package model.pieces;

import model.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Pieces {

    public Queen(Board board, int x, int y, boolean isWhite) {
        super(board, x, y, isWhite);

        this.letter = "Q";
        this.value = 9;
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

        if (Math.abs(position.getX() - getY()) == Math.abs(position.getY() - getY())) {
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

        // diagonals
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
