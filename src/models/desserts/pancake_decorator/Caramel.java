package models.desserts.pancake_decorator;

import models.desserts.Pancake;

public class Caramel extends ChocolateDecorator {

    private Pancake pancake;

    public Caramel(Pancake pancake){
        this.pancake = pancake;
        this.setDescription(pancake.getDescription() + ", Caramel");
    }

    @Override
    public double cost() {
        return 10.50 + pancake.cost();
    }

}
