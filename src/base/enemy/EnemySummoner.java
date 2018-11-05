package base.enemy;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.scene.Scene;
import base.scene.SceneManager;
import base.scene.SceneStage2;
import base.scene.StartGameStage1.SceneImageStage2;
import base.scene.gameoverScen.GameoverScene;
import base.scene.welcomeScene.WelcomeScene;

import java.util.ArrayList;

public class EnemySummoner extends GameObject implements Physics {
    public static ArrayList<Enemy> enemyBornManage = new ArrayList<>();

    BoxCollider collider;
    public static int enemyLeft;
    public static int enemyNow;
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
        Enemy enemy = (Enemy) GameObject.intersect(Enemy.class, this);
        if (enemy == null && enemyNow < 3) {
            this.spawn();
        }
//        System.out.println(enemyLeft);
//        System.out.println(enemyNow);
        if (enemyLeft <= 0 && enemyNow <= 0 && Scene.sceneLeft > 0) {

            SceneManager.signNewScene(new SceneImageStage2());
        }
        if (enemyLeft <= 0 && enemyNow <= 0 && Scene.sceneLeft == 0) {
            SceneManager.signNewScene(new WelcomeScene());
            //Thay = Scene Winner!!!
        }
    }

    public void spawn(){
        if (enemyLeft >= 2) {
            this.enemyType1 = GameObject.recycle(EnemyType1.class);
            enemyType1.position.set(this.position.x, this.position.y);
            enemyLeft --;
            EnemySummoner.enemyBornManage.add(enemyType1);
            enemyNow++;
        } else if (enemyLeft >= 1) {
            this.enemyType4 = GameObject.recycle(EnemyType4.class);
            enemyType4.position.set(this.position.x, this.position.y);
            enemyLeft --;
            EnemySummoner.enemyBornManage.add(enemyType4);
            enemyNow++;
        } else {
            return;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
