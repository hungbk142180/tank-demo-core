package base;

import base.renderer.SingleImageRenderer;
import base.wall.Forest;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

public class Background extends GameObject {
//
//    private int tileSize;
//    private int[][] map;
//    private int mapWidth;
//    private int mapHeight;

//    public Background(){
//        tileSize = 28;
//        //String s = "assets\\maps\\testmap.txt";
//        String s = "assets\\maps\\map_2.txt";
//        this.tileSize = tileSize;
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(s));
//            String x,y;
//            x = br.readLine();
//            y = br.readLine();
//            mapWidth = Integer.parseInt(x);
//            mapHeight = Integer.parseInt(y);
//            map = new int[mapHeight][mapWidth];
//
//            String delimeter = " ";
//            for(int row =0; row < mapHeight ;row++){
//                String line = br.readLine();
//                String[] tokens = line.split(delimeter);
//                for(int col = 0;col<mapWidth;col++){
//                    map[row][col] = Integer.parseInt(tokens[col]);
//                }
//            }
//
//        }catch (Exception e){
//
//        }
//    }
//    @Override
//    public void render(Graphics g) {
//        g.setColor(Color.CYAN);
//        for(int i = 0; i < Settings.COL_COUNT; i++) {
//            g.drawLine(i * Settings.WAY_SIZE, 0
//                    , i * Settings.WAY_SIZE, Settings.SCREEN_HEIGHT);
//        }
//        for(int i = 0; i < Settings.ROW_COUNT; i++) {
//            g.drawLine(0, i * Settings.WAY_SIZE
//                    , Settings.SCREEN_WIDHT, i * Settings.WAY_SIZE);
//        }
//    }

//    @Override
//    public void render(Graphics g) {
//
//        super.render(g);
//        for (int row = 0; row < mapHeight; row++) {
//            for (int col = 0; col < mapWidth; col++) {
//                int rc = map[row][col];
////                if (rc == 0) {
////                    g.fillRect( col * tileSize,  row * tileSize, tileSize, tileSize);
////                   // g.setColor(Color.BLACK);
////                }
//                if (rc == 1) {
//                   // g.setColor(Color.blue);
//                    g.drawImage(imageGack, col * tileSize,row * tileSize,null);
//                }
//                if (rc == 2) {
//                    // g.setColor(Color.blue);
//                    g.drawImage(imageDa, col * tileSize,row * tileSize,null);
//                }
//                if (rc == 3) {
//                    // g.setColor(Color.blue);
//                    g.drawImage(imageRung, col * tileSize,row * tileSize,null);
//                }
//                if (rc == 5) {
//                    // g.setColor(Color.blue);
//                    g.drawImage(imageWater, col * tileSize,row * tileSize,null);
//                }
//
//               // g.fillRect( col * tileSize,  row * tileSize, tileSize, tileSize);
//            }
//        }
//    }

}
