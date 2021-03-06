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

    /**
     * counts connected fours vertically, horizontally, and diagonally
     *
     * @return score of each player
     */
    @Override
    public Score getScore() {
        char[][] state2D = to2dArray();
        int playerScore = 0;
        int aiScore = 0;

        for (int i = 0; i < Game.ROWS; i++) {
            for (int j = 0; j < Game.COLUMNS; j++) {
                if (state2D[i][j] == State.AI) {
                    aiScore += countSlotConnections(state2D, i, j, State.AI);
                } else if (state2D[i][j] == State.PLAYER) {
                    playerScore += countSlotConnections(state2D, i, j, State.PLAYER);
                }
            }
        }

        return new ConnectFourScore(playerScore, aiScore);
    }

    @Override
    public List<State> getNeighbours(char player) {
        List<Integer> availableColumns = getAvailableColumns();
        List<State> res = new LinkedList<>();
        for (int col : availableColumns) {
            res.add(getNextState(col, player));
        }
        return res;
    }

    @Override
    public State getNextState(int col, char player) {
        char[][] grid = to2dArray();
        if (grid[0][col] != State.EMPTY) throw new IllegalArgumentException("Can't play in a full column");

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

    private int countSlotConnections(char[][] state2D, int row, int col, char player) {
        if (row == 0 && state2D[row][col] != player) return 0;
        int connections = 0;
        int i;
        for (i = 0; i < 4 && row - i >= 0; i++) {
            if (state2D[row-i][col] != player)
                break;
        }
        if (i == 4) connections++;

        for (i = 0; i < 4 && col - i >= 0; i++) {
            if (state2D[row][col-i] != player)
                break;
        }
        if (i == 4) connections++;

        for (i = 0; i < 4 && row - i >= 0 && col - i >= 0; i++) {
            if (state2D[row-i][col-i] != player)
                break;
        }
        if (i == 4) connections++;

        for (i = 0; i < 4 && row - i >= 0 && col + i < Game.COLUMNS; i++) {
            if (state2D[row-i][col+i] != player)
                break;
        }
        if (i == 4) connections++;

        return connections;
    }

}
