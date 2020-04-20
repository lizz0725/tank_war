package com.msb;

import com.msb.frame.Dir;
import com.msb.frame.Group;
import com.msb.frame.Tank;
import com.msb.frame.TankFrame;

public class Main {


    private static final int MAX = 5;

    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        for (int i = 0; i < MAX; i++) {
            frame.tanks.add(new Tank(i*80, 200, Dir.DOWN,Group.BAD,frame));
        }
        while (true){
            Thread.sleep(50L);
            frame.repaint();
        }
    }
}
