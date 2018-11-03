package base.scene;

import base.Background;
import base.GameObject;
import base.Settings;

import base.SoundManage;
import base.enemy.*;

import base.player.Tank;
import base.wall.Wall;

import java.util.ArrayList;

public class SceneStage1 extends Scene {// khoi tao doi tuong o day


    @Override
    public void destroy() {
        GameObject.clearAll();
    }

    @Override
    public void init() {

        //GameObject.recycle(Background.class);
        this.tank = GameObject.recycle(Tank.class);
        tank.position.set(9 * Settings.WAY_SIZE, 25 * Settings.WAY_SIZE);

        this.enemyType1 = GameObject.recycle(EnemyType1.class);
        enemyType1.position.set(Settings.WAY_SIZE * 5, Settings.WAY_SIZE * 5);
        EnemySummoner.enemyBornManage.add(enemyType1);

        this.enemyType2 = GameObject.recycle(EnemyType2.class);
        enemyType2.position.set(Settings.WAY_SIZE * 21, Settings.WAY_SIZE * 5);
        EnemySummoner.enemyBornManage.add(enemyType2);

        this.enemyType3 = GameObject.recycle(EnemyType3.class);
        enemyType3.position.set(Settings.WAY_SIZE * 10, Settings.WAY_SIZE * 5);
        EnemySummoner.enemyBornManage.add(enemyType3);

        this.enemySummoner = GameObject.recycle(EnemySummoner.class);

        String[] fileNames = {
                "level_start.wav",
                "enemy/enemy-explosion.wav",
                "enemy/hit_armor.wav",
                "player/player_explode.wav",
                "player/hit_brick.wav",
                "player/hit_wall.wav",
                "player/item-collect.wav"
        };
        SoundManage.loadSounds(fileNames);
        SoundManage.playSound("level_start.wav");
        
//        System.out.println("tank : "+tank.position);
       /*for(int i =0 ; i< 2 ; i++){
           Wall wall = GameObject.recycle(Wall.class);
           wall.position.set(154 + Settings.WAY_SIZE, 70 + Settings.WAY_SIZE);// 168 - 84 : meo hiu sao phai tru di 0.5.wayzize
       }*/

//        Wall wall = GameObject.recycle(Wall.class);
//        wall.position.set(154,70);// 168 - 84 : meo hiu sao phai tru di 0.5.wayzize
//
//        Wall wall1 = GameObject.recycle(Wall.class);
//        wall1.position.set(154+Settings.WAY_SIZE,70);
//
//        Wall wall2 = GameObject.recycle(Wall.class);
//        wall2.position.set(154,70+Settings.WAY_SIZE);
//
//        Wall wall3 = GameObject.recycle(Wall.class);
//        wall3.position.set(154+Settings.WAY_SIZE,70+Settings.WAY_SIZE);

    }
}
