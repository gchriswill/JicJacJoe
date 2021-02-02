import edu.bu.met.CS622.JicJacJoe.Player.Player.PlayerType;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerOne;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerOneTest {

    @Test
    void isSessionStarter() {
        PlayerOne playerOne = new PlayerOne("X", PlayerType.USER);
        Assertions.assertTrue(playerOne.isSessionStarter());
    }
}
