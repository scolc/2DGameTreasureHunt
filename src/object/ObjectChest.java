package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends SuperObject {

    public ObjectChest(GamePanel gp) {

        super(gp);

        this.setName("Chest");

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/chest.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
