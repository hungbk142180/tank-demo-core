//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package base.enemy;

import base.Settings;
import base.SoundManage;
import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyType4 extends Enemy {
    // EnemyType1 có tốc độ 4, máu 1, bắn sẽ rớt ra vật phẩm;
    public EnemyType4() {
        this.renderer = new SingleImageRenderer("assets/tank_image/enemy4_down.png");
        this.collider = new BoxCollider(54, 54);
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            SoundManage.playSound("enemy/enemy-explosion.wav");
            this.destroy();
        }
    }

    public void move() {
        super.move();
        if (this.way == 0 && this.isStuck == false) {
            BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/enemy4_up.png");
            ((SingleImageRenderer) this.renderer).image = imageUp;
        } else if (this.way == 1 && this.isStuck == false) {
            BufferedImage imageDown = SpriteUtils.loadImage("assets/tank_image/enemy4_down.png");
            ((SingleImageRenderer) this.renderer).image = imageDown;
        } else if (this.way == 2 && this.isStuck == false) {
            BufferedImage imageLeft = SpriteUtils.loadImage("assets/tank_image/enemy4_left.png");
            ((SingleImageRenderer) this.renderer).image = imageLeft;
        } else if (this.way == 3 && this.isStuck == false) {
            BufferedImage imageRight = SpriteUtils.loadImage("assets/tank_image/enemy4_right.png");
            ((SingleImageRenderer) this.renderer).image = imageRight;
        }
    }
}
