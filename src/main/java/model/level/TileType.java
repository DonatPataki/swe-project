package model.level;

import lombok.Getter;

/**
 * Helper enum class for {@link Level}.
 */
public enum TileType {
    /**
     * Tile types.
     */
    NOTHING(1), ASCEND(2), DESCEND(3);

    @Getter
    private final int value;

    /**
     * Constructs the enum with the given value.
     *
     * @param value value of enum
     */
    private TileType(int value) {
        this.value = value;
    }
}