package base.scene.StartGameStage1;

import base.GameObject;
import base.Settings;
import base.counter.FrameCounter;
import base.event.KeyEventPress;
import base.renderer.SingleImageRenderer;
import base.scene.SceneManager;
import base.scene.SceneStage1;
import base.scene.SceneStage2;
import tklibs.SpriteUtils;

public class BannerStage1 extends GameObject {
    FrameCounter frameCounter;
    public BannerStage1(){
        super();
        this.renderer = new SingleImageRenderer(SpriteUtils.loadImage("assets/spaceinvader.png"));
        this.position.set(Settings.SCREEN_WIDHT / 2 , Settings.SCREEN_HEIGHT / 2);
        frameCounter = new FrameCounter(60);
    }

    @Override
    public void run() {
        if(frameCounter.run()){
            SceneManager.signNewScene(new SceneStage1());//?
            frameCounter.reset();
        }
    }
}
