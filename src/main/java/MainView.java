import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class MainView extends VBox {

    public static final int HEIGHT = 320;
    public static final int WIDTH = 320;

    private Canvas canvas;

    private Affine affine;

    public MainView() {
        this.canvas = new Canvas( HEIGHT,WIDTH);

        this.getChildren().addAll(this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(HEIGHT / 10f, WIDTH / 10f);
    }

    public void draw() {
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.setTransform(this.affine);

        graphicsContext.setFill(Color.LIGHTGRAY);
        graphicsContext.fillRect(0, 0,  HEIGHT, WIDTH);

        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(0.05f);

        int[] layout = new Level().getLayout();

        graphicsContext.setFill(Color.BLACK);
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (layout[y * 10 + x] == 1) {
                    graphicsContext.fillRect(x, y, 1, 1);
                }
            }
        }

        for (int x = 0; x < 11; x++) {
            graphicsContext.strokeLine(x, 0, x, 10);
        }

        for (int y = 0; y < 11; y++) {
            graphicsContext.strokeLine(0, y, 10, y);
        }
    }
}
