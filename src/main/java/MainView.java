import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class MainView extends VBox {

    public static final int HEIGHT = 640;
    public static final int WIDTH = 640;

    private Canvas canvas;

    private Affine affine;

    public MainView() {
        this.canvas = new Canvas( HEIGHT,WIDTH);

        this.getChildren().addAll(this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(HEIGHT / 20f, WIDTH / 20f);
    }

    public void draw() {
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.setTransform(this.affine);

        graphicsContext.setFill(Color.LIGHTGRAY);
        graphicsContext.fillRect(0, 0,  HEIGHT, WIDTH);

        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(0.05f);

        Level level = new LevelGenerator().generateLevel(20, 20);
        int[][] layout = level.getLayout();

        graphicsContext.setFill(Color.BLACK);
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (layout[y][x] == 0) {
                    graphicsContext.fillRect(x, y, 1, 1);
                }
            }
        }

        for (int x = 0; x < 21; x++) {
            graphicsContext.strokeLine(x, 0, x, 20);
        }

        for (int y = 0; y < 21; y++) {
            graphicsContext.strokeLine(0, y, 20, y);
        }
    }
}
