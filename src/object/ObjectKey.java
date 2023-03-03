package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends SuperObject {

    public ObjectKey(GamePanel gp) {

        super(gp);

        this.setName("Key");

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/key.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
