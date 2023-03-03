package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set what it does when we close window otherwise game won't end
        window.setResizable(false); // Not resizable so won't mess up picture settings
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // Allows window to appear at the preferred size

        window.setLocationRelativeTo(null); // Centres on screen when opened
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
