import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, MainView.HEIGHT,  MainView.WIDTH);
        stage.setScene(scene);
        stage.show();

        mainView.draw();
    }

    public static void main(String[] args) {
        launch();
    }
}
