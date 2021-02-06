package edu.bu.met.CS622.JicJacJoe.Player;

/**
 * The custom Player class for the players that are playing the game
 */
public class PlayerTwo extends Player {

    /**
     * Default constructor of the class based on it's parent
     * @param character The character chosen character of the player. Should be X or O
     * @param playerType The player type of the player. Should be USER or CPU
     */
    public PlayerTwo(String character, PlayerType playerType) {
        super(character, playerType);
    }

    /**
     * The default method for getting the characters based on this class's parent
     * @return The super call of the overrode method
     */
    @Override
    public String getCharacter() {
        return super.getCharacter();
    }

    /**
     * The method for getting the value for when the player should start the first move
     * @return Returns the character value returned in the `getCharacter` and compares it with X
     * The player that chooses X always start the first move.
     */
    @Override
    public boolean isSessionStarter() {
        return getCharacter().equals("X");
    }
}
