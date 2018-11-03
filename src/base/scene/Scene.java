package base.scene;

import base.enemy.*;
import base.item_bonus.Boom;
import base.item_bonus.Clock;
import base.item_bonus.Gun;
import base.item_bonus.Shovel;
import base.player.Tank;

public abstract class Scene {
   // public Player player;
  //  public SnackManager snackManager;

    public Tank tank;
    public static Boom boom;
    public static Gun gun;
    public static Shovel shovel;
    public static Clock clock;

    public EnemyType1 enemyType1;
    public EnemyType2 enemyType2;
    public EnemyType3 enemyType3;
    public EnemyType4 enemyType4;
    public EnemySummoner enemySummoner;

    public abstract void destroy();

    public abstract void init();
}
