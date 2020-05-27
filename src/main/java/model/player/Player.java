package model.player;

import lombok.Data;
import model.util.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Singelton class which represents the player in the game.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Player {

    private static Player instance = null;

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
