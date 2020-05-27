package view;

import controller.GameController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import model.player.Player;

public class MainView extends VBox {

    public static final int HEIGHT = 800;
    public static final int WIDTH = 800;
    public static final int ROWS = 25;
    public static final int COLLUMS = 25;
    public static final int VERTICAL = HEIGHT / ROWS;
    public static final int HORIZONTAL = WIDTH / COLLUMS;

    private Canvas canvas;

    private Affine affine;

    GameController gameController;

    public MainView() throws NoSuchFieldError {
        gameController = new GameController(this);
        this.canvas = new Canvas(HEIGHT,WIDTH);
        this.canvas.setOnMousePressed(gameController::onMousePressed);
        this.canvas.setOnKeyPressed(gameController::onKeyPressed);
        this.canvas.setFocusTraversable(true);

        this.getChildren().addAll(this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(HEIGHT / (float)ROWS, WIDTH / (float)COLLUMS);
    }

    public void draw(int[][] layout, Player player) {
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.setTransform(this.affine);

        graphicsContext.setFill(Color.LIGHTGRAY);
        graphicsContext.fillRect(0, 0,  HEIGHT, WIDTH);

        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(0.05f);

        graphicsContext.setFill(Color.BLACK);
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLLUMS; x++) {
                if (layout[y][x] == 0) {
                    graphicsContext.fillRect(x, y, 1, 1);
                }
            }
        }

        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillRect(player.getPosition().getX(), player.getPosition().getY(), 1, 1);

        for (int x = 0; x < COLLUMS + 1; x++) {
            graphicsContext.strokeLine(x, 0, x, COLLUMS);
        }

        for (int y = 0; y < ROWS + 1; y++) {
            graphicsContext.strokeLine(0, y, ROWS, y);
        }
    }
}
