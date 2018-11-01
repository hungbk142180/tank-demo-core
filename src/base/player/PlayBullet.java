//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package base.player;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.enemy.Enemy;
import base.enemy.EnemyBullet;
import base.physics.BoxCollider;
import base.physics.Physics;

public class PlayBullet extends GameObject implements Physics {
    public BoxCollider collider;
    Vector2D velocity;
    int damage;

    public PlayBullet() {
        this.position = new Vector2D(0.0F, 0.0F);
        this.velocity = new Vector2D(0.0F, 0.0F);
    }

    public void run() {
        Enemy enemy = (Enemy)GameObject.intersect(Enemy.class, this);

        if (enemy != null) {
            enemy.takeDamage(this.damage);
            this.hitEnemy();
        } else if (this.position.y < 0 || this.position.y > Settings.SCREEN_HEIGHT ||
                this.position.x < 0 || this.position.x > Settings.SCREEN_WIDHT) {
            this.destroy();
        } else if (this.isActive == true) {
            this.position.addThis(this.velocity);
        }

    }

    public void hitEnemy() {
    }

    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
