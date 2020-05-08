package com.msb.frame;

import java.awt.*;
import java.util.Random;

public class Tank {

    private int x,y;
    private final int SPEED = 5;
    private final int WIDTH = ResourceManage.goodTankU.getWidth(),
            HEIGHT = ResourceManage.goodTankU.getHeight();
    private boolean moving = true;
    private boolean alive  = true;
    private Group group = null;
    Dir dir = null;
    TankFrame frame = null;
    Random random = new Random();
    //每次爆炸都要新new,会导致内存空间堆积
    Rectangle rect = new Rectangle();


    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.frame = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }



    public void paint(Graphics g) {
        switch (dir){
            case UP:
                if (Group.BAD == this.group){
                    g.drawImage(ResourceManage.badTankU,x,y,null);
                }else{
                    g.drawImage(ResourceManage.goodTankU,x,y,null);
                }
                break;
            case DOWN:
                if (Group.BAD == this.group){
                    g.drawImage(ResourceManage.badTankD,x,y,null);
                }else{
                    g.drawImage(ResourceManage.goodTankD,x,y,null);
                }
                break;
            case LEFT:
                if (Group.BAD == this.group){
                    g.drawImage(ResourceManage.badTankL,x,y,null);
                }else{
                    g.drawImage(ResourceManage.goodTankL,x,y,null);
                }
                break;
            case RIGHT:
                if (Group.BAD == this.group){
                    g.drawImage(ResourceManage.badTankR,x,y,null);
                }else{
                    g.drawImage(ResourceManage.goodTankR,x,y,null);
                }
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
        //坏坦克需要随机移动
        if (Group.BAD == this.group) {
            if (random.nextInt(100) > 95) {
                //随机改变方向
                this.dir = Dir.values()[random.nextInt(4)];
            }

        }
        //移动后判断坦克有没有出界,要考虑到坦克的边界.
        boundsCheck();

            //this.fire();

    }

    /**
     * 边界检测
     */
    private void boundsCheck() {
        if (this.x <= WIDTH / 2){
            this.x = WIDTH / 2;
        }
        if (this.x >= frame.GAME_WIDTH - WIDTH/2  -2){
            this.x = frame.GAME_WIDTH - WIDTH/2 -2;
        }
        if (this.y <= 28){
            this.y = 28;
        }
        if (this.y >= frame.GAME_HEIGHT - HEIGHT/2 -2){
            this.y = (frame.GAME_HEIGHT - HEIGHT/2 -2);
        }

    }

    public void fire() {
        frame.bullets.add(new Bullet(this.x + WIDTH/2,this.y + HEIGHT/2,this.dir,this.group,frame));
    }


    public void die() {
        this.alive = false;
//        int eX = this.getX() + WIDTH/2 - Explode.WIDTH/2;
//        int eY = this.getY() + HEIGHT/2 - Explode.HEIGHT/2;
//        this.frame.explodes.add(new Explode(eX, eY, this.frame));
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
