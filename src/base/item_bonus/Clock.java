package base.item_bonus;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.counter.FrameCounter;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Clock extends GameObject implements Physics {
    BoxCollider collider;
    FrameCounter blinkCounter;
    public Clock(){
        BufferedImage imageClock = SpriteUtils.loadImage("assets/maps/item_bonus/clock.PNG");
        this.renderer = new SingleImageRenderer(imageClock);
        this.collider = new BoxCollider(53,53);
        //this.position = new Vector2D(0.0F, 0.0F);
        this.position = new Vector2D(334F, 334F);
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
