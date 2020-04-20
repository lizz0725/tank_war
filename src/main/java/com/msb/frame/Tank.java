package com.msb.frame;

import java.awt.*;

public class Tank {

    private int x,y;
    private final int SPEED = 5;
    private final int WIDTH = ResourceManage.tankU.getWidth(),
            HEIGHT = ResourceManage.tankU.getHeight();
    private boolean moving = false;
    private boolean alive  = true;
    private Group group = null;
    Dir dir = null;
    TankFrame frame = null;



    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.frame = tf;
    }



    public void paint(Graphics g) {
        switch (dir){
            case UP:
                g.drawImage(ResourceManage.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceManage.tankD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceManage.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManage.tankR,x,y,null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        if (!alive || !moving) return;
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

        this.fire();

    }

    public void fire() {
        frame.bullets.add(new Bullet(this.x + WIDTH/2,this.y + HEIGHT/2,this.dir,this.group,frame));
    }


    public void die() {
        this.alive = false;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Group getGroup() {
        return group;
    }


}
