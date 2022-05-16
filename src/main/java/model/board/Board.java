package model.board;

import model.pieces.Pieces;
import model.pieces.Position;

public class Board {

    private BoardCase[][] boardCases;

    public Board() {
        boardCases = new BoardCase[8][8];
    }

    public BoardCase[][] getBoardCases() {
        return boardCases;
    }

    public Pieces getPieceAt(Position position) {
        return this.getPieceAt(position.getX(), position.getY());
    }

    public Pieces getPieceAt(int x, int y) {
        return boardCases[x][y].getPieces();
    }
}
