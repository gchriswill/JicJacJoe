package edu.bu.met.CS622.JicJacJoe.Board;
import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerOne;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerTwo;

import java.util.HashMap;
import java.util.Map;

/**
 * The Board class is responsible for the I/O to the underlying data structure that holds the game board data.
 *
 */
public class Board {

    // The enum for identifying the game modes
    public enum BoardModes {PvC, PvP}

    // The player one object
    private PlayerOne playerOne;

    // The player two object
    private PlayerTwo playerTwo;

    // The board mode object
    private final BoardModes boardMode;

    // A property for checking if the board has processed the first move
    private boolean boardFirstMove = true;

    // The current player that is performing a move
    public Player.PlayerKeys playerTurn = Player.PlayerKeys.ONE;

    // A Map for mapping the values of the game board in text,
    // making programmatic setter/getter access easy.
    public Map<Integer, String> boardData = new HashMap<>() {{
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

//    Custom Game board made of text with ASCII art pattern.
//    This string serves as graphical interface for the game.
    public String getASCIIBoard() {
        return """
        *************************
        *       *       *       *
        *  %s   *  %s   *  %s   *
        *       *       *       *
        *************************
        *       *       *       *
        *  %s   *  %s   *  %s   *
        *       *       *       *
        *************************
        *       *       *       *
        *  %s   *  %s   *  %s   *
        *       *       *       *
        *************************
        """.formatted(
                this.boardData.get(1),
                this.boardData.get(2),
                this.boardData.get(3),
                this.boardData.get(4),
                this.boardData.get(5),
                this.boardData.get(6),
                this.boardData.get(7),
                this.boardData.get(8),
                this.boardData.get(9)
        );
    }

    // The default constructor of the Board object
    // This will be used in future module or removed if not necessary
//    public Board() {}

    /**
     * The constructor of the Board object
     * @param players The players for the game
     * @param mode The mode of the game
     */
    public Board(Map<Player.PlayerKeys, Player> players, BoardModes mode) {
        this.playerOne = (PlayerOne) players.get(Player.PlayerKeys.ONE); // down casting
        this.playerTwo = (PlayerTwo) players.get(Player.PlayerKeys.TWO); // down casting
        this.boardMode = mode;
    }

    // Gets the current board mode
    public BoardModes getBoardMode() {
        return boardMode;
    }

    // Gets the board first move
    public boolean isBoardFirstMove() {
        return boardFirstMove;
    }

    // Sets the board first move
    // Has a conditional requirement for setting the value only once
    public void disableBoardFirstMove() {
        if (boardFirstMove) {
            this.boardFirstMove = false;
        }
    }

    /**
     * The function to get the current player by passing Player Key
     * @param key The key to access the current player
     * @return Returns the current player object
     */
    public Player getPlayerByKey(Player.PlayerKeys key) {

        Player player = null;

        switch (key) {
            case ONE -> player = this.playerOne;
            case TWO -> player = this.playerTwo;
        }

        return player;
    }

    // Resets the board with default values
    public void resetBoard() {
        this.playerOne = null;
        this.playerTwo = null;
        boardData = new HashMap<>() {{
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
    }
}
