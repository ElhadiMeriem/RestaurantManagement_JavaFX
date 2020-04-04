package models.boissons;


import models.boissons.cold.Cola;
import models.boissons.cold.Fanta;
import models.boissons.cold.Sprite;
import models.boissons.hot.Coffee;
import models.boissons.hot.The;

public class BoissonFactory {

    private static BoissonFactory instance;

    private BoissonFactory() {

    }

    public static BoissonFactory getInstance() {

        if (instance == null)
            instance = new BoissonFactory();

        return instance;
    }

    public Boisson makeBoisson(BoissonType boissonType) {

        switch (boissonType) {
            case THE:
                return new The();
            case COFFEE:
                return new Coffee();
            case COLA:
                return new Cola();
            case FANTA:
                return new Fanta();
            case SPRITE:
                return new Sprite();
        }

        return null;
    }

}
