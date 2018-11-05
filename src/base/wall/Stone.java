package base.wall;

import base.GameObject;
import base.Settings;
import base.SoundManage;
import base.Vector2D;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.PlayBullet;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class Stone extends GameObject implements Physics {
    BoxCollider collider;
    int dem = 0;
    public Stone(){
        BufferedImage imageDa = SpriteUtils.loadImage("assets/maps/item_built_map/ConcreteOrig.png");
        this.renderer = new SingleImageRenderer(imageDa);
        this.collider = new BoxCollider(Settings.WAY_SIZE,Settings.WAY_SIZE);
        this.position = new Vector2D(0.0F, 0.0F);
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            this.destroy();
        }
    }

//        PlayBullet a = (PlayBullet)GameObject.intersect(PlayBullet.class,this);
//        if(a!=null ){
//            if(this.dem==0){
//                this.dem++;
//                BufferedImage imageStone = SpriteUtils.loadImage("assets/maps/item_built_map/nut1.jpg");
//                this.renderer = new SingleImageRenderer(imageStone);
//                this.collider = new BoxCollider(Settings.WAY_SIZE,Settings.WAY_SIZE);
//            }
//            else if(this.dem==1){
//                this.dem++;
//                BufferedImage imageStone = SpriteUtils.loadImage("assets/maps/item_built_map/nut2.jpg");
//                this.renderer = new SingleImageRenderer(imageStone);
//                this.collider = new BoxCollider(Settings.WAY_SIZE,Settings.WAY_SIZE);
//            }
//            else{
//                this.destroy();
//            }
//        }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }

}
