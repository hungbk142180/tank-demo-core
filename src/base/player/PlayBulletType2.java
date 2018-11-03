package base.player;

import base.GameObject;
import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;
import base.wall.Stone;

import java.util.ArrayList;

public class PlayBulletType2 extends PlayBullet {
    public PlayBulletType2() {
        super();
        this.renderer = new SingleImageRenderer("assets/bullets/white.png");
        this.collider = new BoxCollider(16, 16);
        this.damage = 1;
    }

    @Override
    public void run() {
        super.run();
        ArrayList<Stone> stones = GameObject.intersectManyItems(Stone.class, this);

        if(stones.size() > 0){
            for(Stone i : stones){
                i.destroy();
                this.destroy();
            }

        }
    }
}
