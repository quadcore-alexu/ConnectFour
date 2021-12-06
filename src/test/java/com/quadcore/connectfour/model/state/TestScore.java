package com.quadcore.connectfour.model.state;

import com.quadcore.connectfour.model.Game;
import com.quadcore.connectfour.model.ai.ConnectFourAI;
import com.quadcore.connectfour.model.state.ConnectFourState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class TestScore {

    private static ConnectFourState state;


    @BeforeAll
    static void setState() {
        state = new ConnectFourState("CPPPCCCCCPPPPCCCCPCPCCCCCCPPPCCCCPPPCPCCCP");

    }

    @Test
    void testScore() {

        Assertions.assertEquals(Double.valueOf(13),state.getScore());
    }


}
