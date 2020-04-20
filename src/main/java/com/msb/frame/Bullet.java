package com.msb.frame;

import java.awt.*;

public class Bullet {

    private static final  int SPEED = 1;
    private final int WIDTH = ResourceManage.bulletU.getWidth(),
            HEIGHT = ResourceManage.bulletU.getHeight();
    private int x,y;
    private Dir dir;
    private boolean alive = true;
    private TankFrame tf;
    private Group group;


    public Bullet(int x, int y, Dir dir,Group group ,TankFrame tankFrame) {
        this.x = x - WIDTH/2;
        this.y = y - HEIGHT/2;
        this.dir = dir;
        this.group = group;
        this.tf = tankFrame;
    }


    public void paint(Graphics g) {
        switch (dir){
            case UP:
                g.drawImage(ResourceManage.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceManage.bulletD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceManage.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManage.bulletR,x,y,null);
                break;
            default:
                break;
        }

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

    public void collideWith(Tank tank) {
        //同一组的不处理
        if (this.group == tank.getGroup()){
            return;
        }
        Rectangle bulletRectangle = new Rectangle(this.x, this.y, this.WIDTH, this.HEIGHT);
        Rectangle tankRectangle = new Rectangle(tank.getX(), tank.getY(), tank.getWIDTH(), tank.getHEIGHT());
        //如果相撞
        if(this.alive && tank.isAlive() && bulletRectangle.intersects(tankRectangle)){
            this.die();
            tank.die();
        }

    }

    private void die() {
        this.alive = false;
    }
}
