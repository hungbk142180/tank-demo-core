package base.wall;

import base.GameObject;
import base.Vector2D;
import base.player.Tank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WallManagement extends ArrayList<GameObject> {
    public static  int [][] map=null;
    public static ArrayList<GameObject> arrayShovel;
   // public static String fileMap;
    public WallManagement(String fileMap){
        arrayShovel = new ArrayList<>();
        int mapWidth=0;
        int mapHeight=0;
       // int [][] map=null;
        int tileSize = 28;
        try {
             //fileMap = "assets\\maps\\map_2.txt";
         //   fileMap = "assets\\maps\\map_2.txt";
            BufferedReader br = new BufferedReader(new FileReader(fileMap));
            String x,y;
            x = br.readLine();
            y = br.readLine();
            mapWidth = Integer.parseInt(x);
            mapHeight = Integer.parseInt(y);
            map = new int[mapHeight][mapWidth];
            String delimeter = " ";
            for(int row =0; row < mapHeight ;row++){
                String line = br.readLine();
                String[] tokens = line.split(delimeter);
                for(int col = 0;col<mapWidth;col++){
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


            for (int row = 0; row < mapHeight; row++) {
                for (int col = 0; col < mapWidth; col++) {
                    int rc = map[row][col];
                if (rc == 0) {
                    Brick edee = (Brick) GameObject.recycle(Brick.class);
                    edee.destroy();
                }
                    if (rc == 1) {
                        Brick brick = (Brick) GameObject.recycle(Brick.class);
                        brick.position = new Vector2D(col * tileSize+14,row * tileSize+14);
                        add(brick);
                    }
                    if (rc == 2) {
                        Stone stone = (Stone)GameObject.recycle(Stone.class);
                        stone.position = new Vector2D(col * tileSize+14,row * tileSize+14);
                        add(stone);
                    }
                    if (rc == 3) {
                        Forest forest = (Forest)GameObject.recycle(Forest.class);
                        forest.position = new Vector2D(col * tileSize+14,row * tileSize+14);
                        add(forest);
                    }
                    if (rc == 5) {
                        Water water = (Water)GameObject.recycle(Water.class);
                        water.position = new Vector2D(col * tileSize+14,row * tileSize+14);
                        add(water);
                    }
                    if(rc == -1){
                        Brick brick = (Brick) GameObject.recycle(Brick.class);
                        brick.position = new Vector2D(col * tileSize+14,row * tileSize+14);
                        add(brick);
                        this.arrayShovel.add(brick);
                    }

                    // g.fillRect( col * tileSize,  row * tileSize, tileSize, tileSize);
                }
            }




    }
}
