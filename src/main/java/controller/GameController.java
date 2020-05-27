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

public class GameController {

    @Getter
    private MainView mainView;

    private GameState gameState;

    public GameController(MainView mainView) {
        this.mainView = mainView;
        this.gameState = GameState.getInstance();
    }

    public void onMousePressed(MouseEvent event) {
        Point location = new Point((int)event.getX() / HORIZONTAL, (int)event.getY() / VERTICAL);
        Logger.debug("clicked at location: " + location.getX() + " " + location.getY());
        Pathfinder pathfinder = new Pathfinder(gameState.getLevel());

        List<Point> path = pathfinder.findPath(gameState.getPlayer().getPosition(), location);
        if (path.size() != 0)
                moveCharacter(path);
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.R) {
            Logger.debug("regenerate level");
            gameState.setLevel(new LevelGenerator().generateLevel(COLLUMS, ROWS));
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

    public void display() {
        mainView.draw(gameState.getLevel().getLayout(), gameState.getPlayer());
    }

    public void moveCharacter(List<Point> path) {
        gameState.getPlayer().setPosition(path.get(0));
        path.remove(0);
        display();
        if (!path.isEmpty())
            moveCharacter(path);
    }
}
