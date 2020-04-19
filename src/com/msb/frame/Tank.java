package com.msb.frame;

import java.awt.*;

public class Tank {

    private int x,y;
    private final int SPEED = 10,WIDTH = 50,HEIGHT= 50;
    private boolean moving = false;
    Dir dir = null;
    TankFrame frame = null;



    public Tank(int x, int y, Dir dir,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        frame = tf;
    }



    public void paint(Graphics g) {
        System.out.println("paint");
        Color color = g.getColor();
        g.setColor(Color.orange);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(color);
        if (moving)
            move();
    }

    private void move() {
        switch (dir){
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;

        }
    }


    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        frame.bullets.add(new Bullet(this.x + WIDTH/2,this.y + HEIGHT/2,this.dir,frame));
    }

}
