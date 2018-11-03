package base.item_bonus;

import base.GameObject;
import base.Vector2D;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class Boom extends GameObject implements Physics {
    BoxCollider collider;
    public Boom(){
        BufferedImage imageBoom = SpriteUtils.loadImage("assets/maps/item_bonus/boom.PNG");
        this.renderer = new SingleImageRenderer(imageBoom);
        this.collider = new BoxCollider(53,53);
        //this.position = new Vector2D(0.0F, 0.0F);
        this.position = new Vector2D(334F, 502F);
    }
    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
