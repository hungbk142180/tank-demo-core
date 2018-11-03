package base.item_bonus;

import base.Vector2D;
import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;
import java.awt.image.BufferedImage;

public class Shovel extends Bonus {
     public Shovel(){
        BufferedImage imageClock = SpriteUtils.loadImage("assets/maps/item_bonus/shovel.PNG");
        this.renderer = new SingleImageRenderer(imageClock);
        this.collider = new BoxCollider(53,53);
        this.position = new Vector2D(278F, 446F);
    }
}
