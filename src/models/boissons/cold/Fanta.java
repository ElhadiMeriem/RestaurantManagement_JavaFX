package models.boissons.cold;

public class Fanta extends ColdBoisson {

    @Override
    public double cost() {
        return 40.0;
    }

    @Override
    public String getDescription() {
        return "Fanta";
    }

    @Override
    public ColdBoisson clone() {
        return new Fanta() ;
    }

}
