import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerOne;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

    Player player;
    String character;

    @BeforeEach
    void setUp() {
        player = new PlayerOne("X", Player.PlayerType.USER);
        character = player.getCharacter();
    }

    @Test
    void getCharacter() {
        Assertions.assertNotNull(character);
        Assertions.assertFalse(character.trim().isEmpty());
    }

    @AfterEach
    void tearDown() {
        player = null;
        character = null;
    }
}
