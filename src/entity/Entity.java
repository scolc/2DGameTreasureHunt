package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    private GamePanel gp;

    private int worldX, worldY;
    private int speed;

    private BufferedImage image;
    private BufferedImage[] up, down, left, right;
    private String direction;

    private int spriteCounter;
    private int spriteNum;

    private Rectangle solidArea;
    private int solidAreaDefaultX, solidAreaDefaultY;
    private boolean collisionOn;

    public Entity(GamePanel gp) {

        this.gp = gp;

        spriteCounter = 0;
        spriteNum = 1;

        collisionOn = false;

        solidArea = new Rectangle();

        this.up = new BufferedImage[4];
        this.down = new BufferedImage[4];
        this.left = new BufferedImage[4];
        this.right = new BufferedImage[4];
    }

    // Getters and Setters

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage[] getUp() {
        return up;
    }

    public BufferedImage[] getDown() {
        return down;
    }

    public BufferedImage[] getLeft() {
        return left;
    }

    public BufferedImage[] getRight() {
        return right;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    // Methods

    public void getImageSections() {

        int imageX = 0;
        int imageY = 0;
        for (int i = 0; i < down.length; i ++) {
            if (i == 2) {
                down[i] = down[0];
            }
            else {
                down[i] = getSection(imageX, imageY);
                imageX += gp.getOriginalTileSize();
            }
        }

        imageX = 0;
        imageY += getGp().getOriginalTileSize();
        for (int i = 0; i < up.length; i ++) {
            if (i == 2) {
                up[i] = up[0];
            }
            else {
                up[i] = getSection(imageX, imageY);
                imageX += gp.getOriginalTileSize();
            }
        }

        imageX = 0;
        imageY += getGp().getOriginalTileSize();
        for (int i = 0; i < left.length; i ++) {
            if (i == 2) {
                left[i] = left[0];
            }
            else {
                left[i] = getSection(imageX, imageY);
                imageX += gp.getOriginalTileSize();
            }
        }

        imageX = 0;
        imageY += getGp().getOriginalTileSize();
        for (int i = 0; i < right.length; i ++) {
            if (i == 2) {
                right[i] = right[0];
            }
            else {
                right[i] = getSection(imageX, imageY);
                imageX += gp.getOriginalTileSize();
            }
        }

    }

    private BufferedImage getSection(int x, int y) {

        return gp.getuTool().scaleImage(image.getSubimage(x, y, gp.getOriginalTileSize(), gp.getOriginalTileSize()), gp.getTileSize(), gp.getTileSize());
    }
}
