import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerTwo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTwoTest {

    @Test
    void isSessionStarter() {
        PlayerTwo playerTwo = new PlayerTwo("O", Player.PlayerType.USER);
        Assertions.assertFalse(playerTwo.isSessionStarter());
    }
}
