package model.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void testCenter() {
        assertEquals(new Point(4, 4), new Rectangle(0, 0, 8, 8).center());
        assertEquals(new Point(1, -11), new Rectangle(5, -7, -8, -8).center());
    }

    @Test
    void testIntersect() {
        assertFalse(new Rectangle(-50, -50, 1, 1).intersect(new Rectangle(50, 50, 1, 1)));
        assertTrue(new Rectangle(0, 0, 10, 10).intersect(new Rectangle(2, 2, 2, 2)));
        assertTrue(new Rectangle(0, 0, 10, 10).intersect(new Rectangle(2, 2, 10, 10)));
        assertTrue(new Rectangle(0, 0, 10, 10).intersect(new Rectangle(-2, 2, 10, 10)));
        assertTrue(new Rectangle(0, 0, 10, 10).intersect(new Rectangle(-2, -2, 10, 10)));
        assertTrue(new Rectangle(0, 0, 10, 10).intersect(new Rectangle(2, -2, 10, 10)));
    }
}