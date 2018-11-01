package base.enemy;

import base.*;
import base.action.*;
import base.counter.FrameCounter;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.Tank;
import base.renderer.AnimationRenderer;
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
//    static boolean[] way = new boolean[]{false,false,false,false};//up down left right
    int way = 4; // 1-up, 2-down, 3-left, 4-right
    FrameCounter moveCounter;
    Random random = new Random();
    int enemyMoveX;
    int enemyMoveY;
    WallManagement arr;

    public Enemy() {
        super();
        arr = new WallManagement();
        this.position = new Vector2D(200, 100);
        this.moveCounter = new FrameCounter(41);
        this.defineAction();
        this.enemyMoveX = 0;
        this.enemyMoveY = 0;
    }

    void defineAction(){
        ActionWait actionWait = new ActionWait(20);
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
        //System.out.println(Setting.ENEMY_MOVE + " " + this.position.x);
    }
    private boolean checkIntersects() {
        Tank tank = (Tank)GameObject.intersect(Tank.class, this);
        Enemy enemy = (Enemy)GameObject.intersect(Enemy.class, this);
        return   tank != null || enemy != null;
    }

    private boolean checkIntersectsWall(){
        for (GameObject i :
                arr) {
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
        if (!this.checkIntersects() || !this.checkIntersectsWall()) {
            this.currentX = this.position.x;
            this.currentY = this.position.y;
        }
        this.action.run(this);
        if (this.checkIntersects()|| this.checkIntersectsWall() ||
                this.position.x-Settings.WAY_SIZE <=0 ||
                this.position.x+Settings.WAY_SIZE >Settings.SCREEN_WIDHT ||
                this.position.y-Settings.WAY_SIZE <=0 ||
                this.position.y+Settings.WAY_SIZE >Settings.SCREEN_HEIGHT
        ) {
            this.position.x = this.currentX;
            this.position.y = this.currentY;
        }
    }

    public void fire() {
        EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
        if(this.way == 0) {
            bullet.velocity.set(0, -8);
            bullet.position.set(this.position.x, this.position.y - (float) Settings.WAY_SIZE);
        }
        else if(this.way == 1) {
            bullet.velocity.set(0, 8);
            bullet.position.set(this.position.x, this.position.y + (float) Settings.WAY_SIZE);
        }
        else if(this.way == 2) {
            bullet.velocity.set(-8, 0);
            bullet.position.set(this.position.x -(float) Settings.WAY_SIZE , this.position.y);
        }
        else if(this.way == 3) {
            bullet.velocity.set(8, 0);
            bullet.position.set(this.position.x + (float) Settings.WAY_SIZE, this.position.y);
        }
    }

    public void takeDamage(int damage) {

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
