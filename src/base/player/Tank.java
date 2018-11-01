

package base.player;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.counter.FrameCounter;
import base.enemy.Enemy;
import base.event.KeyEventPress;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.SingleImageRenderer;
import base.wall.*;

import java.awt.image.BufferedImage;

import tklibs.SpriteUtils;

public class Tank extends GameObject implements Physics {
    FrameCounter fireCounter;
    BoxCollider collider;
    Boolean isDead;
    Vector2D velocity;
    float currentX = 0.0F;
    float currentY = 0.0F;
    boolean[] way = new boolean[]{false,false,false,false};//up down left right
    WallManagement arr;
    FrameCounter moveCounter;

    public Tank() {
        arr= new WallManagement( );;
        BufferedImage image = SpriteUtils.loadImage("assets/tank_image/tank2.PNG");
        this.position = new Vector2D((float)Settings.START_PLAYER_POSITION_X, (float)Settings.START_PLAYER_POSITION_Y);
        this.renderer = new SingleImageRenderer(image);
        this.velocity = new Vector2D(0.0F, 0.0F);
        this.fireCounter = new FrameCounter(10);
        this.collider = new BoxCollider(52, 52);
        this.moveCounter = new FrameCounter(6);
    }

    public void run() {


        if (!this.checkIntersects() || !this.checkIntersectsWall()) {
            this.currentX = this.position.x;
            this.currentY = this.position.y;
        }
        //
        boolean moveCounterRun = this.moveCounter.run();
        if (KeyEventPress.isUpPress && moveCounterRun) {
            BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/tank2.PNG");
            ((SingleImageRenderer) this.renderer).image = imageUp;
            this.position.addThis(0.0F, -4.0F);
            this.way=new boolean[]{true,false,false,false};
            this.moveCounter.reset();
        } else if (KeyEventPress.isDownPress && moveCounterRun) {
            BufferedImage imageDown = SpriteUtils.loadImage("assets/tank_image/tank2_down.PNG");
            ((SingleImageRenderer) this.renderer).image = imageDown;
            this.position.addThis(0.0F, 4.0F);
            this.way=new boolean[]{false,true,false,false};
            this.moveCounter.reset();
        } else if (KeyEventPress.isLeftPress && moveCounterRun) {
            BufferedImage imageLeft = SpriteUtils.loadImage("assets/tank_image/tank2_left.PNG");
            ((SingleImageRenderer) this.renderer).image = imageLeft;
            this.position.addThis(-4.0F, 0.0F);
            this.way=new boolean[]{false,false,true,false};
            this.moveCounter.reset();
        } else if (KeyEventPress.isRightPress && moveCounterRun) {
            BufferedImage imageRight = SpriteUtils.loadImage("assets/tank_image/tank2_right.PNG");
            ((SingleImageRenderer) this.renderer).image = imageRight;
            this.position.addThis(4.0F, 0.0F);
            this.way=new boolean[]{false,false,false,true};
            this.moveCounter.reset();
        }

        if (this.way[0] && moveCounterRun == false) {
            this.position.addThis(0,-4);
        }
        else if(this.way[1] && moveCounterRun == false) {
            this.position.addThis(0,4);
        }
        else if(this.way[2] && moveCounterRun == false) {
            this.position.addThis(-4,0);
        }
        else if(this.way[3] && moveCounterRun == false) {
            this.position.addThis(4,0);
        }

        boolean fireCounterRun = this.fireCounter.run();
        if (KeyEventPress.isFirePress && fireCounterRun) {
            this.fire();
        }
        this.position.addThis(this.velocity);

        if (this.checkIntersects()|| this.checkIntersectsWall()) {
            this.position.x = this.currentX;
            this.position.y = this.currentY;
        }
    }

    private boolean checkIntersects() {
        Enemy enemy = (Enemy)GameObject.intersect(Enemy.class, this);
        return   enemy != null;
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

    private void fire() {
        PlayBulletType1 bullet = (PlayBulletType1) GameObject.recycle(PlayBulletType1.class);

        if(this.way[0]) {
            bullet.velocity.set(0, -8);
            bullet.position.set(this.position.x, this.position.y - (float) Settings.WAY_SIZE);
        }
        else if(this.way[1]) {
            bullet.velocity.set(0, 8);
            bullet.position.set(this.position.x, this.position.y + (float) Settings.WAY_SIZE);
        }
        else if(this.way[2]) {
            bullet.velocity.set(-8, 0);
            bullet.position.set(this.position.x -(float) Settings.WAY_SIZE , this.position.y);
        }
        else if(this.way[3]) {
            bullet.velocity.set(8, 0);
            bullet.position.set(this.position.x + (float) Settings.WAY_SIZE, this.position.y);
        }
        else {
            bullet.velocity.set(0, -10);
            bullet.position.set(this.position.x, this.position.y - (float) Settings.WAY_SIZE);
        }
            this.fireCounter.reset();
    }

    public void setPositionBullet(){

    }

    public void move(int velocityX, int velocityY) {
        this.velocity.addThis((float)velocityX, (float)velocityY);
        this.velocity.set(this.clamp(this.velocity.x, -3.0F, 3.0F), this.clamp(this.velocity.y, -3.0F, 3.0F));
    }

    public float clamp(float number, float min, float max) {
        return number < min ? min : (number > max ? max : number);
    }

    public void takeDamage(int damage) {
        this.destroy();
    }

    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
