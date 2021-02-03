import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerTwo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTwoTest {

    PlayerTwo playerTwo;

    @BeforeEach
    void setUp() {
        playerTwo = new PlayerTwo("O", Player.PlayerType.USER);
    }

    @Test
    void isSessionStarter() {
        Assertions.assertFalse(playerTwo.isSessionStarter());
    }

    @AfterEach
    void tearDown() {
        playerTwo = null;
    }
}
