package com.quadcore.connectfour.model.heuristics;

import com.quadcore.connectfour.model.state.State;

import java.util.List;

public class Window {

    public enum Orientation {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL
    }

    public final Orientation orientation;
    public final int countFilled;
    public final int countEmptyLeft;
    public final int countEmptyRight;

    private Window(Orientation orientation, int countFilled, int countEmptyLeft, int countEmptyRight) {
        this.orientation = orientation;
        this.countFilled = countFilled;
        this.countEmptyLeft = countEmptyLeft;
        this.countEmptyRight = countEmptyRight;
    }

    protected static List<Window> getAIWindows(State state) {
        //todo
        return null;
    }

    protected static List<Window> getPlayerWindows(State state) {
        //todo
        return null;
    }
}
