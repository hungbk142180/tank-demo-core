package base.player;

import base.GameObject;
import base.Vector2D;
import base.renderer.AnimationRenderer;

public class Explosion extends GameObject {
    public Explosion() {
        this.position = new Vector2D();
        this.renderer = new AnimationRenderer(
                5, true,
                "assets/animation/big_explosion_1.png",
                "assets/animation/big_explosion_2.png",
                "assets/animation/big_explosion_3.png",
                "assets/animation/big_explosion_4.png",
                "assets/animation/big_explosion_5.png"
        );
    }
}