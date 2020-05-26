package model.level;

import model.util.Point;
import model.util.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelGenerator {

    private Random random = new Random();
    private Level level;

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

    private void createRoom(Rectangle room) {
        for (int y = room.getLowerLeftCoord().getY() + 1; y < room.getUpperRightCoord().getY(); y++) {
            for (int x = room.getLowerLeftCoord().getX() + 1; x < room.getUpperRightCoord().getX(); x++) {
                level.setValueAtLocation(x, y, 1);
            }
        }
    }

    private void createHorTunnel(Point from, Point toward) {
        for (int x = Math.min(from.getX(), toward.getX()); x < Math.max(from.getX(), toward.getX()) + 1; x++) {
            level.setValueAtLocation(x, from.getY(), 1);
        }
    }

    private void createVerTunnel(Point from, Point toward) {
        for (int y = Math.min(from.getY(), toward.getY()); y < Math.max(from.getY(), toward.getY()) + 1; y++) {
            level.setValueAtLocation(from.getX(), y, 1);
        }
    }
}
