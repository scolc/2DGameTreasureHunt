package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    private boolean messageOn;
    private String message;
    int messageCounter;
    private boolean gameFinished;
    private double playTime;
    private int playTimeMinutes;
    private DecimalFormat dFormatSeconds, dFormatMinutes;

    public UI(GamePanel gp) {

        this.gp = gp;
        this.arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.arial_80B = new Font("Arial", Font.BOLD, 80);
        this.messageOn = false;
        this.message = "";
        this.messageCounter = 0;
        this.gameFinished = false;
        this.playTime = 0.0;
        this.playTimeMinutes = 0;
        this.dFormatSeconds = new DecimalFormat("00.00");
        this.dFormatMinutes = new DecimalFormat("00:");

        try {
            keyImage = (ImageIO.read(getClass().getResourceAsStream("/objects/key.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    // Methods

    public void showMessage(String text) {

        message = text;
        messageOn = true;
        messageCounter = 0;
    }

    public void draw(Graphics2D g2D) {

        if (gameFinished) {

            g2D.setFont(arial_40);
            g2D.setColor(Color.white);

            String text = "You found the treasure!";
            int textLength = (int)g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();

            int x = (gp.getScreenWidth() / 2) - (textLength / 2);
            int y = (gp.getScreenHeight() / 2) - (gp.getTileSize() * 3);
            g2D.drawString(text, x, y);

            text = "Your time was: " + dFormatMinutes.format(playTimeMinutes) + dFormatSeconds.format(playTime) + "!";

            x = (gp.getScreenWidth() / 2) - (textLength / 2);
            y = (gp.getScreenHeight() / 2) + (gp.getTileSize() * 4);
            g2D.drawString(text, x, y);

            g2D.setFont(arial_80B);
            g2D.setColor(Color.yellow);

            text = "Congratulations!";
            textLength = (int)g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();

            x = (gp.getScreenWidth() / 2) - (textLength / 2);
            y = (gp.getScreenHeight() / 2) + (gp.getTileSize() * 2);
            g2D.drawString(text, x, y);

            gp.setGameThread(null);
        }
        else {

            g2D.setFont(arial_40);
            g2D.setColor(Color.white);
            g2D.drawImage(keyImage, gp.getTileSize() / 2, gp.getTileSize() / 4, gp.getTileSize(), gp.getTileSize(), null);
            g2D.drawString("x " + gp.getPlayer().getNumKeys(), gp.getTileSize() * 3 / 2, gp.getTileSize());

            // Time
            g2D.setFont(g2D.getFont().deriveFont(30F));
            playTime += (double) 1 / 60;
            if (playTime >= 60) {
                playTime -= 60;
                playTimeMinutes ++;
            }

            g2D.drawString("Time: " + dFormatMinutes.format(playTimeMinutes) + dFormatSeconds.format(playTime), gp.getTileSize() * (gp.getMaxScreenCol() - 5), gp.getTileSize());


            // Message
            if (messageOn) {

                g2D.setFont(g2D.getFont().deriveFont(30F));
                g2D.drawString(message, gp.getTileSize() / 2, gp.getTileSize() * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
