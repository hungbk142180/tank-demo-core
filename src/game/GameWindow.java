package game;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.event.KeyEventPress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {
    GameCanvas canvas;
    public GameWindow() {
        //setup window
        this.setSize(Settings.SCREEN_WIDHT + Settings.WAY_SIZE*10,
                        Settings.SCREEN_HEIGHT + Settings.WAY_SIZE + 8);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupEventListtener();
        //init game
        this.canvas = new GameCanvas();
        this.canvas.setPreferredSize(new Dimension(Settings.SCREEN_WIDHT , Settings.SCREEN_HEIGHT));//size ko tinh sisegame window
        this.add(canvas);
        this.setVisible(true);
        this.pack();

    }

    private void setupEventListtener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                KeyEventPress.isAnyKeyPress = true;//
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = true;
                }
               else if(e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = true;
                }
               else if(e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = true;
                }
               else if(e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = true;
                }
               else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isFirePress = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                KeyEventPress.isAnyKeyPress = false;//
                if(e.getKeyCode() == KeyEvent.VK_W) {
                   KeyEventPress.isUpPress = false;

                }
               else if(e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = false;

                }
               else if(e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = false;

                }
               else if(e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = false;

                }
               else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isFirePress = false;
                }
            }
        });
    }

    public void gameLoop() {
        long delay = 1000 / 60;
        long lastTime = 0;
        while(true) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastTime > delay) {
                canvas.run(); //runAll()
                canvas.render(); // render all to backBuffer
                this.repaint(); // render backBuffer to game
                lastTime = currentTime;
            }
        }
    }
}
