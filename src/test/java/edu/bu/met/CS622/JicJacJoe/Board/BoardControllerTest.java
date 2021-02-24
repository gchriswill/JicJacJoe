package edu.bu.met.CS622.JicJacJoe.Board;

import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerList;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerOne;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerTwo;
import edu.bu.met.CS622.JicJacJoe.Resources.IllegalUserInputException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("UnnecessaryLocalVariable")
class BoardControllerTest {

    static final String INTENDED_WRONG_INPUT = "INTENDED_WRONG_INPUT";

    PlayerOne playerOne;
    PlayerTwo playerTwo;
    PlayerList<Player> players;
    BoardController boardController;

    @BeforeEach
    void setUp() {
        players = new PlayerList<>();
        players.add(new PlayerOne("X", Player.PlayerType.USER));
        players.add(new PlayerTwo("O", Player.PlayerType.CPU));

        boardController = new BoardController(players, Board.BoardModes.PVC);
    }

    @Test
    void getBoard() {
        Assertions.assertNotNull(boardController.getBoard());
    }

    @Test
    void getCurrentPlayer() {
        Assertions.assertNotNull(boardController.getCurrentPlayer());
    }

    @Test
    void validateMenuInput() {
        Assertions.assertDoesNotThrow(() -> BoardController.validateMenuInput("Start"));
        Assertions.assertDoesNotThrow(() -> BoardController.validateMenuInput("Load"));
        Assertions.assertDoesNotThrow(() -> BoardController.validateMenuInput("Credits"));
        Assertions.assertDoesNotThrow(() -> BoardController.validateMenuInput("Exit"));

        Assertions.assertThrows(IllegalUserInputException.class, () -> BoardController.validateMenuInput(INTENDED_WRONG_INPUT));
    }

    @Test
    void validateModeInput() {
        Assertions.assertDoesNotThrow(() -> BoardController.validateModeInput("PvC"));

        Assertions.assertThrows(IllegalUserInputException.class, () -> BoardController.validateModeInput(INTENDED_WRONG_INPUT));
    }

    @Test
    void validateCharacterInput() {
        Assertions.assertDoesNotThrow(() -> BoardController.validateCharacterInput("X"));
        Assertions.assertDoesNotThrow(() -> BoardController.validateCharacterInput("O"));

        Assertions.assertThrows(IllegalUserInputException.class, () -> BoardController.validateCharacterInput(INTENDED_WRONG_INPUT));
    }

    @Test
    void validateLocationInput() {

        Assertions.assertDoesNotThrow(() -> BoardController.validateLocationInput("3"));

        Assertions.assertThrows(NumberFormatException.class, () -> BoardController.validateLocationInput(INTENDED_WRONG_INPUT));
        Assertions.assertThrows(IllegalUserInputException.class, () -> BoardController.validateLocationInput("100"));
    }

    @Test
    void validateMove() {

        boardController.getBoard().boardData.replace(9, " X");

        Assertions.assertDoesNotThrow(() -> boardController.validateMove(6));

        Assertions.assertThrows(IllegalUserInputException.class, () -> boardController.validateMove(9));
    }

    @Test
    void validateWinner() {

        // 1, 2, 3
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(1, " X");
        boardController.getBoard().boardData.replace(2, " X");
        boardController.getBoard().boardData.replace(3, " X");

        Assertions.assertTrue(boardController.validateWinner());

        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(1, " X");
        boardController.getBoard().boardData.replace(2, " O");
        boardController.getBoard().boardData.replace(3, " 2");

        Assertions.assertFalse(boardController.validateWinner());

        // 4, 5, 6
        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(4, " X");
        boardController.getBoard().boardData.replace(5, " X");
        boardController.getBoard().boardData.replace(6, " X");

        Assertions.assertTrue(boardController.validateWinner());

        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(4, " X");
        boardController.getBoard().boardData.replace(5, " 1");
        boardController.getBoard().boardData.replace(6, " O");

        Assertions.assertFalse(boardController.validateWinner());

        // 7, 8, 9
        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(7, " X");
        boardController.getBoard().boardData.replace(8, " X");
        boardController.getBoard().boardData.replace(9, " X");

        Assertions.assertTrue(boardController.validateWinner());

        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(7, " 9");
        boardController.getBoard().boardData.replace(8, " X");
        boardController.getBoard().boardData.replace(9, " O");

        Assertions.assertFalse(boardController.validateWinner());

        // 1, 4, 7
        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(1, " X");
        boardController.getBoard().boardData.replace(4, " X");
        boardController.getBoard().boardData.replace(7, " X");

        Assertions.assertTrue(boardController.validateWinner());

        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(1, " X");
        boardController.getBoard().boardData.replace(4, " O");
        boardController.getBoard().boardData.replace(7, " O");

        Assertions.assertFalse(boardController.validateWinner());

        // 2, 5, 8
        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(2, " X");
        boardController.getBoard().boardData.replace(5, " X");
        boardController.getBoard().boardData.replace(8, " X");

        Assertions.assertTrue(boardController.validateWinner());

        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(2, " X");
        boardController.getBoard().boardData.replace(5, " O");
        boardController.getBoard().boardData.replace(8, " O");

        Assertions.assertFalse(boardController.validateWinner());

        // 3, 6, 9
        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(3, " X");
        boardController.getBoard().boardData.replace(6, " X");
        boardController.getBoard().boardData.replace(9, " X");

        Assertions.assertTrue(boardController.validateWinner());

        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(3, " X");
        boardController.getBoard().boardData.replace(6, " 3");
        boardController.getBoard().boardData.replace(9, " O");

        Assertions.assertFalse(boardController.validateWinner());

        // 1, 5, 9
        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(1, " X");
        boardController.getBoard().boardData.replace(5, " X");
        boardController.getBoard().boardData.replace(9, " X");

        Assertions.assertTrue(boardController.validateWinner());

        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(1, " X");
        boardController.getBoard().boardData.replace(5, " O");
        boardController.getBoard().boardData.replace(9, " O");

        Assertions.assertFalse(boardController.validateWinner());

        // 3, 5, 7
        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(3, " X");
        boardController.getBoard().boardData.replace(5, " X");
        boardController.getBoard().boardData.replace(7, " X");

        Assertions.assertTrue(boardController.validateWinner());

        boardController.getBoard().boardData.clear();
        boardController.getBoard().boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData.replace(3, " X");
        boardController.getBoard().boardData.replace(5, " O");
        boardController.getBoard().boardData.replace(7, " O");

        Assertions.assertFalse(boardController.validateWinner());
    }

    @Test
    void validateOutOfMoves() {

        Board.BoardMap boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData = boardData;
        boardController.getBoard().boardData.replace(1, " X");
        boardController.getBoard().boardData.replace(2, " X");
        boardController.getBoard().boardData.replace(3, " O");
        boardController.getBoard().boardData.replace(4, " X");
        boardController.getBoard().boardData.replace(5, " O");
        boardController.getBoard().boardData.replace(6, " O");
        boardController.getBoard().boardData.replace(7, " O");
        boardController.getBoard().boardData.replace(8, " X");
        boardController.getBoard().boardData.replace(9, " X");

        Assertions.assertTrue(boardController.validateOutOfMoves());

        boardController.getBoard().boardData.replace(9, " 9");

        Assertions.assertFalse(boardController.validateOutOfMoves());
    }

    @Test
    void performPlayerMove() {

        Board.BoardMap boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData = boardData;

        boardController.performPlayerMove(1);

        Assertions.assertEquals(" X", boardController.getBoard().boardData.get(1));
    }

    @Test
    void performComputerMove() {

        Board.BoardMap boardData = new Board.BoardMap() {{
            put(1, " 1");
            put(2, " 2");
            put(3, " 3");
            put(4, " 4");
            put(5, " 5");
            put(6, " 6");
            put(7, " 7");
            put(8, " 8");
            put(9, " 9");
        }};

        boardController.getBoard().boardData = boardData;

        Integer location = boardController.performComputerMove();

        Assertions.assertEquals(" X", boardController.getBoard().boardData.get(location));
    }

    @Test
    void getRandomMove() {
        int location = boardController.getRandomMove();
        Assertions.assertFalse(location < 1 || location > 9);
    }

    @Test
    void setNextTurn() {
        boardController.getBoard().playerTurn = Player.PlayerKeys.ONE;
        boardController.setNextTurn();
        Assertions.assertEquals(Player.PlayerKeys.TWO, boardController.getBoard().playerTurn);
    }

    @AfterEach
    void tearDown() {
        playerOne = null;
        playerTwo = null;
        players = null;
        boardController = null;
    }
}
