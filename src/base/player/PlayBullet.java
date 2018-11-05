//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package base.player;

import base.GameObject;
import base.Settings;
import base.SoundManage;
import base.Vector2D;
import base.enemy.Enemy;
import base.enemy.EnemyBullet;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.scene.SceneManager;
import base.scene.SceneStage2;
import base.scene.gameoverScen.GameoverScene;
import base.wall.Brick;
import base.wall.Eagle;
import base.wall.Stone;

import java.util.ArrayList;

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
        ArrayList<Brick> bricks = GameObject.intersectManyItems(Brick.class, this);
        ArrayList<Stone> stones = GameObject.intersectManyItems(Stone.class, this);
        if(bricks.size() > 0){
            for(Brick i : bricks){
                i.destroy();
                SoundManage.playSound("player/hit_brick.wav");
                this.destroy();
            }
        }

        if(stones.size() > 0){
            for(Stone i : stones){

                //i.destroy();

//                i.destroy();
                SoundManage.playSound("player/hit_wall.wav");

                this.destroy();
            }
        }

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


        if (enemyBullet != null) {
            enemyBullet.takeDamage(this.damage);
            this.destroy();
        }
        if(this.checkIntersectsEagle()){//check aegle
            SceneManager.signNewScene(new GameoverScene());
        }

    }
    private boolean checkIntersectsEagle(){// check  eagle
        Eagle eagle = GameObject.intersect(Eagle.class,this);
        return eagle != null;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
