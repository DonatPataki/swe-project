package model.gamestate;

import lombok.Getter;
import lombok.Setter;
import model.level.Level;
import model.level.LevelGenerator;
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

@Getter
@Setter
@XmlRootElement(name = "gamestate")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"player", "level"})
public class GameState {

    private static GameState instance = null;

    private Player player;
    private Level level;

    private GameState() {
        this.player = Player.getInstance();
        this.level = new LevelGenerator().generateLevel(MainView.COLLUMS, MainView.ROWS);
    }

    synchronized public static GameState getInstance() {
        if (instance == null)
            instance = new GameState();
        return instance;
    }

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

    public void load() {
        File xml = new File(getClass().getClassLoader().getResource("save/save.xml").getFile());

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(GameState.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Point temp = ((GameState) jaxbUnmarshaller.unmarshal(xml)).player.getPosition();
            int[][] layout = ((GameState) jaxbUnmarshaller.unmarshal(xml)).level.getLayout();
            player.setPosition(temp);
            level.setLayout(layout);
        } catch (JAXBException e)
        {
            Logger.error(e);
        }
    }
}
