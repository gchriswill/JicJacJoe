package edu.bu.met.CS622.JicJacJoe.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class for organizing and managing SQL transactions
 *
 */

@SuppressWarnings("FieldCanBeLocal")
public final class DatabaseManager {

    private final String dbUrl = "jdbc:sqlite:jicjacjoe.sqlite";
    private Connection connection;

    // Restricted constructor
    public DatabaseManager() throws SQLException {
        this.connect();
    }

    public void connect() throws SQLException {

        if (connection == null) {
            this.connection = DriverManager.getConnection(dbUrl);
        } else {

            if (!connection.isClosed()) {

                connection.close();
                this.connection = DriverManager.getConnection(dbUrl);
            }
        }
    }

    public void createsInitialSessionTables() throws SQLException {

        String createsTableWinnerQuery = """
                create table session_winners
                (
                    winner_id INTEGER not null
                        primary key autoincrement,
                    winner_character TEXT not null,
                    winner_type TEXT not null,
                    winner_max_turns INTEGER not null
                );
                """;

        String createsTableSessionQuery = """
                create table top_ten_sessions
                (
                    session_json TEXT not null,
                    session_id INTEGER not null
                        primary key autoincrement,
                    session_won_timestamp INTEGER not null
                        unique,
                    session_winner INTEGER not null
                        references session_winners
                            deferrable initially deferred
                );
                """;

        connection.createStatement().execute(createsTableWinnerQuery);
        connection.createStatement().execute(createsTableSessionQuery);
        connection.close();
    }
}
