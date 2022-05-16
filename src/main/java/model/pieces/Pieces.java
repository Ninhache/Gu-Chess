package model.pieces;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.board.Board;
import view.ChessCanvas;

import java.util.ArrayList;
import java.util.List;

/**
 * The Pieces class represent any entities in my chess game !
 */
public abstract class Pieces {

    protected Position position;
    protected boolean isWhite;
    protected String letter;
    protected boolean taken = false;
    protected Board board;
    protected Image image;

    // Can be usefull if I want to implement an AI
    protected int value;

    /**
     * A pieces can be created out of the board, but won't be able to be set on it ..
     *
     * @param x position
     * @param y position
     * @param isWhite
     */
    public Pieces(Board board, int x, int y, boolean isWhite) {
        this.board = board;
        this.position = new Position(x,y);
        this.isWhite = isWhite;
    }

    /**
     * Check if the X,Y values are in the board, the board is ALWAYS equals to a 8x8 !
     * @param x
     * @param y
     * @return true if it's in bound
     */
    protected boolean inBounds(int x, int y) {
        return (x >= 0 && y >= 0 && x < 8 && y < 8);
    }

    /**
     * @see Pieces#inBounds(int, int)
     */
    protected boolean inBounds(Position position) {
        return inBounds(position.getX(), position.getY());
    }

    /**
     *  Move the piece to the position
     *
     * CARE ! YOU'VE TO CHECK POSITION BEFORE !
     *
     * @param position position
     */
    protected void move(Position position) {
        Pieces pieces = board.getPieceAt(position.getX(), position.getY());

        if (pieces != null) {
            pieces.taken = true;
        }

        this.position = position.clone();
    }

    /**
     *
     * Check if at position, there's another piece with the same color
     *
     * @param x
     * @param y
     * @return true if the piece will "fall" on a piece that have the same color
     */
    protected boolean attackAllies(int x, int y) {
        Pieces pieces = board.getPieceAt(x, y);

        if (pieces != null) {
            return pieces.isWhite == this.isWhite;
        }
        return false;
    }

    protected boolean attackAllies(Position position) {
        return attackAllies(position.getX(), position.getY());
    }

    /**
     * Check if a piece can move to a destination
     * @param position
     * @return true, if the piece can move
     */
    abstract protected boolean canMove(Position position);

    /**
     * TODO
     * @param position
     * @return
     */
    protected boolean moveThroughPieces(Position position) {
        return moveThroughPieces(position.getX(), position.getY());
    }

    public void show(GraphicsContext gc) {
        gc.drawImage(this.image, gc.getCanvas().getWidth()/2, gc.getCanvas().getHeight()/2, 150, 150);
    }

    protected boolean moveThroughPieces(int x, int y) {
        int tempX = x - getX();
        if (tempX > 0) {
            tempX = 1;
        } else if (tempX < 0) {
            tempX = -1;
        }

        int tempY = y - getY();
        if (tempY > 0) {
            tempY = 1;
        } else if (tempY < 0) {
            tempY = -1;
        }

        Position tempPos = new Position(x,y);
        tempPos.addX(tempX);
        tempPos.addY(tempY);

        while (tempPos.getX() != getX() || tempPos.getY() != getY()) {
            if (board.getPieceAt(tempPos.getX(), tempPos.getY()) != null) {
                return true;
            }

            tempPos.addX(tempX);
            tempPos.addY(tempY);
        }

        return false;
    }

    /**
     * Get all possible moves from the pieces
     * @return a list containing all moves
     */
    abstract protected List<Position> generateMoves();

    public Position getPosition() {
        return position;
    }

    public int getX() {
        return getPosition().getX();
    }

    public int getY() {
        return getPosition().getY();
    }

    public boolean isTaken() {
        return taken;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void move(int x, int y) {
        Pieces piece = board.getPieceAt(x,y);
        if (piece != null) {
            piece.taken = true;
        }

        this.position = new Position(x,y);
    }

    public List<Board> generateNewBoard() {
        List<Board> boards = new ArrayList<>();
        List<Position> positions = this.generateMoves();

        for (int i = 0 ; i < positions.size() ; i++) {
            boards.add(i, this.board.clone());
            boards.get(i).move(position, positions.get(i));
        }

        return boards;
    }

    public int getValue() {
        return value;
    }

    public Image getImage() {
        return image;
    }
}
