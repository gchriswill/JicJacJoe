package edu.bu.met.CS622.JicJacJoe.Player;

import java.util.ArrayList;

// Abstract class declaration
public abstract class Player {

    public enum PlayerType {USER, CPU}
    public enum PlayerKeys {ONE, TWO}

    public PlayerType playerType;
    private final String character;
    private final ArrayList<Integer> locations;

    public Player(String character, PlayerType playerType) {
        this.character = character;
        this.playerType = playerType;
        this.locations = new ArrayList<>();
    }

    public String getCharacter() {
        return character;
    }

    public boolean isSessionStarter() {
        return false;
    }

    public ArrayList<Integer> getLocations() {
        return locations;
    }

    public void addLocation(Integer location) {
        locations.add(location);
    }

    public Integer getCurrentLocation() {

        if (locations.isEmpty()) {
            return -1;
        }

        return locations.get(locations.size() - 1);
    }
}
