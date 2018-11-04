package base.enemy;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.physics.BoxCollider;
import base.physics.Physics;

import java.util.ArrayList;

public class EnemySummoner extends GameObject implements Physics {
    public static ArrayList<Enemy> enemyBornManage = new ArrayList<>();

    BoxCollider collider;
    public static int enemyLeft = 3;
    public static int enemyNow = 3;
    public EnemyType1 enemyType1;
    public EnemyType2 enemyType2;
    public EnemyType3 enemyType3;
    public EnemyType4 enemyType4;

    public EnemySummoner() {
        this.collider = new BoxCollider(54, 54);
        this.position = new Vector2D(Settings.WAY_SIZE*1, Settings.WAY_SIZE*1);
    }

    @Override
    public void run() {
        Enemy enemy = (Enemy)GameObject.intersect(Enemy.class, this);
        if (enemy == null && enemyNow < 3) {
            this.spawn();
            enemyNow++;
        } else {
            return;
        }
    }

    public void spawn(){
        if (enemyLeft >= 2) {
            this.enemyType4 = GameObject.recycle(EnemyType4.class);
            enemyType4.position.set(this.position.x, this.position.y);
            enemyLeft --;
            EnemySummoner.enemyBornManage.add(enemyType4);
        } else if (enemyLeft >= 1) {
            this.enemyType4 = GameObject.recycle(EnemyType4.class);
            enemyType4.position.set(this.position.x, this.position.y);
            enemyLeft --;
            EnemySummoner.enemyBornManage.add(enemyType4);
        } else {
            return;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
