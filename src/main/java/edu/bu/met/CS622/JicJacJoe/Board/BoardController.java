package edu.bu.met.CS622.JicJacJoe.Board;

import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerList;
import edu.bu.met.CS622.JicJacJoe.Resources.IllegalUserInputException;
import org.jetbrains.annotations.NotNull;

import java.util.OptionalInt;
import java.util.Random;

/**
 * The BoardController class is responsible for managing all business logic that does not intersect with user interactions.
 *
 */
@SuppressWarnings({"CommentedOutCode", "RedundantSuppression"})
public class BoardController {

    // The board object of the game
    private Board board;

    /**
     * The constructor of the BoardController object
     * @param players The 2 player objects for the game session
     * @param mode The mode of the game session. Should be PvC or PvP
     */
    public BoardController(PlayerList<Player> players, Board.BoardModes mode) {
        this.board = new Board(players, mode);
    }

    /**
     * The function to validate the menu item selected by the player
     * @param input The menu item input the player has selected
     * @throws IllegalUserInputException The exception thrown if the input does not meet the conditional requirements
     */
    public static void validateMenuInput(@NotNull String input) throws IllegalUserInputException {

        // Conditional statement check for the menu item selected by the player
        if (!input.trim().equalsIgnoreCase("start") && !input.trim().equalsIgnoreCase("load") && !input.trim().equalsIgnoreCase("credits") && !input.trim().equalsIgnoreCase("exit")) {
            throw new IllegalUserInputException("Incorrect menu option! Choose an available menu option.\nAvailable menu options: Start, Load, Credits, Exit");
        }
    }

    /**
     * The function to validate the mode selected by the player
     * @param input The mode input the player has selected
     * @throws IllegalUserInputException The exception thrown if the input does not meet the conditional requirements
     */
    public static void validateModeInput(@NotNull String input) throws IllegalUserInputException {

        // Conditional statement check for the mode selected by the player
        if (!input.trim().equalsIgnoreCase("pvc")) {

            // input.equals("PvP") for to be included in a future module
            if (input.trim().equalsIgnoreCase("PvP")) {
                throw new IllegalUserInputException("PvP is currently not available for module 1.\nPlease try PvC mode...");
            } else {
                throw new IllegalUserInputException("Incorrect game mode! Choose an available game mode.\nAvailable game modes: PvC");
            }
        }
    }

    /**
     * The function to validate the character selected by the player
     * @param input The character input the player has selected
     * @throws IllegalUserInputException The exception thrown if the input does not meet the conditional requirements
     */
    public static void validateCharacterInput(String input) throws IllegalUserInputException {

        // Conditional statement check for the character selected by the player
        if (!input.trim().equalsIgnoreCase("X") && !input.trim().equalsIgnoreCase("O")) {
            throw new IllegalUserInputException("Incorrect Character! Choose an available game character: X or O");
        }
    }

    /**
     * The function to validate the board location selected by the player
     * @param input The location input the player has selected
     * @return The location input parsed to Integer/int
     * @throws NumberFormatException The exception thrown if the input cannot be parsed as Integer/int
     * @throws IllegalUserInputException The exception thrown if the input does not meet the conditional requirements
     */
    public static Integer validateLocationInput(String input) throws NumberFormatException, IllegalUserInputException {

        int intInput;

        // Try/Catch statement check for the parsed location
        try {
            intInput = Integer.parseInt(input);
        } catch (Exception e) {
            throw new NumberFormatException("Incorrect input value! Enter a location number that is from 1 to 9");
        }

        // Conditional statement check for the location selected by the player
        if (intInput < 1 || intInput > 9) {
            throw new IllegalUserInputException("Incorrect location! Enter a location number that is from 1 to 9");
        }

        return intInput;
    }

    // The getter for the Board object
    public Board getBoard() {
        return board;
    }

    // A reset function for house cleaning with a game is restarted or ended
    public void reset() {
        this.board.resetBoard();
        this.board = null;
    }

    // Gets the current player from the board object
    public Player getCurrentPlayer() {
        return this.board.players.getPlayerByKey(this.board.playerTurn);
    }

    /**
     * The function to validate the move selected by the player against the game board value for the location
     * @param location The location the user has selected
     * @throws IllegalUserInputException The exception thrown if the input does not meet the conditional requirements
     */
    public void validateMove(Integer location) throws IllegalUserInputException {

        String character = this.board.boardData.get(location).trim();

        // Conditional statement check for the move with character X attempted by the player
        if (character.trim().equalsIgnoreCase("X")) {
            throw new IllegalUserInputException("Location number " + location + " for X already taken! Choose a location number that is available from the game board.");
        }

        // Conditional statement check for the move with character O attempted by the player
        if (character.trim().equalsIgnoreCase("O")) {
            throw new IllegalUserInputException("Location number " + location + " for O already taken! Choose a location number that is available from the game board.");
        }
    }

    /**
     * The function to validate if any player has won and it hold the winning logical rules of the game.
     * @return Returns ture/false base if the any player has won the game
     */
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

        // Conditional statement check for rule 1, 2, 3
        if (one.equalsIgnoreCase(character) && two.equalsIgnoreCase(character) && three.equalsIgnoreCase(character)) {
            return true;
        }

        // Conditional statement check for rule 4, 5, 6
        if (four.equalsIgnoreCase(character) && five.equalsIgnoreCase(character) && six.equalsIgnoreCase(character)) {
            return true;
        }

        // Conditional statement check for rule 7, 8, 9
        if (seven.equalsIgnoreCase(character) && eight.equalsIgnoreCase(character) && nine.equalsIgnoreCase(character)) {
            return true;
        }

        // Conditional statement check for rule 1, 4, 7
        if (one.equalsIgnoreCase(character) && four.equalsIgnoreCase(character) && seven.equalsIgnoreCase(character)) {
            return true;
        }

        // Conditional statement check for rule 2, 5, 8
        if (two.equalsIgnoreCase(character) && five.equalsIgnoreCase(character) && eight.equalsIgnoreCase(character)) {
            return true;
        }

        // Conditional statement check for rule 3, 6, 9
        if (three.equalsIgnoreCase(character) && six.equalsIgnoreCase(character) && nine.equalsIgnoreCase(character)) {
            return true;
        }

        // Conditional statement check for rule 1, 5, 9
        if (one.equalsIgnoreCase(character) && five.equalsIgnoreCase(character) && nine.equalsIgnoreCase(character)) {
            return true;
        }

        // Conditional statement check for rule 3, 5, 7
        if (three.equalsIgnoreCase(character) && five.equalsIgnoreCase(character) && seven.equalsIgnoreCase(character)) {
            return true;
        }

        // Returns false if no player has won yet
        return false;
    }

    /**
     * The function to validate if there are no more moves available in the current game session
     * @return Returns ture/false base on the game board locations availability
     */
    public boolean validateOutOfMoves() {

        // Looping through all the locations in the board and attempting to match the values at numbers with RegEx
        // Returns false if there is a match
        for (String v : board.boardData.values()) if (v.trim().matches("^-?\\d+$")) return false;

        // Returns ture if there is all items did not matched with a number as string type
        return true;
    }

    // This is for future module or might be removed if unnecessary
//    public void validateEndSession(String input) {
//
//    }

    /**
     * The function that finally executes a user's move by adding/replacing the current value at a specific location in the board object.
     * @param location The location of the board which the value will be added/replaced
     */
    public void performPlayerMove(Integer location) {
        this.board.boardData.replace(location, " " + this.getCurrentPlayer().getCharacter());
    }

    /**
     * The function that finally executes a computer's move by adding/replacing the current value at a specific location in the board object.
     * @return Returns the integers in which the value was added/replaced in the board obejct
     */
    public Integer performComputerMove() {

        Integer location = getRandomMove();

        String character = this.board.boardData.get(location).trim();

        // Conditional statement check for the computer move with character X or O
        if (character.equalsIgnoreCase("X") || character.equalsIgnoreCase("O")) {
            performComputerMove();
        } else {
            this.board.boardData.replace(location, " " + this.getCurrentPlayer().getCharacter());
        }

        return location;
    }

    /**
     * The function to generate a simulated random move
     * @return Returns the random Integer/int
     */
    public int getRandomMove() {

        int randInt = 0;

        // Gets and stores a random optional Integer/int
        OptionalInt optionalInt = new Random().ints(1, 10).findFirst();

        // Conditional statement check for unwrapping the optional Integer/int
        if (optionalInt.isPresent()) {
            randInt = optionalInt.getAsInt();
        }

        return randInt;
    }

    /**
     * The function to swap the turns of the game session
     */
    public void setNextTurn() {

        // Conditional statement check for the current player turn
        if (board.playerTurn.equals(Player.PlayerKeys.ONE)) {
            board.playerTurn = Player.PlayerKeys.TWO;
        } else {
            board.playerTurn = Player.PlayerKeys.ONE;
        }
    }
}
