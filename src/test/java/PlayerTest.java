import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerOne;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void getCharacter() {

        Player player = new PlayerOne("X", Player.PlayerType.USER);
        Assertions.assertFalse(player.getCharacter().isEmpty());
    }
}