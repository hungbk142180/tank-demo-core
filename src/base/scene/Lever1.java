package base.scene;

import base.GameObject;
import base.scene.Scene;
import base.scene.welcomeScene.Banner;

public class Lever1 extends Scene {
    @Override
    public void destroy() {
        GameObject.clearAll();
    }

    @Override
    public void init(){
        GameObject banner = GameObject.recycle(Banner.class);
    }
}
