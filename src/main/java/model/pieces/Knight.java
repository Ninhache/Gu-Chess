package model.pieces;

import model.ImagesUtil;
import model.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Pieces{

    public Knight(Board board, int x, int y, boolean isWhite) {
        super(board, x, y, isWhite);
        this.letter = "Kn";
        this.value = 3;

        String color = isWhite ? "W" : "B";
        this.image = ImagesUtil.createImage(this, "images/" + color + "_KNIGHT.png");
    }

    @Override
    protected boolean canMove(Position position) {
        if (!this.inBounds(position)) {
            return false;
        }

        if (this.attackAllies(position)) {
            return false;
        }

        return ((Math.abs(position.getX() - getX()) == 2 && Math.abs(position.getY() - getY()) == 1) ||
                (Math.abs(position.getX() - getX()) == 1 && Math.abs(position.getY() - getY()) == 2));
    }

    @Override
    protected List<Position> generateMoves() {
        ArrayList<Position> moves = new ArrayList<>();

        for (int i = -2; i < 3; i += 4) {
            for (int j = -1; j < 2; j += 2) {

                int x = i + getX();
                int y = j + getY();
                if (!this.attackAllies(position)) {
                    if (this.inBounds(x, y)) {
                        moves.add(new Position(x, y));

                    }
                }
            }
        }
        for (int i = -1; i < 2; i += 2) {
            for (int j = -2; j < 3; j += 4) {

                int x = i + getX();
                int y = j + getY();

                if (this.inBounds(x, y)) {
                    if (!this.attackAllies(x, y)) {
                        moves.add(new Position(x, y));
                    }
                }
            }
        }

        return moves;
    }
}
