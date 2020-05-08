package com.msb.Wrapper;

public class CalcPrice {

    public static void main(String[] args) {
        /**
         * 计算一杯加糖,加冰的咖啡总价格,并显示原料组成
         */
        Beverage beverage = new Coffee();

        beverage = new Sugar(new Ice(beverage));

        System.out.println(String.format("总价: %s , 原料组成: %s",beverage.cost(),beverage.getName()));
    }
}
