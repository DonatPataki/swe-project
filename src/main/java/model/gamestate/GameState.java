package model.gamestate;

import lombok.Getter;
import lombok.Setter;
import model.level.Level;
import model.level.LevelGenerator;
import model.level.TileType;
import model.player.Player;
import model.util.Point;
import org.tinylog.Logger;
import view.MainView;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds all the data relevant for the game.
 */
@Getter
@Setter
@XmlRootElement(name = "gamestate")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"player", "levels", "currentFloorNum"})
public class GameState {

    private static GameState instance = null;

    private Player player;
    @XmlElementWrapper(name = "levels")
    @XmlElement(name = "level")
    private List<Level> levels;
    private Integer currentFloorNum;

    /**
     * Constructor of {@cod GameState}
     */
    private GameState() {
        this.player = Player.getInstance();
        this.levels = new ArrayList<>();
        levels.add(new LevelGenerator().generateLevel(MainView.COLLUMS, MainView.ROWS));
        currentFloorNum = 0;
    }

    /**
     * Returns the instance of the game.
     *
     * @return {@code GameState} object
     */
    synchronized public static GameState getInstance() {
        if (instance == null)
            instance = new GameState();
        return instance;
    }

    /**
     * Execute character movement on the grid.
     *
     * @param path moves character to the first tile in the list and checks if the tile has any special effect
     */
    public void moveCharacter(List<Point> path) {
        player.setPosition(path.get(0));
        path.remove(0);
        TileType result = checkPosition();
        if (result != TileType.NOTHING) {
            path.clear();
        }
    }

    /**
     * Saves the current gamestate.
     */
    public void save() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GameState.class);
            Marshaller jaxbmarshaller = jaxbContext.createMarshaller();
            jaxbmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File file = new File(getClass().getClassLoader().getResource("save/save.xml").getFile());
            jaxbmarshaller.marshal(this, file);

        } catch (Exception e) {
            Logger.error(e);
        }
    }

    /**
     * Loads the last save.
     */
    public void load() {
        File xml = new File(getClass().getClassLoader().getResource("save/save.xml").getFile());

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(GameState.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Point temp = (((GameState) jaxbUnmarshaller.unmarshal(xml)).player.getPosition());
            levels.set(0, (((GameState) jaxbUnmarshaller.unmarshal(xml)).getLevels().get(0)));
            player.setPosition(temp);
        } catch (JAXBException e)
        {
            Logger.error(e);
        }
    }

    /**
     *
     * Checks tile under player.
     *
     * @return the type of tile under the player
     */
    private TileType checkPosition() {
        if (player.getPosition().equals(levels.get(currentFloorNum).getAscendPoint()))
            return TileType.ASCEND;
        if (player.getPosition().equals(levels.get(currentFloorNum).getDescentPoint()))
            return TileType.DESCEND;
        return TileType.NOTHING;
    }
}