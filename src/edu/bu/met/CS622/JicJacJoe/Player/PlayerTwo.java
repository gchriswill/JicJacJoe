package edu.bu.met.CS622.JicJacJoe.Player;

// Class inheritance
public class PlayerTwo extends Player {

    public PlayerTwo(String character, PlayerType playerType) {
        super(character, playerType);
    }

    @Override
    public String getCharacter() {
        return super.getCharacter();
    }

    // Polymorphism implementation
    @Override
    public boolean isSessionStarter() {
        return getCharacter().equals("X");
    }
}
