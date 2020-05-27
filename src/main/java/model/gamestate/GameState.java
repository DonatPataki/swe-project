package model.gamestate;

import lombok.Getter;
import lombok.Setter;
import model.level.Level;
import model.level.LevelGenerator;
import model.player.Player;
import view.MainView;

@Getter
@Setter
public class GameState {

    private static GameState instance = null;

    private Player player;
    private Level level;

    private GameState() {
        this.player = Player.getInstance();
        this.level = new LevelGenerator().generateLevel(MainView.COLLUMS, MainView.ROWS);
    }

    synchronized public static GameState getInstance() {
        if (instance == null)
            instance = new GameState();
        return instance;
    }
}
