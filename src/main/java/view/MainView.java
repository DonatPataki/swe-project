package view;

import controller.GameController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import model.level.Level;
import model.player.Player;

/**
 * View for the game.
 */
public class MainView extends VBox {

    /**
     * Window height.
     */
    public static final int HEIGHT = 800;
    /**
     * Window width.
     */
    public static final int WIDTH = 800;
    /**
     * Number of rows.
     */
    public static final int ROWS = 25;
    /**
     * Number of collums.
     */
    public static final int COLLUMS = 25;
    /**
     * Height of drawn block.
     */
    public static final int VERTICAL = HEIGHT / ROWS;
    /**
     * Width of drawn block.
     */
    public static final int HORIZONTAL = WIDTH / COLLUMS;

    private Canvas canvas;

    private Affine affine;

    /**
     * Constructs {@code MainView}.
     */
    public MainView() {
        GameController gameController = new GameController(this);
        this.canvas = new Canvas(HEIGHT,WIDTH);
        this.canvas.setOnMousePressed(gameController::onMousePressed);
        this.canvas.setOnKeyPressed(gameController::onKeyPressed);
        this.canvas.setFocusTraversable(true);

        this.getChildren().addAll(this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(HEIGHT / (float)ROWS, WIDTH / (float)COLLUMS);
    }

    /**
     * Draws game current state.
     * @param currentLevel level to be drawn
     * @param player player to be drawn
     */
    public void draw(Level currentLevel, Player player) {
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.setTransform(this.affine);

        graphicsContext.setFill(Color.LIGHTGRAY);
        graphicsContext.fillRect(0, 0,  HEIGHT, WIDTH);

        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(0.05f);

        int[][] layout = currentLevel.getLayout();
        graphicsContext.setFill(Color.BLACK);
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLLUMS; x++) {
                if (layout[y][x] == 0) {
                    graphicsContext.fillRect(x, y, 1, 1);
                }
            }
        }

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(currentLevel.getAscendPoint().getX(), currentLevel.getAscendPoint().getY(), 1, 1);

        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(currentLevel.getDescentPoint().getX(), currentLevel.getDescentPoint().getY(), 1, 1);

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
