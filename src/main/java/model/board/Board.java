package model.board;

import model.pieces.Pieces;

public class Board {

    private BoardCase[][] boardCases;

    public Board() {
        boardCases = new BoardCase[8][8];
    }

    public BoardCase[][] getBoardCases() {
        return boardCases;
    }

    public Pieces getPieceAt(int x, int y) {
        return boardCases[x][y].getPieces();
    }
}
