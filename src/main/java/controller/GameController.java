package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import lombok.Getter;
import model.gamestate.GameState;
import model.level.LevelGenerator;
import model.pathfinding.Pathfinder;
import model.util.Point;
import org.tinylog.Logger;
import view.MainView;

import java.util.List;

import static view.MainView.*;

/**
 * Controller for the game.
 */
public class GameController {

    @Getter
    private MainView mainView;

    private GameState gameState;

    /**
     * Constructs {@code GameController}.
     *
     * @param mainView link to {@link MainView}.
     */
    public GameController(MainView mainView) {
        this.mainView = mainView;
        this.gameState = GameState.getInstance();
    }

    /**
     * Events  on mouse presssed.
     *
     * @param event mouseevent
     */
    public void onMousePressed(MouseEvent event) {
        Point location = new Point((int)event.getX() / HORIZONTAL, (int)event.getY() / VERTICAL);
        Logger.debug("clicked at location: " + location.getX() + " " + location.getY());
        Pathfinder pathfinder = new Pathfinder(gameState.getLevels().get(gameState.getCurrentFloorNum()));

        List<Point> path = pathfinder.findPath(gameState.getPlayer().getPosition(), location);
        if (path.size() != 0) {
            while (!path.isEmpty()) {
                gameState.moveCharacter(path);
                display();
            }
        }
    }

    /**
     * Events on key pressed.
     *
     * @param keyEvent keyevent
     */
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.R) {
            Logger.debug("regenerate level");
            gameState.getLevels().set(gameState.getCurrentFloorNum(), new LevelGenerator().generateLevel(COLLUMS, ROWS));
            display();
        }

        if (keyEvent.getCode() == KeyCode.F5) {
            Logger.debug("saving");
            gameState.save();
        }

        if (keyEvent.getCode() == KeyCode.F6) {
            Logger.debug("loading");
            gameState.load();
            display();
        }
    }

    /**
     * Starts draw.
     */
    public void display() {
        mainView.draw(gameState.getLevels().get(gameState.getCurrentFloorNum()), gameState.getPlayer());
    }
}
