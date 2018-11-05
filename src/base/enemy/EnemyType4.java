//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package base.enemy;

import base.GameObject;
import base.Settings;
import base.SoundManage;
import base.item_bonus.Boom;
import base.item_bonus.Clock;
import base.item_bonus.Gun;
import base.item_bonus.Shovel;
import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;
import base.scene.Scene;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyType4 extends Enemy {
    Random random = new Random();
    // EnemyType1 có tốc độ 4, máu 1, bắn sẽ rớt ra vật phẩm;
    public EnemyType4() {
        this.renderer = new SingleImageRenderer("assets/tank_image/enemy4_down.png");
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            SoundManage.playSound("enemy/enemy-explosion.wav");
            this.destroy();
            int typeOfItemBonus = random.nextInt(4);
            if (typeOfItemBonus == 0) {
                Scene.boom = GameObject.recycle(Boom.class);
            } else if (typeOfItemBonus == 1) {
                Scene.clock = GameObject.recycle(Clock.class);
            } else if (typeOfItemBonus == 2) {
                Scene.gun = GameObject.recycle(Gun.class);
            } else {
                Scene.shovel = GameObject.recycle(Shovel.class);
            }
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
