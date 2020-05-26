package model.pathfinding;

import model.level.Level;
import model.util.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathfinderTest {

    @Test
    void testFindPath() {
        Level testLevel = new Level(5, 5);
        testLevel.setValueAtLocation(1, 0, 1);
        testLevel.setValueAtLocation(1, 1, 1);
        testLevel.setValueAtLocation(1, 2, 1);

        Pathfinder testPathfinder = new Pathfinder(testLevel);

        assertEquals( 7, testPathfinder.findPath(new Point(0, 0), new Point(2, 0)).size());
        assertEquals( new Node(null, new Point(0, 0)).position, testPathfinder.findPath(new Point(0, 0), new Point(2, 0)).get(0).position);

        testLevel.setValueAtLocation(0, 2, 1);
        assertEquals( 0, testPathfinder.findPath(new Point(0, 0), new Point(2, 0)).size());
    }
}