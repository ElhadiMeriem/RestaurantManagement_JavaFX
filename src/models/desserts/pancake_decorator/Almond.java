package models.desserts.pancake_decorator;

import models.desserts.Pancake;

public class Almond extends CondimentDecorator {

    private Pancake pancake;

    public Almond(Pancake pancake){
        this.pancake = pancake;
        this.setDescription(pancake.getDescription() + ", Almond");
    }

    @Override
    public double cost() {
        return 1.35 + pancake.cost();
    }

}
