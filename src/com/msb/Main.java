package com.msb;

import com.msb.frame.TankFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();
        while (true){
            Thread.sleep(50L);
            frame.repaint();
        }
    }
}
