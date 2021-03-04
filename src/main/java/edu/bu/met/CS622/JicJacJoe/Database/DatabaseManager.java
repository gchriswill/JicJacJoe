package edu.bu.met.CS622.JicJacJoe.Database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.bu.met.CS622.JicJacJoe.Board.Board;
import edu.bu.met.CS622.JicJacJoe.Player.Player;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for organizing and managing SQL transactions
 *
 */

@SuppressWarnings("FieldCanBeLocal")
public final class DatabaseManager {

    /**
     * An inner model class for organizing the winning Player values
     *
     */
    public static class DBWinner {

        public int id;
        public String type;
        public int maxTurns;
        public String character;

        public DBWinner(int id, String type, int maxTurns, String character) {
            this.id = id;
            this.type = type;
            this.maxTurns = maxTurns;
            this.character = character;
        }
    }

    /**
     * An inner model class for organizing the winning session values
     *
     */
    public static class DBSession {

        public int id;
        public String json;
        public DBWinner winner;
        public int winningTimestamp;

        public DBSession(int id, String json, DBWinner winner, int winningTimestamp) {
            this.id = id;
            this.json = json;
            this.winner = winner;
            this.winningTimestamp = winningTimestamp;
        }
    }

    // The database connection URL
    private final String dbUrl = "jdbc:sqlite:jicjacjoe.sqlite";

    // The connection object to use for connecting to the database
    private Connection connection;

    // Public Constructor
    public DatabaseManager() {}

    /**
     *  A function for collection the winning session and player values into model objects
     *
     * @param board The board object of the game
     * @param currentPlayer The current winning Player object
     * @return DBSession object with all the collected values
     */
    @SuppressWarnings("rawtypes")
    public static DBSession DBSession(Board board, Player currentPlayer) {

        int idTimestamp = (int) -System.currentTimeMillis();

        Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap>(){}.getType();

        // TODO: Needs refactoring and abstraction to new BoardController.getSessionJson() function
        Map<String, String> sessionMap = new HashMap<>() {{
            put("savedMode", board.getBoardMode().toString());
            put("savedTurn", board.playerTurn.toString());
            put("savedType", currentPlayer.playerType.toString());
            put("savedCharacter", currentPlayer.getCharacter());
            put("boardData", board.getBoardJson());
        }};

        String sessionString = gson.toJson(sessionMap, gsonType);

        DBWinner winner = new DBWinner(idTimestamp,
                currentPlayer.playerType.toString(),
                currentPlayer.getLocations().size(),
                currentPlayer.getCharacter());

        return new DBSession(idTimestamp, sessionString, winner, idTimestamp);
    }

    /**
     * A function for connecting to the database
     *
     * @throws SQLException if the connection fails for any reason
     */
    public void connect() throws SQLException {

        if (connection == null) {

            this.connection = DriverManager.getConnection(dbUrl);

        } else {

            if (connection.isClosed()) {
                connection = null;
                this.connection = DriverManager.getConnection(dbUrl);
            }
        }
    }

    /**
     * A function for creating the database tables required for storing the game sessions
     *
     * @throws SQLException if the tables creation fails for any reason
     */
    public void createsInitialSessionTables() throws SQLException {

        connect();

        String createsTableWinnerQuery = """
                create table if not exists winners
                (
                    winner_id INTEGER not null
                        primary key,
                    winner_character TEXT not null,
                    winner_type TEXT not null,
                    winner_max_turns INTEGER not null
                );
                """;

        String createsTableSessionQuery = """
                create table if not exists sessions
                (
                    session_json TEXT not null,
                    session_id INTEGER not null
                        primary key,
                    session_winning_timestamp INTEGER not null
                        unique,
                    session_winner INTEGER not null
                        references winners
                            deferrable initially deferred
                );
                """;

        connection.createStatement().execute(createsTableWinnerQuery);
        connection.createStatement().execute(createsTableSessionQuery);
        connection.close();
    }

    /**
     * A function for inserting data into the game's database tables
     *
     * @param dbSession DBSession The object to use for the data extraction into the database
     * @throws SQLException if the table insertion fails for any reason
     */
    @SuppressWarnings("DuplicatedCode")
    public void insert(DBSession dbSession) throws SQLException {

        connect();

        String winnersInsertion = """
                insert into winners (winner_id, winner_type, winner_character, winner_max_turns) values (?, ?, ?, ?);
                """;

        String sessionsInsertion = """
                insert into sessions (session_id, session_winning_timestamp, session_json, session_winner) values (?, ?, ?, ?);
                """;

        PreparedStatement preparedWinners = connection.prepareStatement(winnersInsertion);
        preparedWinners.setInt(1, dbSession.winner.id);
        preparedWinners.setString(2, dbSession.winner.type);
        preparedWinners.setString(3, dbSession.winner.character);
        preparedWinners.setInt(4, dbSession.winner.maxTurns);

        preparedWinners.executeUpdate();

        PreparedStatement preparedSessions = connection.prepareStatement(sessionsInsertion);
        preparedSessions.setInt(1, dbSession.id);
        preparedSessions.setInt(2, dbSession.winningTimestamp);
        preparedSessions.setString(3, dbSession.json);
        preparedSessions.setInt(4, dbSession.winner.id);

        preparedSessions.executeUpdate();
        connection.close();
    }

    /**
     * A function for retrieving historical winners' data for the database
     *
     * @return ArrayList with DBSession type
     * @throws SQLException if the table query fails for any reason
     */
    public ArrayList<DBSession> queryWinners() throws SQLException {

        connect();

        ArrayList<DBSession> dbSessions = new ArrayList<>();

        String winnersQuery = """
                select * from winners join sessions s on winners.winner_id = s.session_winner ORDER BY winner_id DESC;
                """;

        ResultSet winnersResultSet = connection.createStatement().executeQuery(winnersQuery);

        while (winnersResultSet.next()) {

            DBWinner dbwinner = new DBWinner(winnersResultSet.getInt("winner_id"),
                    winnersResultSet.getString("winner_type"),
                    winnersResultSet.getInt("winner_max_turns"),
                    winnersResultSet.getString("winner_character"));

            DBSession dbSession = new DBSession(winnersResultSet.getInt("session_id"),
                    winnersResultSet.getString("session_json"),
                    dbwinner,
                    winnersResultSet.getInt("session_winning_timestamp"));

            dbSessions.add(dbSession);
        }

        winnersResultSet.close();
        connection.close();

        return dbSessions;
    }

    /**
     * A function for retrieving the most recent winner's data for the database
     *
     * @return String with data values appended
     * @throws SQLException if the table query fails for any reason
     */
    public String queryMostRecentWinner() throws SQLException {

        connect();

        String mostRecentWinner = "";

        String mostRecentSessionQuery = """
                select min(winner_id), winner_character, winner_type, winner_max_turns from winners;
                """;

        ResultSet mostRecentResultSet = connection.createStatement().executeQuery(mostRecentSessionQuery);

        while (mostRecentResultSet.next()) {
            if (mostRecentWinner.isBlank()) {
                mostRecentWinner = "Character: " + mostRecentResultSet.getString("winner_character") +
                        ", Type: " + mostRecentResultSet.getString("winner_type") +
                        ", Moves: " + mostRecentResultSet.getInt("winner_max_turns");
            }
        }

        mostRecentResultSet.close();
        connection.close();

        return mostRecentWinner;
    }
}
