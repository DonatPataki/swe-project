package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gamestate.GameState;
import view.MainView;

/**
 * Javafx entry point.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, MainView.HEIGHT,  MainView.WIDTH);
        stage.setScene(scene);
        stage.show();

        GameState gameState = GameState.getInstance();
        mainView.draw(gameState.getLevels().get(gameState.getCurrentFloorNum()), gameState.getPlayer());
    }
}
