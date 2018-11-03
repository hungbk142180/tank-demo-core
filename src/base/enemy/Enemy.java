package base.enemy;

import base.*;
import base.action.*;
import base.counter.FrameCounter;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.Tank;
import base.renderer.AnimationRenderer;
import base.renderer.SingleImageRenderer;
import base.scene.SceneStage1;
import base.wall.Brick;
import base.wall.Stone;
import base.wall.WallManagement;
import base.wall.Water;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject implements Physics {
    BoxCollider collider;
    Action action;
    float currentX = 0.0F;
    float currentY = 0.0F;
    int way = 4; // 0-up, 1-down, 2-left, 3-right, 4-default
    FrameCounter moveCounter;
    Random random = new Random();
    int enemyMoveX;
    int enemyMoveY;
   // WallManagement arr;
    boolean isStuck = false;
    FrameCounter startCounter;

//     public Boolean isLife;// check xem hien tai co o tren map khi ma da khoi tao ko
    public static Boolean checkClock = true;

    public Enemy() {
        super();
     //   arr = new WallManagement();
        this.position = new Vector2D(200, 100);
        this.moveCounter = new FrameCounter(139);
        this.startCounter = new FrameCounter(27);
        this.defineAction();
        this.enemyMoveX = 0;
        this.enemyMoveY = 0;
//        isLife = false;
    }

    void defineAction(){
        ActionWait actionWait = new ActionWait(50);
        Action actionFire =  new Action() {
            @Override
            public void run(GameObject master) {
                fire();
                this.isDone = true;
            }

            @Override
            public void reset() {
                this.isDone = false;
            }
        };
        Action actionMove = new Action() {
            @Override
            public void run(GameObject master) {
                move();
            }

            @Override
            public void reset() {

            }
        };

        ActionSequence actionSequence = new ActionSequence(actionWait, actionFire);
        ActionParallel actionParallel = new ActionParallel(actionMove,actionSequence);
        ActionRepeat actionRepeat = new ActionRepeat(actionParallel);
        this.action = actionRepeat;
    }

    public void move(){
        boolean moveCounterRun = this.moveCounter.run();
        boolean startCounterRun = this.startCounter.run();
        if (moveCounterRun || this.isStuck || this.way == 4 && startCounterRun) {
            this.way = random.nextInt(4);
            this.moveCounter.reset();
        }
        if (this.way == 0) {
            this.enemyMoveX = 0;
            this.enemyMoveY = -4;
        } else if (this.way == 1) {
            this.enemyMoveX = 0;
            this.enemyMoveY = 4;
        } else if (this.way == 2) {
            this.enemyMoveX = -4;
            this.enemyMoveY = 0;
        } else if (this.way == 3) {
            this.enemyMoveX = 4;
            this.enemyMoveY = 0;
        }
        this.position.addThis(enemyMoveX, enemyMoveY);
    }
    private boolean checkIntersects() {
        Tank tank = (Tank)GameObject.intersect(Tank.class, this);
        Enemy enemy = (Enemy)GameObject.intersect(Enemy.class, this);
        return   tank != null || enemy != null;
    }

    private boolean checkIntersectsWall(){
        for (GameObject i :
                SceneStage1.arr) {
            if(i instanceof Brick || i instanceof Stone || i instanceof Water){
                Brick brick = (Brick)GameObject.intersect(Brick.class, this);
                Stone stone = (Stone)GameObject.intersect(Stone.class, this);
                Water water = (Water)GameObject.intersect(Water.class, this);
                return brick!=null || stone!=null || water!=null;
            }

        }
        return false;
    }

    @Override
    public void run() {
        if(!checkClock){
            return;
        }
        if (!this.checkIntersects() || !this.checkIntersectsWall()) {
            this.currentX = this.position.x;
            this.currentY = this.position.y;
        }
        this.action.run(this);

        if (this.checkIntersects()|| this.checkIntersectsWall() ||
                this.position.x-Settings.WAY_SIZE <0 ||
                this.position.x+Settings.WAY_SIZE >Settings.SCREEN_WIDHT ||
                this.position.y-Settings.WAY_SIZE <0 ||
                this.position.y+Settings.WAY_SIZE >Settings.SCREEN_HEIGHT
        ) {
            this.position.x = this.currentX;
            this.position.y = this.currentY;
            this.isStuck = true;
        } else {
            this.isStuck = false;
        }
    }

    public void fire() {
        EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
        if(this.way == 0) {
            bullet.velocity.set(0, -12);
            bullet.position.set(this.position.x, this.position.y - (float) Settings.WAY_SIZE);
        }
        else if(this.way == 1) {
            bullet.velocity.set(0, 12);
            bullet.position.set(this.position.x, this.position.y + (float) Settings.WAY_SIZE);
        }
        else if(this.way == 2) {
            bullet.velocity.set(-12, 0);
            bullet.position.set(this.position.x -(float) Settings.WAY_SIZE , this.position.y);
        }
        else if(this.way == 3) {
            bullet.velocity.set(12, 0);
            bullet.position.set(this.position.x + (float) Settings.WAY_SIZE, this.position.y);
        }
    }

    public void takeDamage(int damage) {

    }

    public void destroy() {
        super.destroy();
        EnemySummoner.enemyNow -= 1;
        base.enemy.Explosion explosion = GameObject.recycle(base.enemy.Explosion.class);
        explosion.position.set(this.position);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
