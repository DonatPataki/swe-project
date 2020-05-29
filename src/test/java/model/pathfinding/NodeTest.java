package model.pathfinding;

import model.util.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testCompareTo() {
        Node nodeA = new Node(null, new Point(1, 1));
        nodeA.setG(10);
        nodeA.setH(13);
        Node nodeB = new Node(null, new Point(1, 1));
        nodeB.setG(5);
        nodeB.setH(8);
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