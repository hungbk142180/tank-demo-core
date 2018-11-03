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
import base.wall.Brick;
import base.wall.Stone;

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
        EnemyBullet enemyBullet = (EnemyBullet)GameObject.intersect(EnemyBullet.class, this);
        Brick brick = (Brick)GameObject.intersect(Brick.class, this);
        Stone stone = (Stone)GameObject.intersect(Stone.class, this);
        //xu ly va cham Enemy
        if (enemy != null) {
            enemy.takeDamage(this.damage);
            this.destroy();
        }

        if (this.position.y < 0 || this.position.y > Settings.SCREEN_HEIGHT ||
                this.position.x < 0 || this.position.x > Settings.SCREEN_WIDHT) {
            this.destroy();
        }

        if (this.isActive == true) {
            this.position.addThis(this.velocity);
        }
        //

        //xu ly va cham gach
        if (brick != null) {
            brick.destroy();
            this.destroy();
        }
        // xu ly va cham voi da
        if (stone != null) {
            stone.takeDamage(this.damage);
            this.destroy();
        }

        //xu ly va cham EnemyBullet
        if (enemyBullet != null) {
            enemyBullet.takeDamage(this.damage);
            this.destroy();
        }

//        if(this.checkIntersectBrick()){
//            this.destroy();
//            brick.destroy();
//        }
    }

//    private boolean checkIntersectBrick(){
//        Brick brick = (Brick) GameObject.intersect(Brick.class,this);
//        return brick != null;
//    }


    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
