package com.msb.Wrapper;

public class Ice implements CondimentDecorator {

    Beverage beverage;

    public Ice(Beverage beverage){
        this.beverage = beverage;
    }

    public String getName() {
        return beverage.getName() + separator + "冰";
    }

    public Double cost() {
        return beverage.cost() + 1.5;
    }
}
