package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed, downPressed, leftPressed, rightPressed;
    // Debug
    private boolean checkDrawTime;

    public KeyHandler() {

        this.upPressed = false;
        this.downPressed = false;
        this.leftPressed = false;
        this.rightPressed = false;

        // Debug
        this.checkDrawTime = false;
    }

    // Getters and Setters

    public boolean upPressed() {
        return upPressed;
    }

    public boolean downPressed() {
        return downPressed;
    }

    public boolean leftPressed() {
        return leftPressed;
    }

    public boolean rightPressed() {
        return rightPressed;
    }

    public boolean checkDrawTime() {
        return checkDrawTime;
    }

    // Methods

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        // Debug
        if (code == KeyEvent.VK_T) {
            if (!checkDrawTime) {
                checkDrawTime = true;
            }
            else if (checkDrawTime) {
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
