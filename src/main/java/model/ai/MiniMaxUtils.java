package model.ai;

import model.board.Board;

import java.util.List;

public class MiniMaxUtils {

    public static int maxDepth = 3;

    public static int minFun(Board board, int depth) {
        if (depth >= maxDepth) {
            board.setScore();
            return board.getScore();
        }

        List<Board> boards = board.generateNewWhiteBoardTurn();

        int lowestIdx = 0;
        int lowestScore = 100000;

        for (int i = 0 ; i < boards.size() ; i ++) {
            if (!boards.get(i).isDead()) {
                int score = maxFun(boards.get(i), depth + 1);
                if (score < lowestScore) {
                    lowestIdx = i;
                    lowestScore = score;
                }
            }
        }

        return lowestScore;
    }

    public static int maxFun(Board board, int depth) {
        if (depth >= maxDepth) {
            board.setScore();
            return board.getScore();
        }

        List<Board> boards = board.generateNewBlackBoardTurn();

        int topIdx = 0;
        int topScore = -100000;
        for (int i = 0 ; i < boards.size() ; i++) {
            int score = minFun(boards.get(i), depth +1);
            if (score > topScore) {
                topIdx = i;
                topScore = score;
            }
        }

        if (depth == 0) {
            return boards.get(topIdx).getScore();
        }

        return topScore;
    }

    public int minFunAb(Board board, int alpha, int beta, int depth) {
        if (depth >= maxDepth) {
            board.setScore();
            return board.getScore();
        }

        if (board.isDead()) {
            if (board.getWhiteAi() && board.getWhitesMove()) {
                return 200;
            }
            if (board.getBlackAi() && !board.getWhitesMove()) {
                return -200;
            }
        }

        if (board.hasWon()) {
            if (board.getWhiteAi() && board.getWhitesMove()) {
                return -200;
            }
            if (board.getBlackAi() && !board.getWhitesMove()) {
                return 200;
            }
        }

        List<Board> boards = board.generateNewWhiteBoardTurn();
        int lowestIdx = 0;
        int lowestScore = 300;
        for (int i = 0 ; i < boards.size() ; i++) {
            int score = maxFunAB(boards.get(i), alpha, beta, depth+1);
            if (score < lowestScore) {
                lowestIdx = i;
                lowestScore = score;
            } else {
                if (depth == 0 && score == lowestScore) {
                    if (Math.random() < 0.3) {
                        lowestIdx = i;
                    }
                }
            }

            if (score < alpha) {
                return lowestScore;
            }

            if (score < beta) {
                beta = score;
            }
        }

        if (depth == 0) {
            return boards.get(lowestIdx).getScore();
        }

        return lowestScore;
    }

    public int maxFunAB(Board board, int alpha, int beta, int depth) {
        if (depth >= maxDepth) {
            board.setScore();
            return board.getScore();
        }

        if (board.isDead()) {
            if (board.getWhiteAi() && board.getWhitesMove()) {
                return 200;
            }
            if (board.getBlackAi() && board.getWhitesMove()) {
                return -200;
            }
        }

        if (board.hasWon()) {
            if (board.getWhiteAi() && board.getWhitesMove()) {
                return -200;
            }
            if (board.getBlackAi() && board.getWhitesMove()) {
                return 200;
            }
        }

        List<Board> boards = board.generateNewBlackBoardTurn();
        int topIdx = 0;
        int topScore = -300;

        for (int i = 0; i < boards.size() ; i++) {
            int score = minFunAb(boards.get(i), alpha, beta, depth +1);
            if (score > topScore) {
                topIdx = i;
                topScore = score;
            } else {
                if (depth == 0 && score == topScore) {
                    if (Math.random() < 0.3) {
                        topIdx = i;
                    }
                }
            }

            if (score > beta) {
                return topScore;
            }
            if (score > alpha) {
                alpha = score;
            }
        }

        if (depth == 0) {
            return boards.get(topIdx).getScore();
        }

        return topScore;
    }

}