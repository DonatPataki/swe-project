import lombok.Getter;

public class Level {

    @Getter
    private int[] layout = new int[100];

    public Level() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (x > y) {
                    this.layout[y * 10 + x] = 0;
                } else {
                    this.layout[y * 10 + x] = 1;
                }
            }
        }
    }

}
