package com.quadcore.connectfour.model;

import com.quadcore.connectfour.model.ai.ConnectFourAI;
import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.ConnectFourState;
import com.quadcore.connectfour.model.state.State;

public class ConnectFourGame implements Game {

    private final ConnectFourAI computer;
    private State state = new ConnectFourState();
    private int turnCount = 0;
    private double processingTime = 0;
    double startTime;
    double endTime;

    public ConnectFourGame(ConnectFourAI computer) {
        this.computer = computer;
    }

    @Override
    public void playPlayer(int col) {
        state = state.getNextState(col, State.PLAYER);
    }

    @Override
    public int playAI() {
        State oldState = state;
        turnCount++;
        startTime = System.currentTimeMillis();
        state = computer.play(state);
        endTime = System.currentTimeMillis();
        processingTime += endTime - startTime;
        return indexOfDifference(oldState.getString(), state.getString()) % Game.COLUMNS;
    }

    @Override
    public TreeNode getMinimaxTree() {
        return computer.getMinimaxTree();
    }

    @Override
    public State.Score getCurrentScore() {
        return state.getScore();
    }

    @Override
    public int getNumberOfNodesExpanded() {
        return computer.getNumberOfNodesExpanded();
    }

    private int indexOfDifference(String str1, String str2) {
        int i = 0;
        while (str1.charAt(i) == str2.charAt(i)) i++;
        return i;
    }

    @Override
    public boolean isTerminalState() {
        return state.isTerminal();
    }

    @Override
    public double getAvgProcessingTime() {
        return processingTime / turnCount;
    }
}
