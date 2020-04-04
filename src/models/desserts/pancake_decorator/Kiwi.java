package models.desserts.pancake_decorator;

import models.desserts.Pancake;

public class Kiwi extends FruitDecorator {

    private Pancake pancake;

    public Kiwi(Pancake pancake){
        this.pancake = pancake;
        this.setDescription(pancake.getDescription() + ", Kiwi");
    }

    @Override
    public double cost() {
        return 1.85 + pancake.cost();
    }


}
