package base.enemy;

import base.SoundManage;
import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyType3 extends Enemy {
    //EnemyType3 có máu 2, tốc độ 4
    int hp;
    public EnemyType3() {
        this.renderer = new SingleImageRenderer("assets/tank_image/enemy3_down.png");
        this.collider = new BoxCollider(54, 54);
        this.hp = 2;
    }

    public void takeDamage(int damage) {
        SoundManage.playSound("enemy/hit_armor.wav");
        this.hp -= damage;
        if (this.hp <= 0) {
            SoundManage.playSound("enemy/enemy-explosion.wav");
            hp = 0;
            this.destroy();
        }
    }

    public void move() {
        super.move();
        if (this.way == 0 && this.isStuck == false) {
            if (this.hp == 2) {
                BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/enemy3_up.png");
                ((SingleImageRenderer) this.renderer).image = imageUp;
            } else {
                BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/enemy1_up.png");
                ((SingleImageRenderer) this.renderer).image = imageUp;
            }
        } else if (this.way == 1 && this.isStuck == false) {
            if (this.hp == 2) {
                BufferedImage imageDown = SpriteUtils.loadImage("assets/tank_image/enemy3_down.png");
                ((SingleImageRenderer) this.renderer).image = imageDown;
            } else {
                BufferedImage imageDown = SpriteUtils.loadImage("assets/tank_image/enemy1_down.png");
                ((SingleImageRenderer) this.renderer).image = imageDown;
            }
        } else if (this.way == 2 && this.isStuck == false) {
            if (this.hp == 2) {
                BufferedImage imageLeft = SpriteUtils.loadImage("assets/tank_image/enemy3_left.png");
                ((SingleImageRenderer) this.renderer).image = imageLeft;
            } else {
                BufferedImage imageLeft = SpriteUtils.loadImage("assets/tank_image/enemy1_left.png");
                ((SingleImageRenderer) this.renderer).image = imageLeft;
            }
        } else if (this.way == 3 && this.isStuck == false) {
            if (this.hp == 2) {
                BufferedImage imageRight = SpriteUtils.loadImage("assets/tank_image/enemy3_right.png");
                ((SingleImageRenderer) this.renderer).image = imageRight;
            } else {
                BufferedImage imageRight = SpriteUtils.loadImage("assets/tank_image/enemy1_right.png");
                ((SingleImageRenderer) this.renderer).image = imageRight;
            }
        }
    }
}
