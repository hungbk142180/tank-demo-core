package base.scene;

import base.Background;
import base.GameObject;
import base.Settings;
import base.enemy.Enemy;
import base.enemy.EnemyType1;
import base.player.Tank;
import base.wall.Wall;

import java.util.ArrayList;

public class SceneStage1 extends Scene {// khoi tao doi tuong o day
    public static ArrayList<Enemy> enemyBornManage = new ArrayList<>();
    @Override
    public void destroy() {
        GameObject.clearAll();
    }

    @Override
    public void init() {

        //GameObject.recycle(Background.class);
        this.tank = GameObject.recycle(Tank.class);
        tank.position.set(9* Settings.WAY_SIZE,25 * Settings.WAY_SIZE);

        this.enemyType1 = GameObject.recycle(EnemyType1.class);
        enemyType1.position.set(Settings.WAY_SIZE*5, Settings.WAY_SIZE*5);
        enemyType1.isLife =true;
        enemyBornManage.add(enemyType1);

        this.enemyType2 = GameObject.recycle(EnemyType1.class);
        enemyType2.position.set(Settings.WAY_SIZE*21, Settings.WAY_SIZE*5);
        enemyType2.isLife = true;
        enemyBornManage.add(enemyType2);

//        System.out.println("tank : "+tank.position);
       /*for(int i =0 ; i< 2 ; i++){
           Wall wall = GameObject.recycle(Wall.class);
           wall.position.set(154 + Settings.WAY_SIZE, 70 + Settings.WAY_SIZE);// 168 - 84 : meo hiu sao phai tru di 0.5.wayzize
       }*/
    }
}
