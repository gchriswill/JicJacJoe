package edu.bu.met.CS622.JicJacJoe.Board;

import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerOne;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerTwo;
import edu.bu.met.CS622.JicJacJoe.Resources.IllegalUserInputException;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Scanner;


public final class BoardManager {

    public static enum MenuOptions {START, CREDITS, EXIT}

    private BoardManager() {}

    /**
     * A function to populate the main menu to the console
     */
    public static void displayWelcome() {

        System.out.println("\nWelcome to Jic Jac Joe game!\n");

        String gameTitle = """
         ▄▄▄██▀▀  ██▓  ▄████▄      ▄▄▄██▀▀ ▄▄▄       ▄████▄      ▄▄▄██▀▀ ▒█████   ▓█████
           ▒██  ▒▓██▒ ▒██▀ ▀█        ▒██  ▒████▄    ▒██▀ ▀█        ▒██  ▒██▒  ██▒ ▓█   ▀
           ░██  ▒▒██▒ ▒▓█    ▄       ░██  ▒██  ▀█▄  ▒▓█    ▄       ░██  ▒██░  ██▒ ▒███
        ▓██▄██▓ ░░██░▒▒▓▓▄ ▄██    ▓██▄██▓ ░██▄▄▄▄██▒▒▓▓▄ ▄██    ▓██▄██▓ ▒██   ██░ ▒▓█  ▄
         ▓███▒  ░░██░░▒ ▓███▀      ▓███▒  ▒▓█   ▓██░▒ ▓███▀      ▓███▒  ░ ████▓▒░▒░▒████
         ▒▓▒▒░   ░▓  ░░ ░▒ ▒       ▒▓▒▒░  ░▒▒   ▓▒█░░ ░▒ ▒       ▒▓▒▒░  ░ ▒░▒░▒░ ░░░ ▒░
         ▒ ░▒░  ░ ▒ ░   ░  ▒       ▒ ░▒░  ░ ░   ▒▒    ░  ▒       ▒ ░▒░    ░ ▒ ▒░ ░ ░ ░
         ░ ░ ░  ░ ▒ ░ ░            ░ ░ ░    ░   ▒   ░            ░ ░ ░  ░ ░ ░ ▒      ░
         ░   ░    ░   ░ ░          ░   ░        ░   ░ ░          ░   ░      ░ ░  ░   ░     
        """;

        System.out.println(gameTitle);
    }

    /**
     * A function to populate the main menu prompt to the console
     */
    @SuppressWarnings("TryWithIdenticalCatches")
    public static BoardManager.MenuOptions menuPrompt(Scanner scanner) {

        System.out.println("\nMAIN MENU");
        System.out.println("Choose one of the following menu options:");
        System.out.println("\tStart");
        System.out.println("\tCredits");
        System.out.println("\tExit\n");

        try {

            String stringInput = scanner.next();
            BoardController.validateMenuInput(stringInput);

            switch (stringInput.trim().toLowerCase()) {

                // Case start for selection from the menu options
                case "start" -> {
                    return BoardManager.MenuOptions.START;
                }

                // Case credits for selection from the menu options
                case "credits" -> {
                    return BoardManager.MenuOptions.CREDITS;
                }

                // Case exit for selection from the menu options
                case "exit" -> {
                    return BoardManager.MenuOptions.EXIT;
                }
            }

        } catch (IllegalUserInputException e) { // Catch a specific Exception and prints out the exception's message
            System.out.println(e.getLocalizedMessage());
            return menuPrompt(scanner);
        } catch (Exception e) { // Catch base Exception and prints out the exception's message
            System.out.println(e.getLocalizedMessage());
            return menuPrompt(scanner);
        }

        return null;
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    public static @Nullable Board.BoardModes modePrompt(Scanner scanner) {

        System.out.println("\nPlease choose the game mode you'd like to play: PvC or PvP");
        System.out.println("IMPORTANT NOTE: PvP is currently not available for module 1.");

        try {
            String stringInput = scanner.next();

            if (stringInput.trim().equalsIgnoreCase("exit")) {
                System.exit(0);
                return null;
            }

            if (stringInput.trim().equalsIgnoreCase("restart")) {
                restart(scanner);
                return null;
            }

            BoardController.validateModeInput(stringInput);

            Board.BoardModes mode = null;

            switch (stringInput.trim().toLowerCase()) {

                // Case for selection PvC from the game modes
                case "pvc" -> mode = Board.BoardModes.PvC;

                // Case for selection PvP from the game modes
                case "pvp" -> {
                    // PvP is currently not available for module 1
                    // mode = Board.BoardModes.PvP;
                }
            }

            if (!stringInput.trim().equalsIgnoreCase("exit")) {
                if (!stringInput.trim().equalsIgnoreCase("restart")) {
                    return mode;
                }
            }

        } catch (IllegalUserInputException e) { // Catch a specific Exception and prints out the exception's message
            System.out.println(e.getLocalizedMessage());
            return modePrompt(scanner);
        } catch (Exception e) { // Catch base Exception and prints out the exception's message
            System.out.println(e.getLocalizedMessage());
            return modePrompt(scanner);
        }

        return null;
    }

    @SuppressWarnings({"TryWithIdenticalCatches", "ConstantConditions"})
    public static @Nullable Map<Player.PlayerKeys, Player> characterPrompt(Scanner scanner) {

        System.out.println("\nPlease choose the game character you'd like to play with: X or O");
        System.out.println("IMPORTANT NOTE: X always has the first turn... \uD83D\uDE09");

        PlayerOne playerOne = null;
        PlayerTwo playerTwo = null;

        try {

            String stringInput = scanner.next();

            if (stringInput.trim().equalsIgnoreCase("exit")) {
                System.exit(0);
                return null;
            }

            if (stringInput.trim().equalsIgnoreCase("restart")) {
                restart(scanner);
                return null;
            }

            BoardController.validateCharacterInput(stringInput);

            switch (stringInput.trim().toLowerCase()) {

                // Case for selection PvC from the game modes
                case "x" -> {
                    playerOne = new PlayerOne("X", Player.PlayerType.USER);
                    playerTwo = new PlayerTwo("O", Player.PlayerType.CPU);
                }

                // Case for selection PvP from the game modes
                case "o" -> {
                    playerOne = new PlayerOne("X", Player.PlayerType.CPU);
                    playerTwo = new PlayerTwo("O", Player.PlayerType.USER);
                }
            }

            if (playerOne != null && playerTwo != null) {
                return Map.of(Player.PlayerKeys.ONE, playerOne, Player.PlayerKeys.TWO, playerTwo);
            }

        } catch (IllegalUserInputException e) { // Catch a specific Exception and prints out the exception's message
            System.out.println(e.getLocalizedMessage());
            return characterPrompt(scanner);
        } catch (Exception e) { // Catch base Exception and prints out the exception's message
            System.out.println(e.getLocalizedMessage());
            return characterPrompt(scanner);
        }

        return null;
    }

    @SuppressWarnings({"TryWithIdenticalCatches", "RedundantCast", "ConstantConditions", "DuplicatedCode"})
    public static void movePrompt(Scanner scanner, BoardController boardController) {

        Player player = boardController.getCurrentPlayer();
        boolean isWinningMove = false;

        if (player instanceof PlayerOne) {
            if (((PlayerOne) player).isSessionStarter() && boardController.getBoard().isBoardFirstMove()) {
                System.out.println("\nPlayer " + boardController.getCurrentPlayer().getCharacter() + " will start this game session...\n");
                boardController.getBoard().disableBoardFirstMove();
            }
        }

        if (boardController.getBoard().getBoardMode().equals(Board.BoardModes.PvC)) {
            if (player.playerType.equals(Player.PlayerType.CPU)) {
                player.addLocation(boardController.performComputerMove());

                isWinningMove = boardController.validateWinner();

                if (isWinningMove) {
                    winnerPrompt(player);
                    System.out.println(boardController.getBoard().getASCIIBoard());
                    endGameSession(boardController);
                    restart(scanner);
                    return;
                }

                if (boardController.validateOutOfMoves()) {
                    System.out.println("The board has ran out of locations and no winner has been declared!");
                    System.out.println("Restarting the game...");
                    endGameSession(boardController);
                    restart(scanner);
                    return;
                }

                boardController.setNextTurn();
                movePrompt(scanner, boardController);
            }
        }

        if (!isWinningMove && boardController.getBoard() != null) {
            System.out.println(boardController.getBoard().getASCIIBoard());
            System.out.println("\nPlayer " + boardController.getCurrentPlayer().getCharacter() + "'s turn...");
            System.out.println("Please choose an available location number from the game board");
        } else return;

        try {

            String stringInput = scanner.next();

            if (stringInput.trim().equalsIgnoreCase("exit")) {
                endGameSession(boardController);
                return;
            }

            if (stringInput.trim().equalsIgnoreCase("restart")) {
                endGameSession(boardController);
                restart(scanner);
                return;
            }

            int intInput = BoardController.validateLocationInput(stringInput);

            boardController.validateMove(intInput);
            boardController.performPlayerMove(intInput);
            player.addLocation(intInput);

            isWinningMove = boardController.validateWinner();

            if (isWinningMove) {
                winnerPrompt(player);
                System.out.println(boardController.getBoard().getASCIIBoard());
                endGameSession(boardController);
                restart(scanner);
                return;
            }

            if (boardController.validateOutOfMoves()) {
                System.out.println("The board has ran out of locations and no winner has been declared!");
                System.out.println("Restarting the game...");
                endGameSession(boardController);
                restart(scanner);
                return;
            }

            boardController.setNextTurn();
            movePrompt(scanner, boardController);

        } catch (NumberFormatException e) { // Catch a specific Exception and prints out the exception's message
            System.out.println(e.getLocalizedMessage());
            movePrompt(scanner, boardController);
        } catch (IllegalUserInputException e) { // Catch a specific Exception and prints out the exception's message
            System.out.println(e.getLocalizedMessage());
            movePrompt(scanner, boardController);
        } catch (Exception e) { // Catch base Exception and prints out the exception's message
            System.out.println(e.getLocalizedMessage());
            movePrompt(scanner, boardController);
        }
    }

    public static void winnerPrompt(Player player) {
        System.out.println("\nPlayer " + player.getCharacter() + " Wins!!!");
    }

    public static void endGameSession(BoardController boardController) {
        boardController.reset();
    }

    public static BoardController startGameSession(Map<Player.PlayerKeys, Player> players, Board.BoardModes mode) {
        return new BoardController(players, mode);
    }

    public static void displayCredits() {

        System.out.println("\nGame Credits");
        System.out.println("------------");
        System.out.println("\tTerm: Spring-O1, 2021");
        System.out.println("\tProject: Jic-Jac-Joe");
        System.out.println("\tInstructor: Eric J. Braude");
        System.out.println("\tFacilitator: Kuang-Jung Huang, A.K.A. \"Michael\"");
        System.out.println("\tInstitution: Boston University's Metropolitan College");
        System.out.println("\tCourse: CS622 - Advanced Programming Techniques");
        System.out.println("\tProgram: Software Development M.S. (MSSD)");
        System.out.println("\tStudent: Christopher W. Gonzalez Melendez, D.K.A. \"gchriswill\" : Student/Engineer/Developer\n\n");
    }

    public static void sceneRouter(Scanner scanner, @Nullable BoardManager.MenuOptions menuOptions) {
        if (menuOptions != null) {
            switch (menuOptions) {
                case START -> {
                    Board.BoardModes mode = modePrompt(scanner);
                    Map<Player.PlayerKeys, Player> players = characterPrompt(scanner);

                    BoardController boardController = startGameSession(players, mode);
                    movePrompt(scanner, boardController);
                }
                case CREDITS -> {
                    displayCredits();
                    BoardManager.MenuOptions menuOptionsInner = menuPrompt(scanner);
                    sceneRouter(scanner, menuOptionsInner);
                }

                case EXIT -> System.exit(0);
            }
        }
    }

    public static void start() {

        displayWelcome();

        // Initialize a Scanner for gathering input from user
        Scanner scanner = new Scanner(System.in);

        BoardManager.MenuOptions menuOptions = menuPrompt(scanner);
        sceneRouter(scanner, menuOptions);

        scanner.close();
    }

    public static void restart(Scanner scanner) {
        BoardManager.MenuOptions menuOptions = menuPrompt(scanner);
        sceneRouter(scanner, menuOptions);
    }
}
