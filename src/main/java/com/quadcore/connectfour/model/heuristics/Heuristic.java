package com.quadcore.connectfour.model.heuristics;

import com.quadcore.connectfour.model.state.State;

public interface Heuristic {

    Double evaluate(State state);

}
