package base.scene.gameoverScen;

import base.GameObject;
import base.Settings;
import base.SoundManage;
import base.event.KeyEventPress;
import base.renderer.SingleImageRenderer;
import base.scene.SceneManager;
import base.scene.SceneStage1;
import tklibs.SpriteUtils;

public class Banner extends GameObject {
    public Banner(){
        super();
        this.renderer = new SingleImageRenderer(SpriteUtils.loadImage("assets/06.png"));
        this.position.set(Settings.SCREEN_WIDHT / 2 , Settings.SCREEN_HEIGHT / 2);
    }

    @Override
    public void run() {
        if(KeyEventPress.isAnyKeyPress){
            SceneManager.signNewScene(new SceneStage1());//?
        }
    }
}
