package model.pathfinding;

import model.util.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testCompareTo() {
        Node nodeA = new Node(null, new Point(1, 1));
        nodeA.g = 10;
        nodeA.h = 13;
        Node nodeB = new Node(null, new Point(1, 1));
        nodeB.g = 5;
        nodeB.h = 8;
        assertEquals( 10, nodeA.compareTo(nodeB));
    }

    @Test
    void testEquals() {
        assertEquals(new Node(null, new Point(1, 1)), new Node(null, new Point(1, 1)));
        assertEquals(new Node(null, new Point(-1, 1)), new Node(null, new Point(-1, 1)));
        assertEquals(new Node(null, new Point(1, -1)), new Node(null, new Point(1, -1)));
        assertEquals(new Node(null, new Point(-1, -1)), new Node(null, new Point(-1, -1)));
        assertNotEquals(new Node(null, new Point(-1, 1)), new Node(null, new Point(1, 1)));
        assertNotEquals(new Node(null, new Point(1, -1)), new Node(null, new Point(1, 1)));
        assertNotEquals(new Node(null, new Point(1, 1)), new Node(null, new Point(-1, 1)));
        assertNotEquals(new Node(null, new Point(1, 1)), new Node(null, new Point(1, -1)));
    }
}