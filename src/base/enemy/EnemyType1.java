//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package base.enemy;

import base.Settings;
import base.physics.BoxCollider;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyType1 extends Enemy {
    public EnemyType1() {
        this.renderer = new SingleImageRenderer("assets/tank_image/enemy5.gif");
        this.collider = new BoxCollider(52, 52);
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            this.destroy();
        }
    }

    public void move(){
        boolean moveCounterRun = this.moveCounter.run();
        if (moveCounterRun) {
            this.way = random.nextInt(4);
            this.moveCounter.reset();
        }
        if (this.way == 0) {
            this.enemyMoveX = 0;
            this.enemyMoveY = -4;
            BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/enemy5up.jpg");
            ((SingleImageRenderer) this.renderer).image = imageUp;
        } else if (this.way == 1) {
            this.enemyMoveX = 0;
            this.enemyMoveY = 4;
            BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/enemy5.gif");
            ((SingleImageRenderer) this.renderer).image = imageUp;
        } else if (this.way == 2) {
            this.enemyMoveX = -4;
            this.enemyMoveY = 0;
            BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/enemy5left.jpg");
            ((SingleImageRenderer) this.renderer).image = imageUp;
        } else if (this.way == 3) {
            this.enemyMoveX = 4;
            this.enemyMoveY = 0;
            BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/enemy5right.jpg");
            ((SingleImageRenderer) this.renderer).image = imageUp;
        }
        this.position.addThis(enemyMoveX, enemyMoveY);
//        if(this.position.y == Settings.WAY_SIZE*20){
//            Settings.ENEMY_MOVE = -4;
//            BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/enemy5up.jpg");
//            ((SingleImageRenderer) this.renderer).image = imageUp;
//            this.way = new boolean[]{true,false,false,false};
//        }
//        else if(this.position.y == Settings.WAY_SIZE*5){
//            Settings.ENEMY_MOVE = 4;
//            BufferedImage imageDown = SpriteUtils.loadImage("assets/tank_image/enemy5.gif");
//            ((SingleImageRenderer) this.renderer).image = imageDown;
//            this.way = new boolean[]{false,true,false,false};
//        }
//        this.position.addThis(0, Settings.ENEMY_MOVE);
    }
}
