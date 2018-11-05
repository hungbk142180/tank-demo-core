package base.scene.gameoverScen;

import base.GameObject;
import base.enemy.EnemySummoner;
import base.scene.Scene;

public class GameoverScene extends Scene {
    @Override
    public void destroy() {
        GameObject.clearAll();
        EnemySummoner.enemyBornManage.clear();
    }

    @Override
    public void init() {
        GameObject.recycle(Banner.class);
    }
}