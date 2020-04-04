package models.boissons.cold;

import models.boissons.Boisson;

public abstract class ColdBoisson implements Boisson {

    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public abstract double cost();
    public abstract String getDescription();
    public abstract ColdBoisson clone();

    //template method
    @Override
    public void prepareBoisson() {
        uncoverBoisson();
        putInGlass();
        putPipet();
        addIce();

    }

    public void uncoverBoisson(){
        System.out.println("ouvrir la bouteille de boisson");
    }

    public void putInGlass(){
        System.out.println("Verser dans un verre");
    }

    public void putPipet(){
        System.out.println("Mettre une pipette dans le verre");
    }

    public void addIce(){
        System.out.println("Ajouter glaçons");
    }

}
