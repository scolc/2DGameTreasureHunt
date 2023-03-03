package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    private BufferedImage image;
    private String name;
    private boolean collision;
    private int worldX, worldY;
    private Rectangle solidArea;
    private int solidAreaDefaultX, solidAreaDefaultY;
    private GamePanel gp;

    public SuperObject (GamePanel gp) {

        this.name = "";
        this.collision = false;
        this.worldX = 0;
        this.worldY = 0;

        this.gp = gp;

        this.solidArea = new Rectangle(gp.getTileSize() * 1 / 6, gp.getTileSize() * 1 / 6, gp.getTileSize() * 2 / 3, gp.getTileSize() * 2 / 3);
        this.solidAreaDefaultX = gp.getTileSize() * 1 / 6;
        this.solidAreaDefaultY = gp.getTileSize() * 1 / 6;
    }

    // Getters and Setters

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = gp.getuTool().scaleImage(image, gp.getTileSize(), gp.getTileSize());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
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

    public void draw(Graphics2D g2D, GamePanel gp) {

        int screenX = gp.getPlayer().getScreenX() - gp.getPlayer().getWorldX() + worldX; // Sets X relative to screen (0,0) - subtract player world X then add a screen offset
        int screenY = gp.getPlayer().getScreenY() - gp.getPlayer().getWorldY() + worldY;

        if (worldX > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() - gp.getTileSize() &&
                worldX < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() + gp.getTileSize() &&
                worldY > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() - gp.getTileSize() &&
                worldY < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY() + gp.getTileSize()) {

            g2D.drawImage(image, screenX, screenY, null);
        }
    }
}
