package models.desserts.pancake_decorator;

import models.desserts.Pancake;

public class Nutella extends ChocolateDecorator {

    private Pancake pancake;

    public Nutella(Pancake pancake){
        this.pancake = pancake;
        this.setDescription(pancake.getDescription() + ", Nutella");
    }

    @Override
    public double cost() {
        return 10.25 + pancake.cost();
    }

}
