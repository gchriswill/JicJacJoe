import edu.bu.met.CS622.JicJacJoe.Board.Board;
import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerOne;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerTwo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

class BoardTest {

    Board board;
    PlayerOne playerOne;
    PlayerTwo playerTwo;
    Map<Player.PlayerKeys, Player> players;

    @BeforeEach
    void setUp() {
        playerOne = new PlayerOne("X", Player.PlayerType.USER);
        playerTwo = new PlayerTwo("O", Player.PlayerType.USER);
        players = Map.of(Player.PlayerKeys.ONE, playerOne, Player.PlayerKeys.TWO, playerTwo);
        board = new Board(players, Board.BoardModes.PvC);
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
        Assertions.assertEquals(board.getBoardMode(), Board.BoardModes.PvC);
    }

    @Test
    void isBoardFirstMove() {
        Assertions.assertTrue(board.isBoardFirstMove());
        board.disableBoardFirstMove();
        Assertions.assertFalse(board.isBoardFirstMove());
    }

    @Test
    void getPlayerByKey() {
        Assertions.assertNotNull(board.getPlayerByKey(Player.PlayerKeys.ONE));
        Assertions.assertTrue(board.getPlayerByKey(Player.PlayerKeys.ONE) instanceof PlayerOne);

        Assertions.assertNotNull(board.getPlayerByKey(Player.PlayerKeys.TWO));
        Assertions.assertTrue(board.getPlayerByKey(Player.PlayerKeys.TWO) instanceof PlayerTwo);
    }
}
