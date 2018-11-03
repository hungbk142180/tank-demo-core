

package base.enemy;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.PlayBullet;
import base.player.Tank;
import base.renderer.SingleImageRenderer;
import base.wall.Brick;
import base.wall.Stone;
import tklibs.SpriteUtils;

import java.util.ArrayList;

public class EnemyBullet extends GameObject implements Physics {
    BoxCollider collider;
    Vector2D velocity;
    public int damage;

    public EnemyBullet() {
        this.renderer = new SingleImageRenderer(SpriteUtils.loadImage("assets/bullets/blue.png"));
        this.position = new Vector2D(0.0F, 0.0F);
        this.collider = new BoxCollider(16, 16);
        this.velocity = new Vector2D(0.0F, 0.0F);
        this.damage = 4;
    }

    public void run() {
        if (this.isActive) {
            this.position.addThis(this.velocity);
        }
        Tank tank = (Tank)GameObject.intersect(Tank.class, this);
        PlayBullet playBullet = (PlayBullet)GameObject.intersect(PlayBullet.class, this);
        ArrayList<Brick> bricks = GameObject.intersectManyItems(Brick.class, this);
        ArrayList<Stone> stones = GameObject.intersectManyItems(Stone.class, this);
        if(bricks.size() > 0){
            for(Brick i : bricks){
                i.destroy();
                this.destroy();
            }

        }

        if(stones.size() > 0){
            for(Stone i : stones){
                this.destroy();
            }

        }

        if (tank != null) {
            tank.takeDamage(this.damage);
            this.destroy();
        }
        if(playBullet != null){
            this.destroy();
            playBullet.destroy();

        }

        if(this.position.y < 0 || this.position.y > Settings.SCREEN_HEIGHT ||
            this.position.x < 0 || this.position.x > Settings.SCREEN_WIDHT) {
            this.destroy();
            return;
        }
    }


    private void hitEnemy() {
        this.destroy();
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            this.destroy();
        }
    }

    public BoxCollider getBoxCollider() {
        return this.collider;
    }

}
