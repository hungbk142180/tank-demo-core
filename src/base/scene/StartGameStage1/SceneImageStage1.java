package base.scene.StartGameStage1;

import base.GameObject;
import base.scene.Scene;
import base.scene.gameoverScen.Banner;

public class SceneImageStage1 extends Scene {

    @Override
    public void destroy() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        GameObject.recycle(BannerStage1.class);
    }
}
