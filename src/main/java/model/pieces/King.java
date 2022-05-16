package model.pieces;

import model.board.Board;

import java.util.ArrayList;

public class King extends Pieces {

    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);

        this.letter = "K";
        this.value = 99;
    }

    @Override
    protected void move(Position position, Board board) {

    }

    @Override
    protected boolean attackAllies(Position position, Board board) {
        return false;
    }

    @Override
    protected boolean canMove(Position destination, Board board) {
        if (!this.inBounds(destination)) {
            return false;
        }

        if (this.attackAllies(destination, board)) {
            return false;
        }

        return (Math.abs(getX() - destination.getX()) <= 1 && Math.abs(getY() - destination.getY()) <= 1);
    }

    @Override
    protected boolean moveThroughPieces(Position position, Board board) {
        return false;
    }

    @Override
    protected boolean generateMoves(Board board) {
        ArrayList<Position> moves = new ArrayList<>();
    }
}
