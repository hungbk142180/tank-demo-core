package base.player;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.counter.FrameCounter;
import base.enemy.Enemy;
import base.SoundManage;
import base.enemy.EnemySummoner;
import base.event.KeyEventPress;
import base.item_bonus.Boom;
import base.item_bonus.Clock;
import base.item_bonus.Gun;
import base.item_bonus.Shovel;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.SingleImageRenderer;
import base.scene.Scene;
import base.scene.SceneStage1;
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
 //  public static WallManagement arr;
    FrameCounter moveCounter;
    int countTimeClock;
    int countTimeShovel;
    Boolean checkTimeShovel = false;

    int bulletType;

    public Tank() {
        // arr= new WallManagement("assets\\maps\\map_2.txt" );
        BufferedImage image = SpriteUtils.loadImage("assets/tank_image/selecttank_up.png");
        this.position = new Vector2D((float)Settings.START_PLAYER_POSITION_X, (float)Settings.START_PLAYER_POSITION_Y);
        this.renderer = new SingleImageRenderer(image);
        this.velocity = new Vector2D(0.0F, 0.0F);
        this.fireCounter = new FrameCounter(10);
        this.collider = new BoxCollider(54, 54);
        this.moveCounter = new FrameCounter(6);
        this.bulletType = 1;

    }

    public void run() {
        if(this.checkIntersectsBonus()){//clock
            SoundManage.playSound("pause.wav");
            Scene.clock.destroy();
            Enemy.checkClock = false;
        }
        if(!Enemy.checkClock){//clock
            countTimeClock++;
            if(countTimeClock == 121){
                Enemy.checkClock = true;
                countTimeClock = 0;
            }
        }
        if(this.checkIntersectsShovelBonus()){//shovel
            SoundManage.playSound("player/item-collect.wav");
            checkTimeShovel = true;
            Scene.shovel.destroy();
            for (int row = 0; row < 26; row++) {
                for (int col = 0; col < 26; col++) {
                    int rc = WallManagement.map[row][col];
                    if(rc == -1){
                        for(GameObject shovel : WallManagement.arrayShovel){
                            BufferedImage newImageShovel = SpriteUtils.loadImage("assets/maps/item_built_map/ConcreteOrig.png");
                            ((SingleImageRenderer)shovel.renderer).image = newImageShovel;
                        }

                    }
                }
            }
        }
        if(checkTimeShovel){// shovel
            countTimeShovel++;
            if(countTimeShovel == 60){
                for(GameObject shovel : WallManagement.arrayShovel){
                    BufferedImage newImageBrick = SpriteUtils.loadImage("assets/maps/item_built_map/BricksOrig.png");
                    ((SingleImageRenderer)shovel.renderer).image = newImageBrick;
                }
                countTimeShovel = 0;
            }
        }

        if(this.checkIntersectsBoomBonus()){// boom
            SoundManage.playSound("enemy/enemy-explosion-big.wav");
            Scene.boom.destroy();
            for(Enemy enemy : EnemySummoner.enemyBornManage){
                if(enemy.isActive){
                    enemy.destroy();
                }
            }
        }

        if(this.checkIntersectsGunBonus()){
            SoundManage.playSound("player/powerup.wav");
            Scene.gun.destroy();
            this.bulletType = 2;
        }
        if (!this.checkIntersects() || !this.checkIntersectsWall()) {
            this.currentX = this.position.x;
            this.currentY = this.position.y;
        }

        boolean moveCounterRun = this.moveCounter.run();
        if (KeyEventPress.isUpPress && moveCounterRun) {
            BufferedImage imageUp = SpriteUtils.loadImage("assets/tank_image/selecttank_up.png");
            ((SingleImageRenderer) this.renderer).image = imageUp;
            setPosition();

            this.position.addThis(0.0F, -4.0F);
            this.way=new boolean[]{true,false,false,false};
            this.moveCounter.reset();
        } else if (KeyEventPress.isDownPress && moveCounterRun) {
            BufferedImage imageDown = SpriteUtils.loadImage("assets/tank_image/selecttank_down.png");
            ((SingleImageRenderer) this.renderer).image = imageDown;
            setPosition();

            this.position.addThis(0.0F, 4.0F);
            this.way=new boolean[]{false,true,false,false};
            this.moveCounter.reset();
        } else if (KeyEventPress.isLeftPress && moveCounterRun) {
            BufferedImage imageLeft = SpriteUtils.loadImage("assets/tank_image/selecttank_left.png");
            ((SingleImageRenderer) this.renderer).image = imageLeft;
            setPosition();
            this.position.addThis(-4.0F, 0.0F);
            this.way=new boolean[]{false,false,true,false};
            this.moveCounter.reset();
        } else if (KeyEventPress.isRightPress && moveCounterRun) {
            BufferedImage imageRight = SpriteUtils.loadImage("assets/tank_image/selecttank_right.png");
            ((SingleImageRenderer) this.renderer).image = imageRight;
            setPosition();

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
            SoundManage.playSound("player/player_shot.wav");
            this.fire();
        }
        this.position.addThis(this.velocity);

        if (    this.checkIntersects()||
                this.checkIntersectsWall() ||
                this.position.x-Settings.WAY_SIZE <0 ||
                this.position.x+Settings.WAY_SIZE >Settings.SCREEN_WIDHT ||
                this.position.y-Settings.WAY_SIZE <0 ||
                this.position.y+Settings.WAY_SIZE>Settings.SCREEN_HEIGHT
        ) {
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

    private boolean checkIntersectsBonus(){//Clock bonus
        Clock clock = (Clock) GameObject.intersect(Clock.class,this);
        return clock != null;
    }

    private boolean checkIntersectsShovelBonus(){// shovel bonus
        Shovel shovel = GameObject.intersect(Shovel.class,this);
        return shovel != null;
    }

    private boolean checkIntersectsBoomBonus(){// boom bonus
        Boom boom = GameObject.intersect(Boom.class,this);
        return boom != null;
    }

    private boolean checkIntersectsGunBonus(){// gun bonus
        Gun gun = GameObject.intersect(Gun.class,this);
        return gun != null;
    }

    private void fire() {
        if (this.bulletType == 1) {
            PlayerBulletType1 bullet = (PlayerBulletType1) GameObject.recycle(PlayerBulletType1.class);
            if(this.way[0]) {
                bullet.velocity.set(0, -12);
                bullet.position.set(this.position.x, this.position.y - (float) Settings.WAY_SIZE);
            }
            else if(this.way[1]) {
                bullet.velocity.set(0, 12);
                bullet.position.set(this.position.x, this.position.y + (float) Settings.WAY_SIZE);
            }
            else if(this.way[2]) {
                bullet.velocity.set(-12, 0);
                bullet.position.set(this.position.x -(float) Settings.WAY_SIZE , this.position.y);
            }
            else if(this.way[3]) {
                bullet.velocity.set(12, 0);
                bullet.position.set(this.position.x + (float) Settings.WAY_SIZE, this.position.y);
            }
            else {
                bullet.velocity.set(0, -12);
                bullet.position.set(this.position.x, this.position.y - (float) Settings.WAY_SIZE);
            }
        }

        else {
            PlayerBulletType2 bullet = (PlayerBulletType2) GameObject.recycle(PlayerBulletType2.class);
            if(this.way[0]) {
                bullet.velocity.set(0, -12);
                bullet.position.set(this.position.x, this.position.y - (float) Settings.WAY_SIZE);
                bullet.renderer = new SingleImageRenderer("assets/bullets/bulletU.gif");
            }
            else if(this.way[1]) {
                bullet.velocity.set(0, 12);
                bullet.position.set(this.position.x, this.position.y + (float) Settings.WAY_SIZE);
                bullet.renderer = new SingleImageRenderer("assets/bullets/bulletD.gif");
            }
            else if(this.way[2]) {
                bullet.velocity.set(-12, 0);
                bullet.position.set(this.position.x -(float) Settings.WAY_SIZE , this.position.y);
                bullet.renderer = new SingleImageRenderer("assets/bullets/bulletL.gif");
            }
            else if(this.way[3]) {
                bullet.velocity.set(12, 0);
                bullet.position.set(this.position.x + (float) Settings.WAY_SIZE, this.position.y);
                bullet.renderer = new SingleImageRenderer("assets/bullets/bulletR.gif");
            }
            else {
                bullet.velocity.set(0, -12);
                bullet.position.set(this.position.x, this.position.y - (float) Settings.WAY_SIZE);
                bullet.renderer = new SingleImageRenderer("assets/bullets/bulletU.gif");
            }
        }
        this.fireCounter.reset();
    }


    public void setPosition(){
        if(this.position.x < 0){
            this.position.set(200, 300);
        }
        else if(this.position.x > Settings.SCREEN_WIDHT){
            this.position.set(200,300);
        }
    }

    public void takeDamage(int damage) {
        SoundManage.playSound("player/player_explode.wav");
        this.destroy();
        base.player.Explosion explosion = GameObject.recycle(base.player.Explosion.class);
        explosion.position.set(this.position);
    }

    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
