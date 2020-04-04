package models.boissons.hot;

import models.boissons.Boisson;

public abstract class HotBoisson implements Boisson {

    public abstract double cost();
    public abstract String getDescription();
    public abstract HotBoisson clone();

    //template method
    @Override
    public void prepareBoisson() {
        boilWater();
        brew();
        putInCup();
        addCondiment();
    }


    public void boilWater(){
        System.out.println("Builler eau");
    }

    public abstract void brew();

    public void putInCup(){
        System.out.println("verser dans une tasse");
    }

    public abstract void addCondiment();


}
