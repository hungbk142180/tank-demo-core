package game;

import base.GameObject;
import base.scene.SceneManager;

import base.scene.welcomeScene.WelcomeScene;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    public static JLabel loading;
    public GameCanvas() {
        SceneManager.signNewScene(new WelcomeScene());
        loading = new JLabel("Loading 0%");
        loading.setLocation(0,0);
        loading.setBackground(Color.GRAY);
        loading.setForeground(Color.white);
        loading.setVisible(false);
        this.add(loading);
    }

    public static void setTextLoading(double num){
        loading.setText("Loading "+num+"%");
    }

    public void run() {
        GameObject.runAll();
    }

    public void render() {
        GameObject.renderAllToBackBuffer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        GameObject.renderBackBufferToGame(g);
    }
}
