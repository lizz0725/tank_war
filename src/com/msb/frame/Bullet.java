package com.msb.frame;

import java.awt.*;

public class Bullet {

    private static final  int SPEED = 5;
    private static final int WINDTH = 20,HEIGHT= 20;
    private int x,y;
    private Dir dir;
    private boolean alive = true;
    private TankFrame tf;


    public Bullet(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x - WINDTH/2;
        this.y = y - HEIGHT/2;
        this.dir = dir;
        tf = tankFrame;
    }


    public void paint(Graphics g) {
        System.out.println("paint");
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WINDTH,HEIGHT);
        g.setColor(color);

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
        //判断子弹是否失效
        if (x < 0 || y < 0 || x >= tf.GAME_WIDTH || y >= tf.GAME_HEIGHT){
            alive = false;
        }

    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
