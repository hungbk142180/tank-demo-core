package base.wall;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.PlayBullet;
import base.renderer.BoxColliderRenderer;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brick extends GameObject implements Physics {
    BoxCollider collider;
    int dem = 0;
    public Brick(){
        BufferedImage imageBrick = SpriteUtils.loadImage("assets/maps/item_built_map/BricksOrig.png");
        this.renderer = new SingleImageRenderer(imageBrick);
        this.collider = new BoxCollider(Settings.WAY_SIZE,Settings.WAY_SIZE);
        this.position = new Vector2D(0.0F, 0.0F);
    }
    public void takeDamage(int damage) {
        if (damage > 0) {
            this.destroy();
        }
        System.out.println(damage);
    }

//    public void run() {
////        PlayBullet a = (PlayBullet)GameObject.intersect(PlayBullet.class,this);
////        if(a!=null ){
////            if(this.dem==0){
////                this.dem++;
////                BufferedImage imageBrick = SpriteUtils.loadImage("assets/maps/item_built_map/final.jpg");
////                this.renderer = new SingleImageRenderer(imageBrick);
////                this.collider = new BoxCollider(Settings.WAY_SIZE,Settings.WAY_SIZE);
////            }
////            else{
//////                a.destroy();
////
////                this.destroy();
////            }
////        }
//    }
    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
