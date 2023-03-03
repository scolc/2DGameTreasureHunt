package tile;


import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    private GamePanel gp;
    private Tile[] tile;
    private int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.mapTileNum = new int[gp.getMaxWorldRow()][gp.getMaxWorldCol()];
        this.gp = gp;
        this.tile = new Tile[100];

        // Setup tiles
        getTileImage();
        loadMap("/maps/worldmap.txt");

    }

    // Getters and Setters

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public Tile[] getTile() {
        return tile;
    }

    public void setTile(Tile[] tile) {
        this.tile = tile;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    // Methods

    public void getTileImage() {

        setupImage(0, "blank", true);
        setupImage(1, "grass00", false);
        setupImage(2, "grass01", false);
        setupImage(3, "tree", true);
        setupImage(4, "rock", true);
        setupImage(5, "wall", true);
        setupImage(10, "earth", false);
        setupImage(30, "sand", false);
        setupImage(50, "water", true);

    }

    public void setupImage(int index, String imageName, boolean collision) {

        try {
//            tile[index] = new Tile();
//            tile[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
//            tile[index].setImage(gp.getuTool().scaleImage(tile[index].getImage(), gp.getTileSize(), gp.getTileSize()));
//            tile[index].setCollision(collision);

            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            if (image.getWidth() == gp.getOriginalTileSize()) {
                // Single tile image
                tile[index] = new Tile();
                tile[index].setImage(gp.getuTool().scaleImage(image, gp.getTileSize(), gp.getTileSize()));
                tile[index].setCollision(collision);
            }
            else {
                // 5 x 3 tile image
                setupMultiTile(image, index, collision);

            }

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void setupMultiTile(BufferedImage image, int index, boolean collision) {

        int imageX = 0;
        int imageY = 0;

        for (int i = index; i < (index + 9); i ++) {
            tile[i] = new Tile();
            if (i == index) {
                // First index set to middle tile
                tile[i].setImage(getSection(image, gp.getOriginalTileSize(), gp.getOriginalTileSize()));
            }
            else {
                tile[i].setImage(getSection(image, imageX, imageY));
                if (i == index + 4) {
                    // Skip middle tile X
                    imageX += gp.getOriginalTileSize();
                }
                imageX += gp.getOriginalTileSize();
                if (imageX == gp.getOriginalTileSize() * 3) {
                    imageX = 0;
                    imageY += gp.getOriginalTileSize();
                }
            }

            tile[i].setCollision(collision);
        }

        imageX = gp.getOriginalTileSize() * 3;
        imageY = 0;

        for (int i = index + 9; i < index + 15; i++) {
            tile[i] = new Tile();
            tile[i].setImage(getSection(image, imageX, imageY));
            tile[i].setCollision(collision);
            imageX += gp.getOriginalTileSize();
            if (imageX == gp.getOriginalTileSize() * 5) {
                imageX = gp.getOriginalTileSize() * 3;
                imageY += gp.getOriginalTileSize();
            }
        }
    }

    private BufferedImage getSection(BufferedImage image, int x, int y) {

        return gp.getuTool().scaleImage(image.getSubimage(x, y, gp.getOriginalTileSize(), gp.getOriginalTileSize()), gp.getTileSize(), gp.getTileSize());
    }

    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {

                String line = br.readLine();
                String numbers[] = line.split(" "); // Splits all the numbers from a line into an array using " " as a delimiter


                while (col < gp.getMaxWorldCol()) {

                    int num = Integer.parseInt(numbers[col]); // Current String at col index is parsed into an int

                    mapTileNum[row][col] = num;
                    col ++;
                }

                if (col == gp.getMaxWorldCol()) {
                    col = 0;
                    row ++;
                }
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2D) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = gp.getPlayer().getScreenX() - gp.getPlayer().getWorldX() + worldX; // Sets X relative to player screen position  - subtract player world X then add a screen offset
            int screenY = gp.getPlayer().getScreenY() - gp.getPlayer().getWorldY() + worldY;

            if (worldX > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() - gp.getTileSize() &&
                worldX < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() + gp.getTileSize() &&
                worldY > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() - gp.getTileSize() &&
                worldY < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY() + gp.getTileSize()) {

                g2D.drawImage(tile[mapTileNum[worldRow][worldCol]].getImage(), screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow ++;
            }
        }

    }
}
