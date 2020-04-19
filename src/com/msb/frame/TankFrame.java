package com.msb.frame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200,200,Dir.DOWN,this);

    List<Bullet> bullets = new ArrayList<>();

    public final int GAME_WIDTH = 800,GAME_HEIGHT = 600;

    public TankFrame() {
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setTitle("坦克大战");
        setResizable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new MyKeyListener());
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    @Override
    public void paint(Graphics g){
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawString("字段的数量:" + bullets.size(),10,60);
        g.setColor(color);

        myTank.paint(g);
        //移除失效的子弹
        Iterator<Bullet> iterator = bullets.iterator();
        while(iterator.hasNext()){
            Bullet bullet = iterator.next();
            if (!bullet.isAlive()){
                iterator.remove();
            }
            bullet.paint(g);
        }
//        bullets.forEach(bullet -> bullet.paint(g));
     }



    boolean bU = false;
    boolean bD = false;
    boolean bL = false;
    boolean bR = false;



    class MyKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                default:
                    break;
            }
            setMainDir();
        }


        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainDir();
        }



        private void setMainDir() {
            if (!bU && !bD && !bL && !bR){
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                if (bU) myTank.setDir(Dir.UP);
                if (bD) myTank.setDir(Dir.DOWN);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bR) myTank.setDir(Dir.RIGHT);
            }
        }
    }
}
