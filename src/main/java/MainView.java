import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import org.tinylog.Logger;

public class MainView extends VBox {

    public static final int HEIGHT = 640;
    public static final int WIDTH = 640;

    private Canvas canvas;

    private Affine affine;

    private Player player;

    private Level level;

    public MainView() {
        this.canvas = new Canvas(HEIGHT,WIDTH);
        this.canvas.setOnMousePressed(this::onMousePressed);

        this.getChildren().addAll(this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(HEIGHT / 20f, WIDTH / 20f);
        player = Player.getInstance();
        this.level = new LevelGenerator().generateLevel(20, 20);
    }

    private void onMousePressed(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        Point position = new Point((int)mouseX / 32, (int)mouseY / 32);
        Logger.debug("Clicked at cell index: " + position.getX() + " " + position.getY());
        Player.getInstance().setPosition(position);
        draw();
    }

    public void draw() {
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.setTransform(this.affine);

        graphicsContext.setFill(Color.LIGHTGRAY);
        graphicsContext.fillRect(0, 0,  HEIGHT, WIDTH);

        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(0.05f);

        int[][] layout = level.getLayout();

        graphicsContext.setFill(Color.BLACK);
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (layout[y][x] == 0) {
                    graphicsContext.fillRect(x, y, 1, 1);
                }
            }
        }

        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillRect(player.getPosition().getX(), player.getPosition().getY(), 1, 1);

        for (int x = 0; x < 21; x++) {
            graphicsContext.strokeLine(x, 0, x, 20);
        }

        for (int y = 0; y < 21; y++) {
            graphicsContext.strokeLine(0, y, 20, y);
        }
    }
}
