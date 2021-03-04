package edu.bu.met.CS622.JicJacJoe.Board;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.bu.met.CS622.JicJacJoe.Player.Player;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerList;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerOne;
import edu.bu.met.CS622.JicJacJoe.Player.PlayerTwo;
import edu.bu.met.CS622.JicJacJoe.Resources.IllegalUserInputException;
import edu.bu.met.CS622.JicJacJoe.Resources.SaveSessionErrorException;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The BoardManager class responsibility is to serve as a mediator between the user interactions
 * and the business logic, in which is responsibility of the BoardController class.
 */
public final class BoardManager {

    // A String property for the welcome message and the credits scene
    public static String gameTitle = """
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

    /**
     * The Serializable inner class for to save the game's data
     */
    public static class BoardSession implements Serializable {
        public String json;

        public BoardSession(String json) {
            this.json = json;
        }
    }

    // Menu options enumeration
    public enum MenuOptions {START, LOAD, CREDITS, EXIT}

    // Restricted constructor
    private BoardManager() {}

    /**
     * A function to display the welcome message to the console
     */
    public static void displayWelcome() {

        System.out.println("\nWelcome to Jic Jac Joe game!\n");
        System.out.println(gameTitle);
    }

    /**
     * A function to display the credits scene of the game
     */
    public static void displayCredits() {

        System.out.println("\n" + gameTitle);

        System.out.println("\tGame Credits");
        System.out.println("\t------------");
        System.out.println("\tTerm: Spring-O1, 2021");
        System.out.println("\tProject: Jic-Jac-Joe");
        System.out.println("\tInstructor: Eric J. Braude");
        System.out.println("\tFacilitator: Kuang-Jung Huang, A.K.A. \"Michael\"");
        System.out.println("\tInstitution: Boston University's Metropolitan College");
        System.out.println("\tCourse: CS622 - Advanced Programming Techniques");
        System.out.println("\tProgram: Software Development M.S. (MSSD)");
        System.out.println("\tStudent: Christopher W. Gonzalez Melendez, D.K.A. \"gchriswill\" : Student/Engineer/Developer\n\n");
    }

    /**
     * A function to display the save confirmation message to the console
     */
    public static void displaySaved() {
        System.out.println("\nYour game session has been saved\n");
        System.out.println("\nThanks for playing checking Jic Jac Joe game! \uD83D\uDE09 \uD83D\uDC4D \n");
    }

    /**
     * A function to populate the main menu scene to the console
     */
    @SuppressWarnings("TryWithIdenticalCatches")
    public static BoardManager.MenuOptions menuPrompt(Scanner scanner) {

        /*
        * INTENT: The intent of this function is to display the menu items of the game
        * and also to collect the selected menu item;
        *
        * IMPORTANT NOTE: This function recursively call itself if the input is invalid or if it catches an exception;
        *
        * PRECONDITION: UserInput == ("start") || UserInput == "credits" || UserInput == "exit";
        * Based on validated input from `validateMenuInput` function from the `BoardController` class;
        *
        * CATCHES: The function catches custom exception thrown by the `validateMenuInput` function;
        * Also, it catches default exceptions if any arises during it's execution;
        *
        * RETURNS: Returns the value of MenuOptions type for further processing by `start` function;
        *
        * */

        System.out.println("\nMAIN MENU");
        System.out.println("Choose one of the following menu options:");
        System.out.println("\tStart");
        System.out.println("\tLoad");
        System.out.println("\tCredits");
        System.out.println("\tExit\n");

        try {

            String stringInput = scanner.next();
            BoardController.validateMenuInput(stringInput);

            switch (stringInput.trim().toLowerCase()) {

                // Case start for selection from the menu options
                case "start" -> {
                    return MenuOptions.START;
                }

                // Case credits for selection from the menu options
                case "credits" -> {
                    return MenuOptions.CREDITS;
                }

                // Case exit for selection from the menu options
                case "exit" -> {
                    return MenuOptions.EXIT;
                }
            }

            // Case load for selection from the menu options
            if (stringInput.trim().equalsIgnoreCase("load")) {
                return MenuOptions.LOAD;
            }

        } catch (IllegalUserInputException e) { // Catch a specific Exception and prints out the exception's message
            System.out.println("\n" + e.getLocalizedMessage());
            return menuPrompt(scanner); // Recursively call itself
        } catch (Exception e) { // Catch base Exception and prints out the exception's message
            System.out.println("\n" + e.getLocalizedMessage());
            return menuPrompt(scanner); // Recursively call itself
        }

        return null;
    }

    /**
     * A function for populating a prompt asking for the game mode value
     * @param scanner The object used to collect the user's input
     * @return Returns the selected and validated game mode
     */
    @SuppressWarnings("TryWithIdenticalCatches")
    public static @Nullable Board.BoardModes modePrompt(Scanner scanner) {

        /*
         * INTENT: The main intent of this function is to collect the game mode chosen by the player;
         *
         * IMPORTANT NOTE: This function recursively call itself if the input is invalid or if it catches an exception;
         *
         * PRECONDITION 1: UserInput != ("exit") || UserInput == "restart";
         *
         * PRECONDITION 2: UserInput == ("pvc") || UserInput != "PvP";
         * Based on validated input from `validateModeInput` function from the `BoardController` class;

         * CATCHES: The function catches custom exception thrown by the `validateModeInput` function;
         * Also, it catches default exceptions if any arises during it's execution;
         *
         * RETURNS: Returns the value of BoardModes type for further processing by `sceneRouter` function;
         *
         * */

        System.out.println("\nPlease choose the game mode you'd like to play: PvC or PvP");
        System.out.println("IMPORTANT NOTE: PvP is currently not available for module 1.");

        try {
            String stringInput = scanner.next();

            // TODO: Needs refactoring and abstraction to new BoardManager.specializedCommand() function
            // TODO: Needs refactoring and abstraction to new BoardManager.SpecializedCommands enum
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
                case "pvc" -> mode = Board.BoardModes.PVC;

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
            System.out.println("\n" + e.getLocalizedMessage());
            return modePrompt(scanner); // Recursively call itself
        } catch (Exception e) { // Catch base Exception and prints out the exception's message
            System.out.println("\n" + e.getLocalizedMessage());
            return modePrompt(scanner); // Recursively call itself
        }

        return null;
    }

    /**
     * A function for populating a prompt asking for the player's game character value
     * @param scanner The object used to collect the user's input
     * @return Returns the player objects wrapped in a dictionary
     */
    @SuppressWarnings({"TryWithIdenticalCatches"})
    public static @Nullable PlayerList<Player> characterPrompt(Scanner scanner) {

        /*
         * INTENT: The main intent of this function is to collect the character chosen by the player;
         *
         * IMPORTANT NOTE: This function recursively call itself if the input is invalid or if it catches an exception;
         *
         * PRECONDITION 1: UserInput != ("exit") || UserInput == "restart";
         *
         * PRECONDITION 2: UserInput == ("X") || UserInput == "O";
         * Based on validated input from `validateCharacterInput` function from the `BoardController` class;

         * CATCHES: The function catches custom exception thrown by the `validateCharacterInput` function;
         * Also, it catches default exceptions if any arises during it's execution;
         *
         * RETURNS: Returns a dictionary value containing 2 player objects further processing by `sceneRouter` function;
         *
         * */

        System.out.println("\nPlease choose the game character you'd like to play with: X or O");
        System.out.println("IMPORTANT NOTE: X always has the first turn... \uD83D\uDE09");

        PlayerList<Player> players = new PlayerList<>();

        try {

            String stringInput = scanner.next();

            // TODO: Needs refactoring and abstraction to new BoardManager.specializedCommand() function
            // TODO: Needs refactoring and abstraction to new BoardManager.SpecializedCommands enum
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
                    players.add(new PlayerOne("X", Player.PlayerType.USER));
                    players.add(new PlayerTwo("O", Player.PlayerType.CPU));
                }

                // Case for selection PvP from the game modes
                case "o" -> {
                    players.add(new PlayerOne("X", Player.PlayerType.CPU));
                    players.add(new PlayerTwo("O", Player.PlayerType.USER));
                }
            }

            if (!players.isEmpty()) {
                return players;
            }

        } catch (IllegalUserInputException e) { // Catch a specific Exception and prints out the exception's message
            System.out.println("\n" + e.getLocalizedMessage());
            return characterPrompt(scanner); // Recursively call itself
        } catch (Exception e) { // Catch base Exception and prints out the exception's message
            System.out.println("\n" + e.getLocalizedMessage());
            return characterPrompt(scanner); // Recursively call itself
        }

        return null;
    }

    /**
     * A function for populating a prompt asking for the player's move value
     * This function needs strong breakdown-refactoring
     * @param scanner The object used to collect the user's input
     * @param boardController The object containing all the non-view business logic
     */
    @SuppressWarnings({"TryWithIdenticalCatches", "RedundantCast", "ConstantConditions", "DuplicatedCode"})
    public static void movePrompt(Scanner scanner, BoardController boardController) {

        /*
         * INTENT: The main intent of this function is to collect the move chosen by the player;
         *
         * IMPORTANT NOTE: This function recursively call itself if the input is invalid or if it catches an exception;
         *
         * PRECONDITION 1: UserInput != ("exit") || UserInput == "restart";
         *
         * PRECONDITION 2: UserInput is Integer/int type && (intInput < 1 || intInput > 9)
         * Based on validated input from `validateLocationInput` function from the `BoardController` class;
         *
         * PRECONDITION 3: boardData[UserInput] != X || O
         * Based on validated input from `validateMove` function from the `BoardController` class;
         *
         * POSTCONDITION 1: Performed a winning move :: boardData contains CurrentPlayerCharacter in locations
         * 1, 2, 3 || 4, 5, 6 || 7, 8, 9 || 1, 4, 7 || 2, 5, 8 || 3, 6, 9 || 1, 5, 9 || 3, 5, 7
         * Based on validated input from `validateWinner` function from the `BoardController` class;
         *
         * POSTCONDITION 2: Ran out of move :: boardData does not contains any number :: for v in boardData { not matches RegEx }
         * Based on validated input from `validateOutOfMoves` function from the `BoardController` class;
         *
         * CATCHES: The function catches custom exception thrown by the `validateLocationInput` and `validateMove` functions;
         * Also, it catches default exceptions if any arises during it's execution;
         *
         * */

        // Gets and stores the current player for further processing within this function
        Player player = boardController.getCurrentPlayer();
        boolean isWinningMove = false;

        // Conditional statements to check if this is a first move from the game's lifecycle
        if (player instanceof PlayerOne) {
            if (((PlayerOne) player).isSessionStarter() && boardController.getBoard().isBoardFirstMove()) {
                System.out.println("\nPlayer " + player.getCharacter() + " will start this game session...\n");
                boardController.getBoard().disableBoardFirstMove();
            }
        }

        // Conditional statements to check if this is a collected move from the computer and process it.
        // Ideally, this code block should be in its own function in the BoardController and it will be
        // moved to that location in a future module.
        if (boardController.getBoard().getBoardMode().equals(Board.BoardModes.PVC)) {
            if (player.playerType.equals(Player.PlayerType.CPU)) {
                player.addLocation(boardController.performComputerMove());

                // Stores the return value of the winning move validation from the `validateWinner`
                isWinningMove = boardController.validateWinner();

                // Conditional statements to check if this is a winning move
                if (isWinningMove) {
                    winnerPrompt(player);
                    System.out.println(boardController.getBoard().getASCIIBoard());
                    dumpSessionStorage(boardController);
                    endGameSession(boardController);
                    restart(scanner);
                    return;
                }

                // Conditional statements to check if the game has no more moves available, and if so, the process it.
                // Ideally, this code block should be in its own function in the BoardController and it will be
                // moved to that location in a future module.
                if (boardController.validateOutOfMoves()) {
                    System.out.println("The board has ran out of locations and no winner has been declared!");
                    System.out.println("Restarting the game...");
                    dumpSessionStorage(boardController);
                    endGameSession(boardController);
                    restart(scanner);
                    return;
                }

                // Executes next turn to swap the payer move logic
                boardController.setNextTurn();

                // Recursively call itself for the next turn logic
                movePrompt(scanner, boardController);
            }
        }

        // Conditional statements to check if this is a winning move
        if (!isWinningMove && boardController.getBoard() != null) {
            System.out.println("\n" + boardController.getBoard().getASCIIBoard());
            System.out.println("\nPlayer " + boardController.getCurrentPlayer().getCharacter() + "'s turn...");
            System.out.println("Please choose an available location number from the game board");
        } else return;

        try {

            String stringInput = scanner.next();

            // TODO: Needs refactoring and abstraction to new BoardManager.specializedCommand() function
            // TODO: Needs refactoring and abstraction to new BoardManager.SpecializedCommands enum
            if (stringInput.trim().equalsIgnoreCase("exit")) {
                endGameSession(boardController);
                return;
            }

            if (stringInput.trim().equalsIgnoreCase("restart")) {
                endGameSession(boardController);
                restart(scanner);
                return;
            }

            if (stringInput.trim().equalsIgnoreCase("save")) {
                saveSession(boardController);
                displaySaved();
                endGameSession(boardController);
                restart(scanner);
                return;
            }

            int intInput = BoardController.validateLocationInput(stringInput);

            boardController.validateMove(intInput);
            boardController.performPlayerMove(intInput);
            player.addLocation(intInput);

            // Stores the return value of the winning move validation from the `validateWinner`
            isWinningMove = boardController.validateWinner();

            // Conditional statements to check if this is a winning move
            if (isWinningMove) {
                winnerPrompt(player);
                System.out.println(boardController.getBoard().getASCIIBoard());
                dumpSessionStorage(boardController);
                endGameSession(boardController);
                restart(scanner);
                return;
            }

            // Conditional statements to check if the game has no more moves available, and if so, the process it.
            // Ideally, this code block should be in its own function in the BoardController and it will be
            // moved to that location in a future module.
            if (boardController.validateOutOfMoves()) {
                System.out.println("The board has ran out of locations and no winner has been declared!");
                System.out.println("Restarting the game...");
                dumpSessionStorage(boardController);
                endGameSession(boardController);
                restart(scanner);
                return;
            }

            // Executes next turn to swap the payer move logic
            boardController.setNextTurn();

            // Recursively call itself for the next turn logic
            movePrompt(scanner, boardController);

        } catch (NumberFormatException e) { // Catch a specific Exception and prints out the exception's message
            System.out.println("\n" + e.getClass() + "::" + e.getLocalizedMessage());
            movePrompt(scanner, boardController); // Recursively call itself
        } catch (IllegalUserInputException e) { // Catch a specific Exception and prints out the exception's message
            System.out.println("\n" + e.getClass() + "::" + e.getLocalizedMessage());
            movePrompt(scanner, boardController); // Recursively call itself
        } catch (SaveSessionErrorException e) { // Catch a specific Exception and prints out the exception's message
            System.out.println("\n" + e.getClass() + "::" + e.getLocalizedMessage());
            movePrompt(scanner, boardController); // Recursively call itself
        } catch (Exception e) { // Catch the base Exception and prints out the exception's message
            System.out.println("\n" + e.getClass() + "::" + e.getLocalizedMessage());
            movePrompt(scanner, boardController); // Recursively call itself
        }
    }

    /**
     * A function to display the winner of the game
     * @param player The player that won
     */
    public static void winnerPrompt(Player player) {
        System.out.println("\nPlayer " + player.getCharacter() + " Wins!!!");
    }

    /**
     * A function to executes the end game process
     * @param boardController The object containing all the non-view business logic
     */
    public static void endGameSession(BoardController boardController) {
        boardController.reset();
    }

    /**
     * A function to executes the start game process
     * @param players The player objects that will play the game. This could be a user or the computer
     * @param mode The selected and validated game mode
     * @return Returns the object containing all the non-view business logic
     */
    public static BoardController startGameSession(PlayerList<Player> players, Board.BoardModes mode) {
        return new BoardController(players, mode);
    }

    /**
     * A function to save a current game session from a given BoardController object
     * The game data is saved as a BoardSession serializable object containing raw JSON string with the game's data,
     * and then is serialized for to be written into a `jicjacjoe.dat` file.
     *
     * @param boardController The BoardController object to save the current game session from
     */
    @SuppressWarnings("rawtypes")
    public static void saveSession(BoardController boardController) throws SaveSessionErrorException {

        Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap>(){}.getType();

        Board board = boardController.getBoard();

        // TODO: Needs refactoring and abstraction to new BoardController.getSessionJson() function
        Map<String, String> sessionMap = new HashMap<>() {{
            put("savedMode", board.getBoardMode().toString());
            put("savedTurn", board.playerTurn.toString());
            put("savedType", boardController.getCurrentPlayer().playerType.toString());
            put("savedCharacter", boardController.getCurrentPlayer().getCharacter());
            put("boardData", board.getBoardJson());
        }};

        String sessionString = gson.toJson(sessionMap, gsonType);

        try {

            File file = new File("jicjacjoe.dat");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // The serializable object to write
            BoardSession boardSession = new BoardSession(sessionString);

            // Object write to output stream
            oos.writeObject(boardSession);

            oos.close();
            fos.close();

        } catch (Exception e) {
            System.out.println("\nJic jac Joe encountered an error while saving the current game session.");
            System.out.println("Please try again during your next game session.");
            throw new SaveSessionErrorException(e.getLocalizedMessage());
        }
    }

    /**
     * A function to load a saved game session from a given scanner object
     * The game session is loaded from reading a BoardSession serializable object containing raw JSON string data,
     * stored in a `jicjacjoe.dat` file.
     *
     * @param scanner The scanner object to load the game session from
     */
    public static void loadSession(Scanner scanner) {

        Board.BoardModes mode;
        Player.PlayerKeys turn;
        PlayerList<Player> players;
        String rawBoard;

        try {

            File file = new File("jicjacjoe.dat");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            // Object read from input stream
            BoardSession boardSession = (BoardSession) ois.readObject();

            ois.close();
            fis.close();

            Type TokenTypeOut = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> jsonMap1 = new Gson().fromJson(boardSession.json, TokenTypeOut);

            // TODO: Needs refactoring and abstraction to new BoardController.startGameSession(Map<String, String> jsonMap) function
            Player.PlayerType playerType = Player.PlayerType.valueOf(jsonMap1.get("savedType"));
            players = loadCharacters(jsonMap1.get("savedCharacter"), playerType);

            turn = Player.PlayerKeys.valueOf(jsonMap1.get("savedTurn"));
            mode = Board.BoardModes.valueOf(jsonMap1.get("savedMode"));
            rawBoard = jsonMap1.get("boardData");

            Type TokenTypeOutIn = new TypeToken<Board.BoardMap>(){}.getType();
            Board.BoardMap jsonMap2 = new Gson().fromJson(rawBoard, TokenTypeOutIn);

            BoardController boardController = startGameSession(players, mode);
            boardController.getBoard().playerTurn = turn;
            boardController.getBoard().boardData = jsonMap2;

            boardController.getBoard().isCurrentSessionFromLoad = true;

            movePrompt(scanner, boardController);

        } catch (FileNotFoundException e) {
            System.out.println("\n" + e.getLocalizedMessage());
            System.out.println("Jic jac Joe couldn't find any game session saved.");
            System.out.println("A game session can be saved by sending a `save` command during a game session.");
            BoardManager.MenuOptions menuOptionsInner = menuPrompt(scanner);
            sceneRouter(scanner, menuOptionsInner);
        } catch (IOException e) {
            System.out.println("\n" + e.getLocalizedMessage());
            System.out.println("Jic jac Joe encountered an error while retrieving your saved game session.");
            BoardManager.MenuOptions menuOptionsInner = menuPrompt(scanner);
            sceneRouter(scanner, menuOptionsInner);
        } catch (Exception e) {
            System.out.println("\n" + e.getLocalizedMessage());
            BoardManager.MenuOptions menuOptionsInner = menuPrompt(scanner);
            sceneRouter(scanner, menuOptionsInner);
        }
    }

    /**
     * A function to load the players objects from given string characters and type
     * @param character The character string to create the player object from
     * @param type The type object for the player object
     * @return Returns a list of player objects
     */
    public static PlayerList<Player> loadCharacters(String character, Player.PlayerType type) {

        PlayerList<Player> players = new PlayerList<>();
        Player.PlayerType conditionalType = type.equals(Player.PlayerType.USER) ? Player.PlayerType.CPU : Player.PlayerType.USER;

        if (character.trim().equalsIgnoreCase("x")) {
            players.add(new PlayerOne(character, type));
            players.add(new PlayerTwo("O", conditionalType));
        } else {
            players.add(new PlayerOne("X", conditionalType));
            players.add(new PlayerTwo(character, type));
        }

        return players;
    }

    /**
     * A function to route the process, based on user selection
     * @param scanner The object used to collect the user's input
     * @param menuOptions The object to be used to help distributing code blocks for each process' route
     */
    public static void sceneRouter(Scanner scanner, @Nullable BoardManager.MenuOptions menuOptions) {
        if (menuOptions != null) {
            switch (menuOptions) {
                case START -> {
                    @Nullable Board.BoardModes mode = modePrompt(scanner);
                    @Nullable PlayerList<Player> players = characterPrompt(scanner);

                    if (mode != null && players != null && !players.isEmpty()) {
                        BoardController boardController = startGameSession(players, mode);
                        movePrompt(scanner, boardController);
                    } else {

                        System.out.println("\nJic jac Joe encountered an internal error.");
                        System.out.println("Please try starting a game session again...");

                        BoardManager.MenuOptions menuOptionsInner = menuPrompt(scanner);
                        sceneRouter(scanner, menuOptionsInner);
                    }
                }

                case LOAD -> loadSession(scanner);

                case CREDITS -> {
                    displayCredits();
                    BoardManager.MenuOptions menuOptionsInner = menuPrompt(scanner);
                    sceneRouter(scanner, menuOptionsInner);
                }

                case EXIT -> System.exit(0);
            }
        }
    }

    /**
     * A function that serves as an execution entry point for the game
     */
    public static void start() {

        displayWelcome();

        // Initialize a Scanner for gathering input from user
        Scanner scanner = new Scanner(System.in);

        BoardManager.MenuOptions menuOptions = menuPrompt(scanner);
        sceneRouter(scanner, menuOptions);

        scanner.close();
    }

    /**
     * A function for restarting the game
     * @param scanner The object used to collect the user's input
     */
    public static void restart(Scanner scanner) {
        BoardManager.MenuOptions menuOptions = menuPrompt(scanner);
        sceneRouter(scanner, menuOptions);
    }

    /**
     * A function for dumping the saved data after winning or loosing
     * @param boardController The controller containing the board object that tracks the session
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void dumpSessionStorage(BoardController boardController) {
        if (boardController.getBoard().isCurrentSessionFromLoad) {
            File datFile = new File("jicjacjoe.dat");
            datFile.delete();
        }
    }

}
