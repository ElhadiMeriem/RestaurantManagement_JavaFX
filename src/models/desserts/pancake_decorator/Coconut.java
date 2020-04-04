package models.desserts.pancake_decorator;

import models.desserts.Pancake;

public class Coconut extends CondimentDecorator {

    private Pancake pancake;

    public Coconut(Pancake pancake){
        this.pancake = pancake;
        this.setDescription(pancake.getDescription() + ", Coconut");
    }

    @Override
    public double cost() {
        return 1.10 + pancake.cost();
    }

}
