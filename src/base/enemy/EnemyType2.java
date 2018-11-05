package base.enemy;

import base.SoundManage;
import base.counter.FrameCounter;
import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyType2 extends Enemy {
    // EnemyType2 có tốc độ 6, máu 1.
    public EnemyType2() {
        this.renderer = new SingleImageRenderer("assets/tank_image/enemy2_down.png");
        this.moveCounter = new FrameCounter(83);
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            SoundManage.playSound("enemy/enemy-explosion.wav");
            this.destroy();
        }
    }

    public void move() {
        boolean moveCounterRun = this.moveCounter.run();
        boolean startCounterRun = this.startCounter.run();
        if (moveCounterRun || this.isStuck || this.way == 4 && startCounterRun) {
            this.way = random.nextInt(4);
            this.moveCounter.reset();
        }
        if (this.way == 0) {
            this.enemyMoveX = 0;
            this.enemyMoveY = -6;
        } else if (this.way == 1) {
            this.enemyMoveX = 0;
            this.enemyMoveY = 6;
        } else if (this.way == 2) {
            this.enemyMoveX = -6;
            this.enemyMoveY = 0;
        } else if (this.way == 3) {
            this.enemyMoveX = 6;
            this.enemyMoveY = 0;
        }
        this.position.addThis(enemyMoveX, enemyMoveY);
        if (this.way == 0 && this.isStuck == false) {
            BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/enemy2_up.png");
            ((SingleImageRenderer) this.renderer).image = imageUp;
        } else if (this.way == 1 && this.isStuck == false) {
            BufferedImage imageDown = SpriteUtils.loadImage("assets/tank_image/enemy2_down.png");
            ((SingleImageRenderer) this.renderer).image = imageDown;
        } else if (this.way == 2 && this.isStuck == false) {
            BufferedImage imageLeft = SpriteUtils.loadImage("assets/tank_image/enemy2_left.png");
            ((SingleImageRenderer) this.renderer).image = imageLeft;
        } else if (this.way == 3 && this.isStuck == false) {
            BufferedImage imageRight = SpriteUtils.loadImage("assets/tank_image/enemy2_right.png");
            ((SingleImageRenderer) this.renderer).image = imageRight;
        }
    }
}
