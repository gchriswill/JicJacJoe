package edu.bu.met.CS622.JicJacJoe.Player;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerOneTest {

    PlayerOne playerOne;

    @BeforeEach
    void setUp() {
        playerOne = new PlayerOne("X", Player.PlayerType.USER);
    }

    @Test
    void isSessionStarter() {
        Assertions.assertTrue(playerOne.isSessionStarter());
    }

    @AfterEach
    void tearDown() {
        playerOne = null;
    }
}
