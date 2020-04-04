package models.plats.entree;

import models.plats.Plat;

public abstract class PlatEnt implements Plat {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
