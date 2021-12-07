package com.quadcore.connectfour.model.state;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ConnectFourStateTest {

    State state = new ConnectFourState("CPPP0C0CCPPPP0CCCPCPCCCCCCPPPCCCCPPPCPCCCP");

    @Test
    void evaluate() {
        State scoreState = new ConnectFourState("CPPPCCCCCPPPPCCCCPCPCCCCCCPPPCCCCPPPCPCCCP");
        assertEquals(10,scoreState.evaluate());
    }

    @Test
    void getNeighbours() {
        List<State> neighbours = state.getNeighbours();
        List<String> strings = neighbours.stream().map(State::getString).collect(Collectors.toList());
        assertTrue(strings.contains("CPPPCC0CCPPPP0CCCPCPCCCCCCPPPCCCCPPPCPCCCP"));
        assertEquals(2, strings.size());
    }

    @Test
    void getAvailableColumns() {
        List<Integer> availableColumns = state.getAvailableColumns();
        assertEquals(2, availableColumns.size());
        assertTrue(availableColumns.contains(4));
        assertTrue(availableColumns.contains(6));
    }

    @Test
    void getNextState() {
        State next = state.getNextState(4, State.AI);
        assertEquals("CPPPCC0CCPPPP0CCCPCPCCCCCCPPPCCCCPPPCPCCCP", next.getString());
        next = state.getNextState(6, State.PLAYER);
        assertEquals("CPPP0C0CCPPPPPCCCPCPCCCCCCPPPCCCCPPPCPCCCP", next.getString());
    }
}
