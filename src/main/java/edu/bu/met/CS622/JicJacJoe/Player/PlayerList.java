package edu.bu.met.CS622.JicJacJoe.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * A custom generic class that inherits from ArrayList
 * This class is intended to maintain a collection of all types of players that inherits from Player class.
 * @param <P> The type of the items in the collection
 */
public class PlayerList<P extends Player> extends ArrayList<P> {

    /**
     * The function to get the current player by passing Player Key
     * @param key The key to access the current player
     * @return Returns the current player object
     */
    public @Nullable P getPlayerByKey (Player.PlayerKeys key) {

        switch (key) {
            case ONE -> { return super.get(0); }
            case TWO -> { return super.get(1); }
        }

        return null;
    }
}
