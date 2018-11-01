package base.enemy;

import base.*;
import base.action.*;
import base.counter.FrameCounter;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject implements Physics {
    BoxCollider collider;
    Action action;
//    static boolean[] way = new boolean[]{false,false,false,false};//up down left right
    int way = 4; // 1-up, 2-down, 3-left, 4-right
    FrameCounter moveCounter;
    Random random = new Random();
    int enemyMoveX;
    int enemyMoveY;

    public Enemy() {
        super();
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

    @Override
    public void run() {
        this.action.run(this);
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
