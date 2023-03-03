package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public UtilityTool(){

    }

    public BufferedImage scaleImage(BufferedImage original, int width, int height) {

        BufferedImage image = new BufferedImage(width, height, original.getType());
        Graphics2D g2D = image.createGraphics();
        g2D.drawImage(original, 0, 0, width, height, null);
        g2D.dispose();

        return image;
    }
}
