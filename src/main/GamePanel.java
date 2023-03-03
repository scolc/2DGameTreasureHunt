package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable { // Implements Runnable requires the run() method override

    // SCREEN SETTINGS
    private final int originalTileSize; // Final means can't be extended or changed.
    private final int scale;

    private final int tileSize;
    private final int maxScreenCol;
    private final int maxScreenRow;
    private final int screenWidth;
    private final int screenHeight;

    // World Settings
    private final int maxWorldCol;
    private final int maxWorldRow;

    // FPS
    private int FPS = 60;

    // System
    private TileManager tileM;
    private KeyHandler keyH;
    private Thread gameThread; // Useful for running time within the game
    private Sound se, music;
    private UI ui;

    // Player and Objects
    private Player player;
    private CollisionChecker cChecker;
    private SuperObject object[];
    private AssetManager AssMan;
    private UtilityTool uTool;

    public GamePanel () {

        this.originalTileSize = 32; // 32x32 tile size.
        this.scale = 2; // To scale up the tile resolution to fit a monitor

        this.tileSize = originalTileSize * scale; // 64x64 tile
        this.maxScreenCol = 16;
        this.maxScreenRow = 12; // These make a 16x12 tiles or 4:3 ratio
        this.screenWidth = tileSize * maxScreenCol; // 1024 pixels
        this.screenHeight = tileSize * maxScreenRow; // 768 pixels

        this.maxWorldCol = 60;
        this.maxWorldRow = 56;

        this.uTool = new UtilityTool();

        this.tileM = new TileManager(this);
        this.keyH = new KeyHandler();
        this.se = new Sound();
        this.music = new Sound();
        this.ui = new UI(this);

        this.player = new Player(this, keyH);
        this.object = new SuperObject[30];
        this.AssMan = new AssetManager(this);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Improve rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); // GamePanel can be "focused" to receive key input

        this.cChecker = new CollisionChecker(this);
    }

    // Getters and Setters

    public int getTileSize() {
        return tileSize;
    }

    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public Player getPlayer() {
        return player;
    }

    public CollisionChecker getCChecker() {
        return cChecker;
    }

    public TileManager getTileM() {
        return tileM;
    }

    public SuperObject[] getObject() {
        return object;
    }

    public Sound getSound() {
        return se;
    }

    public void setSound(Sound sound) {
        this.se = sound;
    }

    public Sound getMusic() {
        return music;
    }

    public void setMusic(Sound music) {
        this.music = music;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public UtilityTool getuTool() {
        return uTool;
    }

    // Methods

    public void setupGame() {

        AssMan.setObject();
        playMusic(0);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start(); // Calls the run() method
    }

    @Override
    public void run() {
        // The game loop

        // Set a timer between updates
        double drawInterval = 1000000000/FPS; // 1 second / 60 = 0.01666666667 interval.
        double delta = 0; // Track the interval passed.
        long lastTime = System.nanoTime(); // Nanoseconds. System.currentTimeMillis() could also be used 1000 ms = 1 second. Nano more accurate.
        long currentTime;

        // FPS counter
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval; // Work out how much of the interval has passed. When delta >= 1 means drawInterval has passed.
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {

                // 1. Update: update the information eg character position
                update();

                // 2. Draw: draw the screen with the updated information
                repaint(); // Calls the paintComponent() method in JComponent

                delta --;
                drawCount ++;
            }
//            if (timer >= 1000000000){
//                System.out.println("FPS: " + drawCount);
//                // System.out.println("Player world co-ords: (" + player.getWorldX() + "," + player.getWorldY() + ")");
//                drawCount = 0;
//                timer = 0;
//            }
        }
    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) { // This comes from JComponent

        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        // Debug
        long drawStart = 0;
        if (keyH.checkDrawTime()) {
            drawStart = System.nanoTime();
        }

        // Tile
        tileM.draw(g2D);

        // Objects
        for (int i = 0; i < object.length; i ++) {
            if (object[i] != null) {
                object[i].draw(g2D, this);
            }
        }

        // Player
        player.draw(g2D);

        // UI
        ui.draw(g2D);

        // Debug
        if (keyH.checkDrawTime()) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2D.setColor(Color.white);
            g2D.drawString("Draw Time: " + passed, 10, 500);
            System.out.println("Draw Time: " + passed);
        }

        g2D.dispose(); // Saves memory
    }

    public void playMusic(int i) {

        music.setFile(i);;
        music.play();
        music.loop();
    }

    public void stopMusic() {

        music.stop();
    }

    public void playSE(int i) {

        se.setFile(i);
        se.play();
    }
}
