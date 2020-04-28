package com.msb.frame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManage {

    public static BufferedImage badTankU,badTankD,badTankL,badTankR,goodTankU,goodTankD,goodTankL,goodTankR;
    public static BufferedImage bulletU,bulletD,bulletL,bulletR;
    public static BufferedImage[] explodes = new BufferedImage[16];


    static {
        try {
            //加载坏坦克图片
            badTankU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankD = ImageUtil.rotateImage(badTankU,180);
            badTankL = ImageUtil.rotateImage(badTankU,-90);
            badTankR = ImageUtil.rotateImage(badTankU,90);

            //加载好坦克图片
            goodTankU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankD = ImageUtil.rotateImage(goodTankU,180);
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);
            goodTankR = ImageUtil.rotateImage(goodTankU,90);

            //加载子弹大小
            bulletU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            bulletL = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletR = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));

            //每一次保障由16张图片完成
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) +".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
