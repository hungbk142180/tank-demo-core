package base.scene;

import base.enemy.*;
import base.player.Tank;

public abstract class Scene {
   // public Player player;
  //  public SnackManager snackManager;

    public Tank tank;

    public EnemyType1 enemyType1;
    public EnemyType2 enemyType2;
    public EnemyType3 enemyType3;
    public EnemyType4 enemyType4;
    public EnemySummoner enemySummoner;

    public abstract void destroy();

    public abstract void init();
}
