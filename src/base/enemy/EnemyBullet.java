

package base.enemy;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.PlayBullet;
import base.player.Tank;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

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
        this.position.addThis(this.velocity);
        Tank tank = (Tank)GameObject.intersect(Tank.class, this);
        PlayBullet playBullet = (PlayBullet)GameObject.intersect(PlayBullet.class, this);
//        EnemyBullet enemyBullet = (EnemyBullet)GameObject.intersect(EnemyBullet.class, this);
        if (tank != null) {
            tank.takeDamage(this.damage);
            this.destroy();
        }
        if(playBullet != null){
            this.destroy();
        }

        if(this.position.y < 0 || this.position.y > Settings.SCREEN_HEIGHT ||
            this.position.x < 0 || this.position.x > Settings.SCREEN_WIDHT) {
            this.destroy();
            return;
        }
    }

    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
