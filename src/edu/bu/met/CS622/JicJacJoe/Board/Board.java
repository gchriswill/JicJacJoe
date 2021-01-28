package edu.bu.met.CS622.JicJacJoe.Board;
import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerOne;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerTwo;

import java.util.HashMap;
import java.util.Map;

public class Board {

    public enum BoardModes {PvC, PvP}

    private PlayerOne playerOne;
    private PlayerTwo playerTwo;
    private BoardModes boardMode;
    private boolean boardFirstMove = true;

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

    public Board() {}

    public Board(Map<Player.PlayerKeys, Player> players, BoardModes mode) {
        this.playerOne = (PlayerOne) players.get(Player.PlayerKeys.ONE); // down casting
        this.playerTwo = (PlayerTwo) players.get(Player.PlayerKeys.TWO); // down casting
        this.boardMode = mode;
    }

    public BoardModes getBoardMode() {
        return boardMode;
    }

    public boolean isBoardFirstMove() {
        return boardFirstMove;
    }

    public void disableBoardFirstMove() {
        if (boardFirstMove) {
            this.boardFirstMove = false;
        }
    }

    public Player getPlayerByKey(Player.PlayerKeys key) {

        Player player = null;

        switch (key) {
            case ONE -> player = this.playerOne;
            case TWO -> player = this.playerTwo;
        }

        return player;
    }

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
