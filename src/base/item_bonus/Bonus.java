package base.item_bonus;

import base.GameObject;
import base.counter.FrameCounter;
import base.physics.BoxCollider;
import base.physics.Physics;

import java.awt.*;

public class Bonus extends GameObject implements Physics {
    FrameCounter blinkCounter;
    BoxCollider collider;
    int alive = 500;
    public Bonus() {
        this.blinkCounter = new FrameCounter(15);
    }
    @Override
    public void run() {
        super.run();
        this.alive--;
        if (this.alive <= 0) {
            this.destroy();
        }
    }
    public void render(Graphics g) {
        if(this.blinkCounter.run()) {
            super.render(g);
            this.blinkCounter.reset();
        }
    }
    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
