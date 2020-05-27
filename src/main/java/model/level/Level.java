package model.level;

import lombok.Data;
import model.util.Point;

import javax.xml.bind.annotation.*;

/**
 * Represents a level in the game.
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@Data
public class Level {

    private int[][] layout;
    private Point ascendPoint;
    private Point descentPoint;

    /**
     * Constructs a {@code Level} with the given number of rows and collums.
     *
     * @param colls number of collums or width
     * @param rows number of rows or height
     */
    public Level(int colls, int rows) {
        this.layout = new int[rows][colls];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < colls; x++) {
                layout[y][x] = 0;
            }
        }
    }

    /**
     * Default constructor
     */
    public Level() {}

    /**
     * Sets the value at a specific location.
     *
     * @param x x coordinate of the level
     * @param y y coordinate of the level
     * @param value the value we want to be set
     */
    public void setValueAtLocation(int x, int y, int value) {
        this.layout[y][x] = value;
    }

}
