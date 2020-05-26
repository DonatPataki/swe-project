package model.util;

import model.level.Level;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testEquals() {
        assertEquals(new Point(1, 1), new Point(1, 1));
        assertNotEquals(new Point( -2, 2), new Point(2, 2));
        assertFalse(new Point(1, 1).equals(new Level(1, 1)));
    }
}