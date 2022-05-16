package model.pieces;

import model.board.Board;

public abstract class Pieces {

    protected Position position;
    protected boolean isWhite;
    protected int value;
    protected String letter;

    public Pieces(int x, int y, boolean isWhite) {
        this.position = new Position(x,y);
        this.isWhite = isWhite;
    }

    protected boolean inBounds(Position position) {
        return inBounds(position.getX(), position.getY());
    }

    protected boolean inBounds(int x, int y) {
        return (x >= 0 && y >= 0 && x < 8 && y < 8);
    }

    abstract protected void move(Position position, Board board);
    abstract protected boolean attackAllies(Position position, Board board);
    abstract protected boolean canMove(Position position, Board board);
    abstract protected boolean moveThroughPieces(Position position, Board board);
    abstract protected boolean generateMoves(Board board);

    public Position getPosition() {
        return position;
    }

    public int getX() {
        return getPosition().getX();
    }

    public int getY() {
        return getPosition().getY();
    }
}
