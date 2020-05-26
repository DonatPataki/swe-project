package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import model.level.Level;
import model.level.LevelGenerator;
import model.player.Player;
import model.save.JsonWriter;
import model.util.Point;
import model.pathfinding.Node;
import model.pathfinding.Pathfinder;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainView extends VBox {

    public static final int HEIGHT = 800;
    public static final int WIDTH = 800;
    public static final int ROWS = 25;
    public static final int COLLUMS = 25;
    public static final int VERTICAL = HEIGHT / ROWS;
    public static final int HORIZONTAL = WIDTH / COLLUMS;

    private Canvas canvas;

    private Affine affine;

    private Player player;

    private Level level;

    private List<Node> path = new ArrayList<>();

    public MainView() throws NoSuchFieldError {
        this.canvas = new Canvas(HEIGHT,WIDTH);
        this.canvas.setOnMousePressed(this::onMousePressed);

        this.getChildren().addAll(this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(HEIGHT / ROWS, WIDTH / COLLUMS);
        player = Player.getInstance();
        this.level = new LevelGenerator().generateLevel(COLLUMS, ROWS);
    }

    private void onMousePressed(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        Point position = new Point((int)mouseX / HORIZONTAL, (int)mouseY / VERTICAL);
        Logger.debug("Clicked at cell index: " + position.getX() + " " + position.getY());
        Pathfinder pathfinder = new Pathfinder(level.getLayout());
//        if (pathfinder.findPath(model.player.Player.getIn1stance().getPosition(), position) != null)
//            model.player.Player.getInstance().setPosition(position);
        this.path = pathfinder.findPath(player.getPosition(), position);
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
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLLUMS; x++) {
                if (layout[y][x] == 0) {
                    graphicsContext.fillRect(x, y, 1, 1);
                }
            }
        }

        graphicsContext.setFill((Color.BLUE));
        for (int i = 0; i < this.path.size(); i++) {
            graphicsContext.fillRect(this.path.get(i).position.getX(), this.path.get(i).position.getY(), 1, 1);
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
