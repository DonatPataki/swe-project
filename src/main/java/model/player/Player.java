package model.player;

import lombok.Getter;
import lombok.Setter;
import model.util.Point;

public class Player {

    private static Player instance = null;

    @Getter
    @Setter
    private Point position;

    private Player() {
        this.position = new Point((int)(Math.random() * 19), (int)(Math.random() * 19));
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }

        return instance;
    }
}
