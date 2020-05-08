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

    //public static final TankFrame INSTANCE = new TankFrame();
    Tank myTank = new Tank(200,200,Dir.DOWN,Group.GOOD,this);

    List<Bullet> bullets = new ArrayList<>();
    public List<Tank> tanks = new ArrayList<>();
    //public List<Explode> explodes = new ArrayList<>();

    public final int GAME_WIDTH = 800,GAME_HEIGHT = 600;


    /**
     * 1.子弹与坦克碰撞后(碰撞:Rectangle.intersects()), 子弹消失,坦克消失.坦克消失后,移除.防止内存泄漏
     * 2.坦克数量计数器
     * 3.敌方坦克发射子弹,并且新增炮弹,子弹的分组   ------ 已完成
     *
     * todo 4-1 ~ 4-3
     * 1.完善爆炸,碰撞时也需要爆炸 .  同样可能存在多个爆炸 使用List
     * 2.坦克图片优化, 好坦克与坏坦克分开
     * 3.新增ImageUtil旋转图片
     * 4.敌方坦克,随机移动,随机发射子弹    随机移动已完成,边界范围待完成
     * 5.随机移动的频率降低
     * 6.爆炸的位置在坦克的中心
     * 7.坦克好坏显示要不一致
     * 8.边界检测, 达到边界值时,将 x,y设为边界值
     * 9.Rectangle 对象内存泄漏  思路, 每个bullet 和 tank中 new一个Rectangle,构造器中初始化
     *
     * 10.新增配置文件与配置文件读取类 Properties
     *
     * todo 5-1 ~ 5-3
     * 1.单例模式  + 策略模式
     */

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
        g.drawString("子弹的数量:" + bullets.size(),10,60);
        g.drawString("敌方坦克的数量:" + tanks.size(),10,80);
        //g.drawString("explodes" + explodes.size(), 10, 100);
        g.setColor(color);

        myTank.paint(g);

        //迭代所有的子弹,敌方坦克.判断是否碰撞,如果碰撞,则删除子弹与坦克
        bullets.forEach(bullet ->{
            tanks.forEach(tank ->{
                bullet.collideWith(tank);
            });
        });

        //移除失效的子弹
        Iterator<Bullet> iterator = bullets.iterator();
        while(iterator.hasNext()){
            Bullet bullet = iterator.next();
            if (!bullet.isAlive()){
                iterator.remove();
            }
            bullet.paint(g);
        }
        //移除失效的坦克
        Iterator<Tank> tankIterator = tanks.iterator();
        while(tankIterator.hasNext()){
            Tank tank = tankIterator.next();
            if (!tank.isAlive()){
                tankIterator.remove();
            }
            tank.paint(g);
        }

        //爆炸
//        for (int i = 0; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }

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
