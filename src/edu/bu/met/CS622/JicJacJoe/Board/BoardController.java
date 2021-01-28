package edu.bu.met.CS622.JicJacJoe.Board;

import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerTwo;
import edu.bu.met.CS622.JicJacJoe.Resources.IllegalUserInputException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Random;

public class BoardController {

    private Board board;

    public BoardController(Map<Player.PlayerKeys, Player> players, Board.BoardModes mode) {
        this.board = new Board(players, mode);
    }

    public Board getBoard() {
        return board;
    }

    public void reset() {
        this.board.resetBoard();
        this.board = null;
    }

    public Player getCurrentPlayer() {
        return this.board.getPlayerByKey(this.board.playerTurn);
    }

    public static void validateMenuInput(@NotNull String input) throws IllegalUserInputException {
        if (!input.trim().equalsIgnoreCase("start") && !input.trim().equalsIgnoreCase("credits") && !input.trim().equalsIgnoreCase("exit")) {
            throw new IllegalUserInputException("Incorrect menu option! Choose an available menu option.\nAvailable menu options: Start, Credits, Exit");
        }
    }

    public static void validateModeInput(@NotNull String input) throws IllegalUserInputException {

        if (!input.trim().equalsIgnoreCase("pvc")) {

            // input.equals("PvP") for to be included in a future module
            if (input.trim().equalsIgnoreCase("PvP")) {
                throw new IllegalUserInputException("PvP is currently not available for module 1.\nPlease try PvC mode...");
            } else {
                throw new IllegalUserInputException("Incorrect game mode! Choose an available game mode.\nAvailable game modes: PvC");
            }
        }
    }

    public static void validateCharacterInput(String input) throws IllegalUserInputException {
        if (!input.trim().equalsIgnoreCase("X") && !input.trim().equalsIgnoreCase("O")) {
            throw new IllegalUserInputException("Incorrect Character! Choose an available game character: X or O");
        }
    }


    public static Integer validateLocationInput(String input) throws NumberFormatException, IllegalUserInputException {

        int intInput;

        try {
            intInput = Integer.parseInt(input);
        } catch (Exception e) {
            throw new NumberFormatException("Incorrect input value! Enter a location number that is from 1 to 9");
        }

        if (intInput < 1 || intInput > 9) {
            throw new IllegalUserInputException("Incorrect location! Enter a location number that is from 1 to 9");
        }

        return intInput;
    }

    public void validateMove(Integer location) throws IllegalUserInputException {

        String character = this.board.boardData.get(location).trim();

        if (character.trim().equalsIgnoreCase("X")) {
            throw new IllegalUserInputException("Location number " + location + " for X already taken! Choose a location number that is available from the game board.");
        }

        if (character.trim().equalsIgnoreCase("O")) {
            throw new IllegalUserInputException("Location number " + location + " for O already taken! Choose a location number that is available from the game board.");
        }
    }

    @SuppressWarnings({"DuplicatedCode", "RedundantIfStatement"})
    public boolean validateWinner() {

        String character = this.board.playerTurn.equals(Player.PlayerKeys.ONE) ? "X" : "O";

        String one = board.boardData.get(1).trim();
        String two = board.boardData.get(2).trim();
        String three = board.boardData.get(3).trim();
        String four = board.boardData.get(4).trim();
        String five = board.boardData.get(5).trim();
        String six = board.boardData.get(6).trim();
        String seven = board.boardData.get(7).trim();
        String eight = board.boardData.get(8).trim();
        String nine = board.boardData.get(9).trim();

        if (one.equalsIgnoreCase(character) && two.equalsIgnoreCase(character) && three.equalsIgnoreCase(character)) {
            return true;
        }

        if (four.equalsIgnoreCase(character) && five.equalsIgnoreCase(character) && six.equalsIgnoreCase(character)) {
            return true;
        }

        if (seven.equalsIgnoreCase(character) && eight.equalsIgnoreCase(character) && nine.equalsIgnoreCase(character)) {
            return true;
        }

        if (one.equalsIgnoreCase(character) && four.equalsIgnoreCase(character) && seven.equalsIgnoreCase(character)) {
            return true;
        }

        if (two.equalsIgnoreCase(character) && five.equalsIgnoreCase(character) && eight.equalsIgnoreCase(character)) {
            return true;
        }

        if (three.equalsIgnoreCase(character) && six.equalsIgnoreCase(character) && nine.equalsIgnoreCase(character)) {
            return true;
        }

        if (one.equalsIgnoreCase(character) && five.equalsIgnoreCase(character) && nine.equalsIgnoreCase(character)) {
            return true;
        }

        if (three.equalsIgnoreCase(character) && five.equalsIgnoreCase(character) && seven.equalsIgnoreCase(character)) {
            return true;
        }

        return false;
    }

    public boolean validateOutOfMoves() {

        for (String v : board.boardData.values()) if (v.trim().matches("^-?\\d+$")) return false;

        return true;
    }

    public void validateEndSession(String input) {

    }

    public void performPlayerMove(Integer location) {
        this.board.boardData.replace(location, " " + this.getCurrentPlayer().getCharacter());
    }

    public Integer performComputerMove() {

        Integer location = getRandomMove();

        String character = this.board.boardData.get(location).trim();

        if (character.equalsIgnoreCase("X") || character.equalsIgnoreCase("O")) {
            performComputerMove();
        } else {
            this.board.boardData.replace(location, " " + this.getCurrentPlayer().getCharacter());
        }

        return location;
    }

    public int getRandomMove() {

        int randInt = 0;

        OptionalInt optionalInt = new Random().ints(1, 10).findFirst();
        if (optionalInt.isPresent()) {
            randInt = optionalInt.getAsInt();
        }

        return randInt;
    }

    public void setNextTurn() {
        if (board.playerTurn.equals(Player.PlayerKeys.ONE)) {
            board.playerTurn = Player.PlayerKeys.TWO;
        } else {
            board.playerTurn = Player.PlayerKeys.ONE;
        }
    }
}
