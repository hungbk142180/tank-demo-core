package base.renderer;

import base.GameObject;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SingleImageRenderer extends Renderer {
    public BufferedImage image;

    public SingleImageRenderer(String url) {
        this.image = SpriteUtils.loadImage(url);
    }

    public SingleImageRenderer(BufferedImage image) {
        this.image = image;
    }
    @Override
    public void render(Graphics g, GameObject master) {
        double x = master.position.x - image.getWidth() * master.anchor.x;
        double y = master.position.y - image.getHeight() * master.anchor.y;
        g.drawImage(this.image, (int)x, (int)y, null);
       //g.drawImage(this.image,(int)master.position.x,(int)master.position.y,null);
    }
}
