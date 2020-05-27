package model.level;

/**
 * Helper enum class for {@link Level}
 */
public enum TileType {
    NOTHING(1), ASCEND(2), DESCEND(3);

    private final int value;

    /**
     * Constructs the enum with the given value.
     *
     * @param value value of enum
     */
    private TileType(int value) {
        this.value = value;
    }

    /**
     * Returns the value of the enum.
     *
     * @return value of enum
     */
    public int getValue() {
        return value;
    }
}