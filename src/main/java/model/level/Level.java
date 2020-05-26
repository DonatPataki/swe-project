package model.level;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Level {

    private int[][] layout;

    public Level(int colls, int rows) {
        this.layout = new int[rows][colls];

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                layout[y][x] = 0;
            }
        }
    }

    public void setValueAtLocation(int x, int y, int value) {
        this.layout[y][x] = value;
    }

}
