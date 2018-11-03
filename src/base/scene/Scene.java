package base.scene;

import base.enemy.*;
import base.player.Tank;

public abstract class Scene {
   // public Player player;
  //  public SnackManager snackManager;

    public Tank tank;
<<<<<<< HEAD
    public EnemyType1 enemyType1;
    public EnemyType2 enemyType2;
    public EnemyType3 enemyType3;
    public EnemyType4 enemyType4;
    public EnemySummoner enemySummoner;
=======
    public EnemyType1 enemyType1,enemyType2;
>>>>>>> e5c0ae9e2ba1640a42f56e2063caf5746d6c6636
    public abstract void destroy();

    public abstract void init();
}
