package models.boissons;

import models.plats.Plat;

public interface Boisson extends Plat {

    public int getId();
    public void setId(int id);
    public String getDescription();
    public double cost();
    public void prepareBoisson();


}
