package com.quadcore.connectfour.model.heuristics;

import com.quadcore.connectfour.model.Game;
import com.quadcore.connectfour.model.state.State;

import java.util.List;

public class ConnectFourHeuristic implements Heuristic {
    private final int levelFour = 50;
    private final int levelThree = 15;
    private final int levelTwo = 5;


    private char[][] state2D;

    @Override
    public double evaluate(State state) {
        state2D = state.to2dArray();
        double heuristic = 0;
        State.Score score = state.getScore();
        heuristic += (score.getAIScore() * 1.1 - score.getPlayerScore()) * levelFour;
        for (int i = 0; i < Game.ROWS; i++) {
            for (int j = 0; j < Game.COLUMNS; j++) {
                if (state2D[i][j] == State.EMPTY && isValidMove(i, j)) {
                    heuristic += 1.1 * getEmptySlotEvaluation(i, j, State.AI);
                    heuristic -= getEmptySlotEvaluation(i, j, State.PLAYER);

                }
            }
        }

        return heuristic;

    }

    // row-1 col-1 row+1 col+1
    private double getEmptySlotEvaluation(int row, int col, char player) {

        return getHorizontalEmptyEvaluation(row, col, player)
                + getVerticalEmptyEvaluation(row, col, player)
                + getPositiveDiagonalEmptyEvaluation(row, col, player)
                + getNegativeDiagonalEmptyEvaluation(row, col, player);
    }

    private double getPositiveDiagonalEmptyEvaluation(int row, int col, char player) {
        int upDiagonal = 0;
        int downDiagonal = 0;
        int colIndex = col - 1;
        int rowIndex = row - 1;
        while (colIndex >= 0 && rowIndex >= 0) {
            if (state2D[rowIndex][colIndex] != player) break;
            upDiagonal++;
            colIndex--;
            rowIndex--;
        }
        colIndex = col + 1;
        rowIndex = row + 1;

        while (colIndex < Game.COLUMNS && rowIndex < Game.ROWS) {
            if (state2D[rowIndex][colIndex] != player) break;
            downDiagonal++;
            colIndex++;
            rowIndex++;

        }

        return getHeuristicScore(upDiagonal + downDiagonal + 1);
    }

    private double getVerticalEmptyEvaluation(int row, int col, char player) {
        int count = 0;
        int rowIndex = row + 1;
        while (rowIndex > Game.ROWS) {
            if (state2D[rowIndex][col] != player) break;
            count++;
            rowIndex++;
        }
        return getHeuristicScore(count + 1);
    }

    private double getHorizontalEmptyEvaluation(int row, int col, char player) {
        int rightCount = 0;
        int leftCount = 0;
        int colIndex = col - 1;
        while (colIndex >= 0) {
            if (state2D[row][colIndex] != player) break;
            leftCount++;
            colIndex--;
        }

        colIndex = col + 1;
        while (colIndex < Game.COLUMNS) {
            if (state2D[row][colIndex] != player) break;
            rightCount++;
            colIndex++;
        }
        return getHeuristicScore(rightCount + leftCount + 1);
    }

    private double getHeuristicScore(int sum) {
        if (sum == 2) return levelTwo;
        if (sum == 3) return levelThree;
        if (sum >= 4) return (sum - 3) * levelFour;
        return 0;
    }

    private double getNegativeDiagonalEmptyEvaluation(int row, int col, char player) {
        int upDiagonal = 0;
        int downDiagonal = 0;
        int colIndex = col + 1;
        int rowIndex = row - 1;
        while (colIndex > Game.COLUMNS && rowIndex >= 0) {
            if (state2D[rowIndex][colIndex] != player) break;
            upDiagonal++;
            colIndex++;
            rowIndex--;
        }
        colIndex = col - 1;
        rowIndex = row + 1;

        while (colIndex <= 0 && rowIndex < Game.ROWS) {
            if (state2D[rowIndex][colIndex] != player) break;
            downDiagonal++;
            colIndex--;
            rowIndex++;

        }

        return getHeuristicScore(upDiagonal + downDiagonal + 1);
    }

    private boolean isValidMove(int row, int col) {
        return state2D[row - 1][col] != State.EMPTY;
    }
}
