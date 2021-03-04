package edu.bu.met.CS622.JicJacJoe.Player;

import java.util.ArrayList;

// Abstract class declaration

/**
 * The base class for the player object
 */
@SuppressWarnings({"CommentedOutCode", "MismatchedQueryAndUpdateOfCollection"})
public abstract class Player {

    // The string character of the player.
    // This character should be X or O
    private final String character;
    // The list of all the locations the player has chosen over the lifecycle of the game session
    private final ArrayList<Integer> locations;
    // To hold the value of enum for identifying the type
    public PlayerType playerType;

    // The constructor of the Player class.
    // Requires 2 default parameters
    // Initializes the locations arraylist
    public Player(String character, PlayerType playerType) {
        this.character = character;
        this.playerType = playerType;
        this.locations = new ArrayList<>();
    }

    // Getter for the character of the player
    public String getCharacter() {
        return character;
    }

    public boolean isSessionStarter() {
        return false;
    }

    public void addLocation(Integer location) {
        locations.add(location);
    }

    // Enum for identifying the player type
    public enum PlayerType {USER, CPU}

    /**
     * A getter function for getting all the player's board captured locations
     * @return ArrayList of integers representing the captured locations
     */
    public ArrayList<Integer> getLocations() {
        return locations;
    }

    // Enum for keys fo the players
    // This enum is used for the keys of a Map
    public enum PlayerKeys {ONE, TWO}

    // // This will be used in future module or removed if not necessary
//    public Integer getCurrentLocation() {
//
//        if (locations.isEmpty()) {
//            return -1;
//        }
//
//        return locations.get(locations.size() - 1);
//    }
}
