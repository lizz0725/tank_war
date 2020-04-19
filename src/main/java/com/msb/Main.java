package com.msb;

import com.msb.frame.TankFrame;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();
        while (true){
            Thread.sleep(50L);
            frame.repaint();
        }
    }
}
