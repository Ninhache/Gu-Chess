package model.board;

import model.pieces.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private BoardCase[][] boardCases;
    private int score;

    private List<Pieces> whitePieces, blackPieces;

    private Boolean whitesMove, whiteAi, blackAi;

    public Board() {
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();

        setupPieces("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    }

    public Board(List<Pieces> whitePieces, List<Pieces> blackPieces) {
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
    }

    // https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
    // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
    public void setupPieces(String fen) {
        fen = fen.replaceAll("/", "");

        for(int idx = 0 ; idx < fen.length() ; idx ++) {
            int y = idx % 8;
            int x = idx / 8;

            char charAt_piece = fen.charAt(idx);

            boolean isWhite = Character.isLowerCase(charAt_piece);

            Pieces pieces;

            switch (Character.toLowerCase(charAt_piece)) {
                case 'p':
                    pieces = new Pawn(this, x, y, isWhite);
                    break;
                case 'r':
                    pieces = new Rook(this, x, y, isWhite);
                    break;
                case 'n':
                    pieces = new Knight(this, x, y, isWhite);
                    break;
                case 'b':
                    pieces = new Bishop(this, x, y, isWhite);
                    break;
                case 'q':
                    pieces =new Queen(this, x, y, isWhite);
                    break;
                case 'k':
                    pieces = new King(this, x, y, isWhite);
                    break;
                default:
                    break;
            }

            if (isWhite) {

            } else {

            }

        }
    }

    public BoardCase[][] getBoardCases() {
        return boardCases;
    }

    public Pieces getPieceAt(Position position) {
        return this.getPieceAt(position.getX(), position.getY());
    }

    public boolean isPieceAt(Position position) {
        return isPieceAt(position.getX(), position.getY());
    }

    public boolean isPieceAt(int x, int y) {
        return getPieceAt(x, y) != null;
    }

    public void move(Position from, Position to) {
        move(from.getX(), from.getY(), to.getX(), to.getY());
    }

    public List<Board> generateNewWhiteBoardTurn() {
        List<Board> boards = new ArrayList<>();
        for (Pieces whitePiece : this.whitePieces) {
            if (!whitePiece.isTaken()) {
                List<Board> tempBoard = whitePiece.generateNewBoard();
                boards.addAll(tempBoard);
            }
        }

        return boards;
    }

    public List<Board> generateNewBlackBoardTurn() {
        List<Board> boards = new ArrayList<>();
        for (Pieces blackPiece : this.blackPieces) {
            if (!blackPiece.isTaken()) {
                List<Board> tempBoard = blackPiece.generateNewBoard();
                boards.addAll(tempBoard);
            }
        }

        return boards;
    }


    public void move(int x1, int y1, int x2, int y2) {
        Pieces piece = this.getPieceAt(x1, y1);

        if (piece == null) {
            return;
        }

        piece.move(x2,y2);

    }

    public Pieces getPieceAt(int x, int y) {
        return boardCases[x][y].getPieces();
    }

    public Board clone() {
        ArrayList<Pieces> white = (ArrayList) ((ArrayList) getWhitePieces()).clone();
        ArrayList<Pieces> black = (ArrayList) ((ArrayList) getBlackPieces()).clone();

        return new Board(white, black);
    }

    public List<Pieces> getBlackPieces() {
        return blackPieces;
    }

    public List<Pieces> getWhitePieces() {
        return whitePieces;
    }

    public void setBlackPieces(List<Pieces> blackPieces) {
        this.blackPieces = blackPieces;
    }

    public void setWhitePieces(List<Pieces> whitePieces) {
        this.whitePieces = whitePieces;
    }

    public int getScore() {
        return score;
    }

    public void setScore() {
        this.score = 0;

        whitePieces.stream().filter(p -> !p.isTaken()).forEach(item -> score -= item.getValue());
        blackPieces.stream().filter(p -> !p.isTaken()).forEach(item -> score += item.getValue());
    }

    public Pieces getWhiteKing() {
        return whitePieces.stream().filter(item -> King.class.equals(item.getClass())).findFirst().orElse(null);
    }

    public Pieces getBlackKing() {
        return blackPieces.stream().filter(item -> King.class.equals(item.getClass())).findFirst().orElse(null);
    }

    public boolean isDead() {
        if (whiteAi && whitesMove) {
            return getBlackKing().isTaken();
        }
        if (blackAi && !whitesMove) {
            return getWhiteKing().isTaken();
        }

        return false;
    }

    public boolean hasWon() {
        if (whiteAi && whitesMove) {
            return getBlackKing().isTaken();
        }
        if (blackAi && !whitesMove) {
            return getWhiteKing().isWhite();
        }

        return false;
    }

    public Boolean getBlackAi() {
        return blackAi;
    }

    public Boolean getWhiteAi() {
        return whiteAi;
    }

    public Boolean getWhitesMove() {
        return whitesMove;
    }
}
