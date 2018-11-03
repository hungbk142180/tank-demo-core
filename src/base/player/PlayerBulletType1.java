//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package base.player;

import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;

public class PlayerBulletType1 extends PlayBullet {
    public PlayerBulletType1() {
        this.renderer = new SingleImageRenderer("assets/bullets/red.png");
        this.collider = new BoxCollider(16, 16);
        this.damage = 1;
    }
}