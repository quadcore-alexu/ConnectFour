package com.quadcore.connectfour.model.state;

import com.quadcore.connectfour.model.Game;
import com.quadcore.connectfour.model.heuristics.ConnectFourHeuristic;
import com.quadcore.connectfour.model.heuristics.Heuristic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConnectFourState implements State {

    public static class ConnectFourScore implements Score {
        private final int player;
        private final int ai;

        public ConnectFourScore(int player, int ai) {
            this.player = player;
            this.ai = ai;
        }

        @Override
        public int getPlayerScore() {
            return player;
        }

        @Override
        public int getAIScore() {
            return ai;
        }
    }

    private final Heuristic heuristic = new ConnectFourHeuristic();
    private final String state;

    /**
     * Creates an empty state.
     */
    public ConnectFourState() {
        this.state = String.valueOf(State.EMPTY).repeat(Game.ROWS * Game.COLUMNS);
    }

    /**
     * Creates state from string.
     *
     * @param state representation. Must be {@code `Game.ROWS * Game.COLUMNS` length}
     */
    public ConnectFourState(String state) {
        if (!isValidState(state)) throw new IllegalArgumentException("Invalid state");
        this.state = state;
    }

    @Override
    public double evaluateHeuristic() {
        return heuristic.evaluate(this);
    }

    @Override
    public List<State> getNeighbours() {
        List<Integer> availableColumns = getAvailableColumns();
        List<State> res = new LinkedList<>();
        for (int col : availableColumns) {
            res.add(getNextState(col, State.AI));
        }
        return res;
    }

    @Override
    public State getNextState(int col, char player) {
        char[][] grid = to2dArray();
        if (grid[0][col] != State.EMPTY) throw new IllegalArgumentException("Can't play in full column");

        //get last empty slot
        int i = 0;
        while (i + 1 < Game.ROWS && grid[i + 1][col] == State.EMPTY) i++;

        grid[i][col] = player;
        return new ConnectFourState(arrayToString(grid));
    }

    @Override
    public char[][] to2dArray() {
        return to2dArrayImp(state);
    }

    @Override
    public List<Integer> getAvailableColumns() {
        char[][] grid = to2dArray();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < Game.COLUMNS; i++) {
            if (grid[0][i] == State.EMPTY) res.add(i);
        }
        return res;
    }

    @Override
    public boolean isTerminal() {
        return !state.contains(String.valueOf(State.EMPTY));
    }

    @Override
    public String getString() {
        return state;
    }

    private char[][] to2dArrayImp(String state) {
        char[][] res = new char[Game.ROWS][Game.COLUMNS];
        for (int i = 0; i < state.length(); i++) {
            res[i / Game.COLUMNS][i % Game.COLUMNS] = state.charAt(i);
        }
        return res;
    }

    private String arrayToString(char[][] grid) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] row : grid)
            for (char c : row)
                stringBuilder.append(c);
        return stringBuilder.toString();

    }

    private boolean isValidState(String state) {
        if (state == null) throw new NullPointerException("State representation can't be null");
        if (state.length() != Game.ROWS * Game.COLUMNS) return false;
        char[][] grid = to2dArrayImp(state);
        for (int i = 0; i < Game.COLUMNS; i++) if (!isValidColumn(i, grid)) return false;
        return true;
    }

    private boolean isValidColumn(int col, char[][] grid) {
        int i = 0;
        //skip all empty slots
        for (; i < Game.ROWS; i++) {
            if (grid[i][col] != State.EMPTY) break;
        }

        //check if there are any empty slots after a filled slot
        for (; i < Game.ROWS; i++) {
            if (grid[i][col] == State.EMPTY) return false;
        }

        return true;
    }

    /**
     * counts connected fours vertically, horizontally, and diagonally
     *
     * @return score of each player
     */
    public Score getScore() {
        boolean[][] markedVertically = new boolean[Game.ROWS][Game.COLUMNS];
        boolean[][] markedHorizontally = new boolean[Game.ROWS][Game.COLUMNS];
        boolean[][] markedDiagonally = new boolean[Game.ROWS][Game.COLUMNS];
        char[][] state2D = to2dArray();
        int playerScore = 0;
        int aiScore = 0;

        for (int i = 0; i < Game.ROWS; i++) {
            for (int j = 0; j < Game.COLUMNS; j++) {
                if (state2D[i][j] == State.AI) {
                    aiScore += testVertical(state2D, markedVertically, i, j, State.AI);
                    aiScore += testHorizontal(state2D, markedHorizontally, i, j, State.AI);
                    aiScore += testDiagonal(state2D, markedDiagonally, i, j, State.AI);
                } else if (state2D[i][j] == State.PLAYER) {
                    playerScore += testVertical(state2D, markedVertically, i, j, State.PLAYER);
                    playerScore += testHorizontal(state2D, markedHorizontally, i, j, State.PLAYER);
                    playerScore += testDiagonal(state2D, markedDiagonally, i, j, State.PLAYER);
                }
            }
        }

        return new ConnectFourScore(playerScore, aiScore);
    }

    private int testVertical(char[][] state2D, boolean[][] marked, int row, int col, char player) {
        if (!marked[row][col]) {
            marked[row][col] = true;
            return countVertical(state2D, marked, row, col, player);
        }
        return 0;
    }

    private int testHorizontal(char[][] state2D, boolean[][] marked, int row, int col, char player) {
        if (!marked[row][col]) {
            marked[row][col] = true;
            return countHorizontal(state2D, marked, row, col, player);
        }
        return 0;
    }

    private int testDiagonal(char[][] state2D, boolean[][] marked, int row, int col, char player) {
        if (!marked[row][col]) {
            marked[row][col] = true;
            return countDiagonal(state2D, marked, row, col, player);
        }
        return 0;
    }

    private int countVertical(char[][] state2D, boolean[][] marked, int row, int col, char player) {
        int countUp = 0;
        int countDown = 0;
        int rowIndex = row - 1;
        while (rowIndex >= 0) {
            if (state2D[rowIndex][col] != player || marked[rowIndex][col]) break;
            marked[rowIndex][col] = true;
            countUp++;
            rowIndex--;
        }
        rowIndex = row + 1;

        while (rowIndex < Game.ROWS) {
            if (state2D[rowIndex][col] != player || marked[rowIndex][col]) break;
            marked[rowIndex][col] = true;
            countDown++;
            rowIndex++;

        }

        int score = 1 + (countUp + countDown + 1 - 4);
        return Math.max(score, 0);
    }

    private int countHorizontal(char[][] state2D, boolean[][] marked, int row, int col, char player) {
        int countLeft = 0;
        int countRight = 0;
        int colIndex = col - 1;
        while (colIndex >= 0) {
            if (state2D[row][colIndex] != player || marked[row][colIndex]) break;
            marked[row][colIndex] = true;
            countLeft++;
            colIndex--;
        }
        colIndex = col + 1;

        while (colIndex < Game.COLUMNS) {
            if (state2D[row][colIndex] != player || marked[row][colIndex]) break;
            marked[row][colIndex] = true;
            countRight++;
            colIndex++;

        }

        int score = 1 + (countRight + countLeft + 1 - 4);
        return Math.max(score, 0);
    }

    private int countDiagonal(char[][] state2D, boolean[][] marked, int row, int col, char player) {
        int upDiagonal = 0;
        int downDiagonal = 0;
        int colIndex = col - 1;
        int rowIndex = row - 1;
        while (colIndex >= 0 && rowIndex >= 0) {
            if (state2D[rowIndex][colIndex] != player || marked[rowIndex][colIndex]) break;
            marked[rowIndex][colIndex] = true;
            upDiagonal++;
            colIndex--;
            rowIndex--;
        }
        colIndex = col + 1;
        rowIndex = row + 1;

        while (colIndex < Game.COLUMNS && rowIndex < Game.ROWS) {
            if (state2D[rowIndex][colIndex] != player || marked[rowIndex][colIndex]) break;
            marked[rowIndex][colIndex] = true;
            downDiagonal++;
            colIndex++;
            rowIndex++;

        }

        int score = 1 + (upDiagonal + downDiagonal + 1 - 4);
        return Math.max(score, 0);
    }

}
