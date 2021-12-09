package com.quadcore.connectfour.model.heuristics;

import com.quadcore.connectfour.model.Game;
import com.quadcore.connectfour.model.state.State;


public class ConnectFourHeuristic implements Heuristic {
    private static final int LVL4 = 200;
    private static final int LVL3 = 40;
    private static final int LVL2 = 10;


    private char[][] state2D;

    @Override
    public double evaluate(State state) {

        state2D = state.to2dArray();
        double heuristic = 0;
        State.Score score = state.getScore();
        heuristic += (score.getAIScore() - score.getPlayerScore()) * LVL4;
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
        //todo
        return getHorizontalEmptyEvaluation(row, col, player)
                + getVerticalEmptyEvaluation(row, col, player)
                + getNegativeDiagonalEvaluation(row, col, player)
                + getPositiveDiagonalEvaluation(row, col, player);
    }

    private double getHeuristicScore(int run, int emptyLeft, int emptyRight, char player) {
        /*no way we can get 4*/
        if (run + emptyLeft + emptyRight < 4) return 0;
        /*empty left, three filled, empty right*/
        if (run == 3 && emptyLeft > 0 && emptyRight > 0) {
            if (player == State.PLAYER) return LVL4 * 1.5;
            return LVL4;
        }
        /*three filled, empty from one side*/
        if (run == 3) return LVL3;
        if (run == 2 && emptyLeft > 0 && emptyRight > 0) return LVL3;
        if (run == 2) return LVL2;
        if (run >= 4) {
            if (player == State.PLAYER) return LVL4 * 1.5;
            return LVL4;
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

        return getHeuristicScore(count, row, 0, player);
    }

    private double getHorizontalEmptyEvaluation(int row, int col, char player) {
        int count = 1;
        int emptyLeft = 0;
        int emptyRight = 0;

        int colIndex = col - 1;
        while (colIndex >= 0 && state2D[row][colIndex] == player) {
            count++;
            colIndex--;
        }
        while (colIndex >= 0 && state2D[row][colIndex] == State.EMPTY) {
            emptyLeft++;
            colIndex--;
        }

        colIndex = col + 1;
        while (colIndex < Game.COLUMNS && state2D[row][colIndex] == player) {
            count++;
            colIndex++;
        }
        while (colIndex < Game.COLUMNS && state2D[row][colIndex] == State.EMPTY) {
            emptyRight++;
            colIndex++;
        }

        return getHeuristicScore(count, emptyLeft, emptyRight, player);
    }

    private double getPositiveDiagonalEvaluation(int row, int col, char player) {
        int count = 1;
        int emptyLeft = 0;
        int emptyRight = 0;

        int colIndex = col + 1;
        int rowIndex = row - 1;
        while (colIndex < Game.COLUMNS && rowIndex >= 0 && state2D[rowIndex][colIndex] == player) {
            count++;
            colIndex++;
            rowIndex--;
        }
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
        while (colIndex >= 0 && rowIndex < Game.ROWS && state2D[rowIndex][colIndex] == State.EMPTY) {
            emptyLeft++;
            colIndex--;
            rowIndex++;
        }

        return getHeuristicScore(count, emptyLeft, emptyRight, player);
    }

    private double getNegativeDiagonalEvaluation(int row, int col, char player) {
        int count = 1;
        int emptyLeft = 0;
        int emptyRight = 0;

        int colIndex = col - 1;
        int rowIndex = row - 1;
        while (colIndex >= 0 && rowIndex >= 0 && state2D[rowIndex][colIndex] == player) {
            count++;
            colIndex--;
            rowIndex--;
        }
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
        while (colIndex < Game.COLUMNS && rowIndex < Game.ROWS && state2D[rowIndex][colIndex] == State.EMPTY) {
            emptyRight++;
            colIndex++;
            rowIndex++;
        }

        return getHeuristicScore(count, emptyLeft, emptyRight, player);
    }

    private boolean isValidMove(int row, int col) {
        if (row == Game.ROWS - 1) return true;
        return state2D[row + 1][col] != State.EMPTY;
    }
}
