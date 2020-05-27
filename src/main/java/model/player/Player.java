package model.player;

import lombok.Getter;
import lombok.Setter;
import model.util.Point;

/**
 * Singelton class which represents the player in the game.
 */
public class Player {

    private static Player instance = null;

    @Getter
    @Setter
    private Point position;

    /**
     * Constructs {@code Player}.
     */
    private Player() {
    }

    /**
     * Returns the instance of the {@code Player} if exists. If not constructs then returns it.
     *
     * @return the instance of the {@code Player}
     */
    synchronized public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }

        return instance;
    }
}
