package com.quadcore.connectfour.model.heuristics;

import com.quadcore.connectfour.model.Game;
import com.quadcore.connectfour.model.state.State;


public class ConnectFourHeuristic implements Heuristic {
    private static final int LVL5 = 300;
    private static final int LVL4 = 60;
    private static final int LVL3 = 40;
    private static final int LVL2 = 10;
    private static final double DEFENSIVE_FACTOR = 0.2;
    private static final double OVERLAPPING_FACTOR = 0.05;


    private char[][] state2D;

    @Override
    public double evaluate(State state) {

        state2D = state.to2dArray();
        double heuristic = 0;
        State.Score score = state.getScore();
        heuristic += (score.getAIScore() - score.getPlayerScore()) * LVL5;
        for (int i = 0; i < Game.ROWS; i++) {
            for (int j = 0; j < Game.COLUMNS; j++) {
                if (state2D[i][j] == State.EMPTY && isValidMove(i, j)) {
                    heuristic += getEmptySlotEvaluation(i, j, State.AI);
                    heuristic -= getEmptySlotEvaluation(i, j, State.PLAYER);
                }
            }
        }
        return heuristic;
    }

    private double getEmptySlotEvaluation(int row, int col, char player) {
        double horizontal = getHorizontalEmptyEvaluation(row, col, player);
        double vertical = getVerticalEmptyEvaluation(row, col, player);
        double positiveDiagonal = getPositiveDiagonalEvaluation(row, col, player);
        double negativeDiagonal = getNegativeDiagonalEvaluation(row, col, player);
        return getOverlappingFactor(horizontal, vertical, positiveDiagonal, negativeDiagonal) * (
                horizontal + vertical + positiveDiagonal + negativeDiagonal
                );
    }

    private double getHeuristicScore(int run, int emptyLeft, int emptyRight,
                                     boolean canPlayLeft, boolean canPlayRight, char player) {
        /*no way we can get 4*/
        if (run + emptyLeft + emptyRight < 4) return 0;

        /*three filled, both sides empty*/
        if (run == 3 && emptyLeft > 0 && emptyRight > 0) {
            if (canPlayLeft && canPlayRight && player == State.PLAYER) return LVL5 * (1 + DEFENSIVE_FACTOR);
            if (canPlayLeft && canPlayRight) return LVL5;
            if ((canPlayLeft || canPlayRight) && player == State.PLAYER) return LVL4 * (1 + DEFENSIVE_FACTOR);
            if (canPlayLeft || canPlayRight) return LVL4;
            if (player == State.PLAYER) return LVL4 * (1 + DEFENSIVE_FACTOR);
            return LVL4;
        }

        /*three filled, empty from one side*/
        if (run == 3) {
            if (canPlayLeft || canPlayRight) return LVL2;
            return LVL3;
        }

        /*two filled, empty both sides*/
        if (run == 2 && emptyLeft > 0 && emptyRight > 0) {
            if (canPlayLeft && canPlayRight) return LVL3;
            if (canPlayLeft || canPlayRight) return LVL2;
            return (3 * LVL2 + LVL3) / 4.0;
        }
        /*two filled, empty one sides*/
        if (run == 2) {
            if (canPlayLeft || canPlayRight) return LVL2;
            return (3 * LVL2 + LVL3) / 4.0;
        }

        if (run >= 4) {
            if (player == State.PLAYER) return LVL5 * (1 + DEFENSIVE_FACTOR);
            return LVL5;
        }

        return 0;
    }

    private double getVerticalEmptyEvaluation(int row, int col, char player) {
        int count = 1;

        int rowIndex = row + 1;
        while (rowIndex < Game.ROWS && state2D[rowIndex][col] == player) {
            count++;
            rowIndex++;
        }

        return getHeuristicScore(count, row, 0, true, false, player);
    }

    private double getHorizontalEmptyEvaluation(int row, int col, char player) {
        int count = 1;
        int emptyLeft = 0;
        int emptyRight = 0;
        boolean canPlayLeft = false;
        boolean canPlayRight = false;

        int colIndex = col - 1;
        while (colIndex >= 0 && state2D[row][colIndex] == player) {
            count++;
            colIndex--;
        }
        if (colIndex >= 0 && state2D[row][colIndex] == State.EMPTY
                && isValidMove(row, colIndex)) canPlayLeft = true;
        while (colIndex >= 0 && state2D[row][colIndex] == State.EMPTY) {
            emptyLeft++;
            colIndex--;
        }

        colIndex = col + 1;
        while (colIndex < Game.COLUMNS && state2D[row][colIndex] == player) {
            count++;
            colIndex++;
        }
        if (colIndex < Game.COLUMNS && state2D[row][colIndex] == State.EMPTY
                && isValidMove(row, colIndex)) canPlayRight = true;
        while (colIndex < Game.COLUMNS && state2D[row][colIndex] == State.EMPTY) {
            emptyRight++;
            colIndex++;
        }

        return getHeuristicScore(count, emptyLeft, emptyRight, canPlayLeft, canPlayRight, player);
    }

    private double getPositiveDiagonalEvaluation(int row, int col, char player) {
        int count = 1;
        int emptyLeft = 0;
        int emptyRight = 0;
        boolean canPlayLeft = false;
        boolean canPlayRight = false;

        int colIndex = col + 1;
        int rowIndex = row - 1;
        while (colIndex < Game.COLUMNS && rowIndex >= 0 && state2D[rowIndex][colIndex] == player) {
            count++;
            colIndex++;
            rowIndex--;
        }
        if (colIndex < Game.COLUMNS && rowIndex >= 0 && state2D[rowIndex][colIndex] == State.EMPTY
                && isValidMove(rowIndex, colIndex)) canPlayRight = true;
        while (colIndex < Game.COLUMNS && rowIndex >= 0 && state2D[rowIndex][colIndex] == State.EMPTY) {
            emptyRight++;
            colIndex++;
            rowIndex--;
        }

        colIndex = col - 1;
        rowIndex = row + 1;
        while (colIndex >= 0 && rowIndex < Game.ROWS && state2D[rowIndex][colIndex] == player) {
            count++;
            colIndex--;
            rowIndex++;
        }
        if (colIndex >= 0 && rowIndex < Game.ROWS && state2D[rowIndex][colIndex] == State.EMPTY
                && isValidMove(rowIndex, colIndex)) canPlayLeft = true;
        while (colIndex >= 0 && rowIndex < Game.ROWS && state2D[rowIndex][colIndex] == State.EMPTY) {
            emptyLeft++;
            colIndex--;
            rowIndex++;
        }

        return getHeuristicScore(count, emptyLeft, emptyRight, canPlayLeft, canPlayRight, player);
    }

    private double getNegativeDiagonalEvaluation(int row, int col, char player) {
        int count = 1;
        int emptyLeft = 0;
        int emptyRight = 0;
        boolean canPlayLeft = false;
        boolean canPlayRight = false;

        int colIndex = col - 1;
        int rowIndex = row - 1;
        while (colIndex >= 0 && rowIndex >= 0 && state2D[rowIndex][colIndex] == player) {
            count++;
            colIndex--;
            rowIndex--;
        }
        if (colIndex >= 0 && rowIndex >= 0 && state2D[rowIndex][colIndex] == State.EMPTY
                && isValidMove(rowIndex, colIndex)) canPlayLeft = true;
        while (colIndex >= 0 && rowIndex >= 0 && state2D[rowIndex][colIndex] == State.EMPTY) {
            emptyLeft++;
            colIndex--;
            rowIndex--;
        }

        colIndex = col + 1;
        rowIndex = row + 1;
        while (colIndex < Game.COLUMNS && rowIndex < Game.ROWS && state2D[rowIndex][colIndex] == player) {
            count++;
            colIndex++;
            rowIndex++;
        }
        if (colIndex < Game.COLUMNS && rowIndex < Game.ROWS && state2D[rowIndex][colIndex] == State.EMPTY
                && isValidMove(rowIndex, colIndex)) canPlayRight = true;
        while (colIndex < Game.COLUMNS && rowIndex < Game.ROWS && state2D[rowIndex][colIndex] == State.EMPTY) {
            emptyRight++;
            colIndex++;
            rowIndex++;
        }

        return getHeuristicScore(count, emptyLeft, emptyRight, canPlayLeft, canPlayRight, player);
    }

    private boolean isValidMove(int row, int col) {
        if (row == Game.ROWS - 1) return true;
        return state2D[row + 1][col] != State.EMPTY;
    }

    private double getOverlappingFactor(double a, double b, double c, double d) {
        int count = 0;
        if (a > 0) count++;
        if (b > 0) count++;
        if (c > 0) count++;
        if (d > 0) count++;
        return 1 + count * OVERLAPPING_FACTOR;
    }
}
