//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package base.player;

import base.GameObject;
import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;
import base.wall.Stone;

import java.util.ArrayList;

public class PlayerBulletType2 extends PlayBullet {
    public PlayerBulletType2() {
        this.collider = new BoxCollider(16, 16);
        this.damage = 1;
    }

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