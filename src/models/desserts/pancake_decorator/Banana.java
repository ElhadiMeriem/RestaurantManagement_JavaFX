package models.desserts.pancake_decorator;

import models.desserts.Pancake;

public class Banana extends FruitDecorator{

    private Pancake pancake;

    public Banana(Pancake pancake){
        this.pancake = pancake;
        this.setDescription(pancake.getDescription() + ", Caramel");
    }

    @Override
    public double cost() {
        return 1.75 + pancake.cost();
    }

}
