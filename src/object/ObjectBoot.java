package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectBoot extends SuperObject {

    public ObjectBoot(GamePanel gp) {

        super(gp);

        this.setName("Boot");

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/boot.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
