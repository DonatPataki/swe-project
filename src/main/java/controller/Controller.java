package controller;

import javafx.scene.input.MouseEvent;
import model.player.Player;
import model.util.Point;
import org.tinylog.Logger;

public class Controller {

    public static void onMousePressed(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        Point position = new Point((int)mouseX / 32, (int)mouseY / 32);
        Logger.debug("Clicked at cell index: " + position.getX() + " " + position.getY());
        Player.getInstance().setPosition(position);

    }
}
