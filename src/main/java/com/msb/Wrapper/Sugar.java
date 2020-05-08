package com.msb.Wrapper;

public class Sugar implements CondimentDecorator {

    Beverage beverage;

    public Sugar(Beverage beverage){
        this.beverage = beverage;
    }

    public String getName() {
        return beverage.getName() + separator + "糖";
    }

    public Double cost() {
        return beverage.cost() + 1.0;
    }
}
