package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoor extends SuperObject {

    public ObjectDoor(GamePanel gp) {

        super(gp);

        this.setName("Door");
        this.setCollision(true);

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/door.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
