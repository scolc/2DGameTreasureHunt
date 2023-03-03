package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    private KeyHandler keyH;
    private final int screenX;
    private final int screenY;
//    private Rectangle solid;
    private int numKeys;

    public Player (GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        // Game camera position.
        screenX = (gp.getScreenWidth() / 2) - (gp.getTileSize() / 2);
        screenY = (gp.getScreenHeight() / 2) - (gp.getTileSize() / 2);

        getSolidArea().x = gp.getTileSize() / 4;
        getSolidArea().y = gp.getTileSize() * 45 / 100;
        getSolidArea().width = gp.getTileSize() / 2;
        getSolidArea().height = gp.getTileSize() / 2;

        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);

        this.numKeys = 0;

        // Setup player sprite
        setDefaultValues();
        getPlayerImage();
    }

    // Getters and Setters

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getNumKeys() {
        return numKeys;
    }

    public void setNumKeys(int numKeys) {
        this.numKeys = numKeys;
    }

    // Methods

    public void setDefaultValues() {
        // Starting position on world map

        setWorldX(getGp().getTileSize() * 10);
        setWorldY(getGp().getTileSize() * 47);
        setSpeed(2);
        setDirection("down");
    }

    public void getPlayerImage() {

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/entity/player.png")));
            getImageSections();
        }
        catch (IOException e) {
            e.printStackTrace();;
        }
    }

    public void update() {

        if (getKeyH().upPressed() || getKeyH().downPressed() || getKeyH().leftPressed() || getKeyH().rightPressed()) {
            if (getKeyH().upPressed()) {
                setDirection("up");
            } else if (getKeyH().downPressed()) {
                setDirection("down");
            } else if (getKeyH().leftPressed()) {
                setDirection("left");
            } else if (getKeyH().rightPressed()) {
                setDirection("right");
            }

            // Check tile collision based on direction
            setCollisionOn(false);
            getGp().getCChecker().checkTile(this);

            // Check object collision
            int objIndex = getGp().getCChecker().checkObject(this, true);
            pickUpObject(objIndex);

            // If collision is false, player can move
            if (!isCollisionOn()) {
                switch (getDirection()) {
                    case ("up"):
                        setWorldY(getWorldY() - getSpeed());
                        break;
                    case ("down"):
                        setWorldY(getWorldY() + getSpeed());
                        break;
                    case ("left"):
                        setWorldX(getWorldX() - getSpeed());
                        break;
                    case ("right"):
                        setWorldX(getWorldX() + getSpeed());
                        break;
                }
            }

            // Make the sprite walk every 10 frames
            setSpriteCounter(getSpriteCounter() + 1);
            if (getSpriteCounter() > 10) {
                if (getSpriteNum() == 0) {
                    setSpriteNum(1);
                }
                else if (getSpriteNum() == 1) {
                    setSpriteNum(2);
                }
                else if (getSpriteNum() == 2) {
                    setSpriteNum(3);
                }
                else if (getSpriteNum() == 3) {
                    setSpriteNum(0);
                }
                setSpriteCounter(0);
            }
        }

    }

    public void pickUpObject(int i) {

        if (i != 999) {

            String objectName = getGp().getObject()[i].getName();

            switch (objectName) {
                case "Key":
                    numKeys ++;
                    getGp().getObject()[i] = null;
                    getGp().playSE(1);
                    getGp().getUi().showMessage("You got a key!");
                    break;
                case "Door":
                    if (numKeys > 0) {
                        getGp().getObject()[i] = null;
                        numKeys --;
                        getGp().playSE(3);
                        getGp().getUi().showMessage("You opened the door!");
                    }
                    else {
                        getGp().getUi().showMessage("You need a key!");
                    }
                    break;
                case "Chest":
                    getGp().getObject()[i] = null;
                    getGp().stopMusic();
                    getGp().playSE(4);
                    getGp().getUi().setGameFinished(true);
                    break;
                case "Boot":
                    getGp().getObject()[i] = null;
                    setSpeed(getSpeed() + 2);
                    getGp().playSE(2);
                    getGp().getUi().showMessage("Speed up!");
                    break;
            }
        }
    }

    public void draw(Graphics2D g2D) {

//        g2D.setColor(Color.WHITE);
//        g2D.fillRect(getX(), getY(), gp.getTileSize(), gp.getTileSize()); // white rectangle

        BufferedImage image = null;

        switch(getDirection()) {
            case "up":
                image = getUp()[getSpriteNum()];
                break;
            case "down" :
                image = getDown()[getSpriteNum()];
                break;
            case "left" :
                image = getLeft()[getSpriteNum()];
                break;
            case "right" :
                image = getRight()[getSpriteNum()];
                break;
        }

        g2D.drawImage(image, screenX, screenY, null);
    }
}
