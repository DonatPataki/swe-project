package model.pathfinding;

import model.level.Level;
import model.util.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathfinderTest {

    @Test
    void testFindPath() {
        Level testLevel = new Level(5, 5);
        testLevel.setValueAtLocation(0, 0, 1);
        testLevel.setValueAtLocation(0, 1, 1);
        testLevel.setValueAtLocation(0, 2, 1);
        testLevel.setValueAtLocation(0, 3, 1);
        testLevel.setValueAtLocation(1, 3, 1);
        testLevel.setValueAtLocation(2, 0, 1);
        testLevel.setValueAtLocation(2, 1, 1);
        testLevel.setValueAtLocation(2, 2, 1);
        testLevel.setValueAtLocation(2, 3, 1);
        Pathfinder testPathfinder = new Pathfinder(testLevel);

        assertEquals( 7, testPathfinder.findPath(new Point(0, 0), new Point(2, 0)).size());
        assertEquals( new Node(null, new Point(0, 0)).position, testPathfinder.findPath(new Point(0, 0), new Point(2, 0)).get(0));

        testLevel.setValueAtLocation(0, 2, 0);
        assertEquals( 0, testPathfinder.findPath(new Point(0, 0), new Point(2, 0)).size());
    }
}