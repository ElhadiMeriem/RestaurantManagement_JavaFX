package models.desserts.pancake_decorator;

import models.desserts.Pancake;

public class Strawberry extends FruitDecorator {

    private Pancake pancake;

    public Strawberry(Pancake pancake){
        this.pancake = pancake;
        this.setDescription(pancake.getDescription() + ", Strawberry");
    }

    @Override
    public double cost() {
        return 1.65 + pancake.cost();
    }

}
