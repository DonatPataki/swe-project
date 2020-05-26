package model.level;

import model.util.Point;
import model.util.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class which generates a randomised level using tunneling algorithm.
 */
public class LevelGenerator {

    private Random random = new Random();
    private Level level;

    /**
     * Creates a random {@link Level}.
     *
     * @param levelWidth the width of the {@link Level}
     * @param levelHeight the height of the {@link Level}
     * @return the generated {@link Level}
     */
    public Level generateLevel(int levelWidth, int levelHeight) {
        final int ROOM_MAX_SIZE = 8;
        final int ROOM_MIN_SIZE = 4;
        final int MAX_ROOMS = 10;

        level = new Level(levelWidth, levelHeight);
        int numRooms = 0;
        List<Rectangle> rooms = new ArrayList<>();

        for (int i = 0; i < MAX_ROOMS + 1; i++) {
            int w = random.nextInt((ROOM_MAX_SIZE - ROOM_MIN_SIZE) + 1) + ROOM_MIN_SIZE;
            int h = random.nextInt((ROOM_MAX_SIZE - ROOM_MIN_SIZE) + 1) + ROOM_MIN_SIZE;
            int x = random.nextInt(levelWidth - w);
            int y = random.nextInt(levelHeight - h);

            Rectangle newRoom = new Rectangle(x, y, w, h);

            boolean overlapping = false;
            for (Rectangle room : rooms) {
                if (newRoom.intersect(room)) {
                    overlapping = true;
                    break;
                }
            }

            if (!overlapping) {
                createRoom(newRoom);
                Point newCenter = newRoom.center();

                if (numRooms != 0) {
                    Point prevCenter = rooms.get(numRooms - 1).center();

                    if (random.nextBoolean()) {
                        createHorTunnel(prevCenter, newCenter);
                        createVerTunnel(newCenter, prevCenter);
                    } else {
                        createVerTunnel(prevCenter, newCenter);
                        createHorTunnel(newCenter, prevCenter);
                    }
                }

                rooms.add(newRoom);
                numRooms++;
            }
        }

        return level;
    }

    /**
     * Fill the {@link Rectangle} thus creating a walkable room.
     *
     * @param room the {@link Rectangle} we want to fill
     */
    private void createRoom(Rectangle room) {
        for (int y = room.getLowerLeftCoord().getY() + 1; y < room.getUpperRightCoord().getY(); y++) {
            for (int x = room.getLowerLeftCoord().getX() + 1; x < room.getUpperRightCoord().getX(); x++) {
                level.setValueAtLocation(x, y, 1);
            }
        }
    }

    /**
     * Creates a horizontal tunnel from a {@link Rectangle}'s center to another's center.
     *
     * @param from starting room of the tunnel
     * @param toward where we want to bore
     */
    private void createHorTunnel(Point from, Point toward) {
        for (int x = Math.min(from.getX(), toward.getX()); x < Math.max(from.getX(), toward.getX()) + 1; x++) {
            level.setValueAtLocation(x, from.getY(), 1);
        }
    }

    /**
     * Creates a vertical tunnel from a {@link Rectangle}'s center to another's center.
     * @param from starting room of the tunnel
     * @param toward where we want to bore
     */
    private void createVerTunnel(Point from, Point toward) {
        for (int y = Math.min(from.getY(), toward.getY()); y < Math.max(from.getY(), toward.getY()) + 1; y++) {
            level.setValueAtLocation(from.getX(), y, 1);
        }
    }
}
