package base.scene;

import base.enemy.EnemyType1;
import base.player.Tank;

public abstract class Scene {
   // public Player player;
  //  public SnackManager snackManager;

    public Tank tank;
    public EnemyType1 enemyType1,enemyType2;
    public abstract void destroy();

    public abstract void init();
}
