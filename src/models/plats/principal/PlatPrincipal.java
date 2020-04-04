package models.plats.principal;

import models.plats.Plat;

public abstract class PlatPrincipal implements Plat {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
