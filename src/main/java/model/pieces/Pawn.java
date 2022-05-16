package model.pieces;

import model.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Pieces {

    private boolean firstTurn;

    public Pawn(Board board, int x, int y, boolean isWhite) {
        super(board, x, y, isWhite);
        firstTurn = true;
        this.letter = "P";
        this.value = 1;
    }

    @Override
    protected boolean canMove(Position position) {
        if (!this.inBounds(position)) {
            return false;
        }

        if (this.attackAllies(position)) {
            return false;
        }

        Pieces pieces = board.getPieceAt(position);
        if (pieces != null) {
            if (Math.abs(position.getX() - getX()) == Math.abs(position.getY() - getY()) &&
                    (this.isWhite && (position.getY() - getY() == -1)) ||
                    (!this.isWhite && (position.getY() - getY() == 1))) {
                this.firstTurn = false;
                return true;
            }
        }

        if (position.getX() != getX()) {
            return false;
        }

        if (this.isWhite && position.getY() - getY() == -1 ||
            !this.isWhite && position.getY() - getY() == 1) {
            this.firstTurn = false;
            return true;
        }

        if (this.firstTurn && ((this.isWhite && position.getY() - getY() == -2) ) ||
            (!this.isWhite && position.getY() - getY() == 2)) {
            if (this.moveThroughPieces(position)) {
                return false;
            }

            this.firstTurn = false;
            return true;
        }

        return false;
    }

    @Override
    protected List<Position> generateMoves() {
        ArrayList<Position> moves = new ArrayList<>();

        for (int i = -1 ; i < 2; i+=2) {
            int x = getX() + i;
            int y = getY() + (isWhite ? - 1 : 1);

            Pieces pieces = board.getPieceAt(x,y);
            if (pieces != null) {
                if (!this.attackAllies(x,y)) {
                    moves.add(new Position(x,y));
                }
            }
        }

        int x = getX();
        int y = getY() + (isWhite ? -1 : 1);

        if (!(board.getPieceAt(x,y) == null) && this.inBounds(x,y)) {
            moves.add(new Position(x,y));
        }

        if (this.firstTurn) {
            y = getY() + (isWhite ? -2 : 2);

            if (!(board.getPieceAt(x,y) == null) && this.inBounds(x,y)) {
                if (!this.moveThroughPieces(position)) {
                    moves.add(new Position(x,y));
                }
            }
        }

        return moves;
    }

    @Override
    protected void move(Position position) {
        super.move(position);
        this.firstTurn = false;
    }

}
