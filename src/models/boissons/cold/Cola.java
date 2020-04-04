package models.boissons.cold;

public class Cola extends ColdBoisson {

    @Override
    public double cost() {
        return 40.0;
    }

    @Override
    public String getDescription() {
        return "Coca Cola";
    }

    @Override
    public ColdBoisson clone() {
        return new Cola() ;
    }

}
