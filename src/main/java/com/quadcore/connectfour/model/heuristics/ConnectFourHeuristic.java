package com.quadcore.connectfour.model.heuristics;

import com.quadcore.connectfour.model.Game;
import com.quadcore.connectfour.model.state.State;


public class ConnectFourHeuristic implements Heuristic {
    private static final int LVL4 = 100;
    private static final int LVL3 = 60;
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

    private double getVerticalEmptyEvaluation(int row, int col, char player) {
        int count = 0;

        int rowIndex = row + 1;
        while (rowIndex < Game.ROWS && state2D[rowIndex][col] == player) {
            count++;
            rowIndex++;
        }

        return getHeuristicScore(count + 1,player);
    }

    private double getHorizontalEmptyEvaluation(int row, int col, char player) {
        int rightCount = 0;
        int leftCount = 0;

        int colIndex = col - 1;
        while (colIndex >= 0 && state2D[row][colIndex] == player) {
            leftCount++;
            colIndex--;
        }

        colIndex = col + 1;
        while (colIndex < Game.COLUMNS && state2D[row][colIndex] == player) {
            rightCount++;
            colIndex++;
        }

        return getHeuristicScore(rightCount + leftCount + 1,player);
    }

    private int getHeuristicScore(int sum,char player) {
        if (sum == 2) return LVL2;
        if (sum == 3) return LVL3;
        if (sum == 4) {if(player==State.PLAYER) return (int) (LVL4*1.1); else return LVL4;}
        return 0;
    }

    private double getPositiveDiagonalEvaluation(int row, int col, char player) {
        int upDiagonal = 0;
        int downDiagonal = 0;

        int colIndex = col + 1;
        int rowIndex = row - 1;
        while (colIndex < Game.COLUMNS && rowIndex >= 0 && state2D[rowIndex][colIndex] == player) {
            upDiagonal++;
            colIndex++;
            rowIndex--;
        }

        colIndex = col - 1;
        rowIndex = row + 1;
        while (colIndex >= 0 && rowIndex < Game.ROWS && state2D[rowIndex][colIndex] == player) {
            downDiagonal++;
            colIndex--;
            rowIndex++;
        }

        return getHeuristicScore(upDiagonal + downDiagonal + 1,player);
    }

    private double getNegativeDiagonalEvaluation(int row, int col, char player) {
        int upDiagonal = 0;
        int downDiagonal = 0;

        int colIndex = col - 1;
        int rowIndex = row - 1;
        while (colIndex >= 0 && rowIndex >= 0 && state2D[rowIndex][colIndex] == player) {
            upDiagonal++;
            colIndex--;
            rowIndex--;
        }

        colIndex = col + 1;
        rowIndex = row + 1;
        while (colIndex < Game.COLUMNS && rowIndex < Game.ROWS && state2D[rowIndex][colIndex] == player) {
            downDiagonal++;
            colIndex++;
            rowIndex++;
        }

        return getHeuristicScore(upDiagonal + downDiagonal + 1,player);
    }

    private boolean isValidMove(int row, int col) {
        if (row == Game.ROWS - 1) return true;
        return state2D[row + 1][col] != State.EMPTY;
    }
}
