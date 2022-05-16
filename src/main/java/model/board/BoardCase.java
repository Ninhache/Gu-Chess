package model.board;

import model.pieces.Pieces;

public class BoardCase {

    private final int x, y;
    private Pieces pieces;

    public BoardCase(int x, int y, Pieces pieces) {
        this.x = x;
        this.y = y;
        this.pieces = pieces;
    }

    public BoardCase(int x, int y) {
        this(x,y,null);
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }

    public Pieces getPieces() {
        return pieces;
    }

    public boolean isEmpty() {
        return getPieces() == null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        BoardCase boardCase = (BoardCase) o;
        if (this.x != boardCase.x) return false;
        return this.y == boardCase.y;
    }

    @Override
    protected BoardCase clone() {
        BoardCase boardCase = new BoardCase(x, y, getPieces());

        return boardCase;
    }
}

