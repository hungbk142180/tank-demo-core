package base.item_bonus;

import base.Vector2D;
import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;
import java.awt.image.BufferedImage;

public class Gun extends Bonus {
    public Gun(){
        BufferedImage imageGun = SpriteUtils.loadImage("assets/maps/item_bonus/gun.PNG");
        this.renderer = new SingleImageRenderer(imageGun);
        this.collider = new BoxCollider(53,53);
        this.position = new Vector2D(446F, 502F);
    }
}
