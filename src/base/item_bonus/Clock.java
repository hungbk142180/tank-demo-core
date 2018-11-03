package base.item_bonus;

import base.Vector2D;
import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;
import java.awt.image.BufferedImage;

public class Clock extends Bonus {
    public Clock(){
        BufferedImage imageClock = SpriteUtils.loadImage("assets/maps/item_bonus/clock.PNG");
        this.renderer = new SingleImageRenderer(imageClock);
        this.collider = new BoxCollider(53,53);
        this.position = new Vector2D(334F, 334F);
    }
}
