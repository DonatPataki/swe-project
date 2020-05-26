package model.level;

import model.util.Point;
import model.util.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelGenerator {

    private Random random = new Random();
    private Level level;
    private final int ROOM_MAX_SIZE = 6;
    private final int ROOM_MIN_SIZE = 4;
    private final int MAX_ROOMS = 20;

    public Level generateLevel(int levelWidth, int levelHeight) {

        level = new Level(levelWidth, levelHeight);
        int numRooms = 0;
        List<Rectangle> rooms = new ArrayList<>();

        for (int i = 0; i < MAX_ROOMS + 1; i++) {
            int w = random.nextInt((ROOM_MAX_SIZE - ROOM_MIN_SIZE) + 1) + ROOM_MIN_SIZE;
            int h = random.nextInt((ROOM_MAX_SIZE - ROOM_MIN_SIZE) + 1) + ROOM_MIN_SIZE;
            int x = random.nextInt(20 - w);
            int y = random.nextInt(20 - h);

            Rectangle newRoom = new Rectangle(x, y, w, h);

            boolean overlapping = false;
            for (int j = 0; j < rooms.size(); j++) {
                if (newRoom.intersect(rooms.get(j))) {
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
                        createVerTunnel(prevCenter, newCenter);
                    } else {
                        createVerTunnel(prevCenter, newCenter);
                        createHorTunnel(prevCenter, newCenter);
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

    private void createHorTunnel(Point previous, Point current) {
        for (int x = Math.min(previous.getX(), current.getX()); x < Math.max(previous.getX(), current.getX()) + 1; x++) {
            level.setValueAtLocation(x, previous.getY(), 1);
        }
    }

    private void createVerTunnel(Point previous, Point current) {
        for (int y = Math.min(previous.getY(), current.getY()); y < Math.max(previous.getY(), current.getY()) + 1; y++) {
            level.setValueAtLocation(previous.getX(), y, 1);
        }
    }
}
