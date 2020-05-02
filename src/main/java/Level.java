import lombok.Getter;

public class Level {

    @Getter
    private int[][] layout = new int[20][20];

    public Level() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                layout[y][x] = 0;
            }
        }
    }

    public void setLayout(int x, int y, int value) {
        this.layout[y][x] = value;
    }

}
