package base.wall;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class Eagle extends GameObject implements Physics {
    BoxCollider collider;
    public Eagle(){
        BufferedImage imageEagle = SpriteUtils.loadImage("assets/maps/item_bonus/boom.PNG");
        this.renderer = new SingleImageRenderer(imageEagle);
        this.collider = new BoxCollider(Settings.WAY_SIZE, Settings.WAY_SIZE);
        this.position = new Vector2D(0.0F, 0.0F);
    }
    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
