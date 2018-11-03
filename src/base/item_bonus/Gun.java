package base.item_bonus;

import base.GameObject;
import base.Vector2D;
import base.counter.FrameCounter;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Gun extends GameObject implements Physics {
    BoxCollider collider;
    FrameCounter blinkCounter;
    public Gun(){
        BufferedImage imageGun = SpriteUtils.loadImage("assets/maps/item_bonus/gun.PNG");
        this.renderer = new SingleImageRenderer(imageGun);
        this.collider = new BoxCollider(53,53);
        //this.position = new Vector2D(0.0F, 0.0F);
        this.position = new Vector2D(446F, 502F);
        this.blinkCounter = new FrameCounter(15);
    }
    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
    public void render(Graphics g) {
        if(this.blinkCounter.run()) {
            super.render(g);
            this.blinkCounter.reset();
        }
    }
}
