import edu.bu.met.CS622.JicJacJoe.Board.Board;
import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerList;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerOne;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerTwo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    Board board;
    PlayerList<Player> players;

    @BeforeEach
    void setUp() {
        players = new PlayerList<>();
        players.add(new PlayerOne("X", Player.PlayerType.USER));
        players.add(new PlayerTwo("O", Player.PlayerType.CPU));

        board = new Board(players, Board.BoardModes.PVC);
    }

    @Test
    void validateBoardData() {
        Assertions.assertNotNull(board.boardData);
        Assertions.assertFalse(board.boardData.isEmpty());
        Assertions.assertEquals(board.boardData.size(), 9);
    }

    @Test
    void getASCIIBoard() {
        Assertions.assertNotNull(board.getASCIIBoard());
        Assertions.assertFalse(board.getASCIIBoard().trim().isEmpty());
    }

    @Test
    void getBoardMode() {
        Assertions.assertNotNull(board.getBoardMode());
        Assertions.assertEquals(board.getBoardMode(), Board.BoardModes.PVC);
    }

    @Test
    void isBoardFirstMove() {
        Assertions.assertTrue(board.isBoardFirstMove());
        board.disableBoardFirstMove();
        Assertions.assertFalse(board.isBoardFirstMove());
    }

    @Test
    void getPlayerByKey() {
        Assertions.assertNotNull(board.players.getPlayerByKey(Player.PlayerKeys.ONE));
        Assertions.assertTrue(board.players.getPlayerByKey(Player.PlayerKeys.ONE) instanceof PlayerOne);

        Assertions.assertNotNull(board.players.getPlayerByKey(Player.PlayerKeys.TWO));
        Assertions.assertTrue(board.players.getPlayerByKey(Player.PlayerKeys.TWO) instanceof PlayerTwo);
    }

    @AfterEach
    void tearDown() {
        board = null;
        players = null;
    }
}
