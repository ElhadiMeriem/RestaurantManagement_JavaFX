package models.boissons.cold;

public class Sprite extends ColdBoisson{

    @Override
    public double cost() {
        return 40.0;
    }

    @Override
    public String getDescription() {
        return "Sprite";
    }

    @Override
    public ColdBoisson clone() {
        return new Sprite() ;
    }

}
