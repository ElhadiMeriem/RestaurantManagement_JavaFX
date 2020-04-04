package models.plats.principal;

public class FruttiDiMare extends  Pizza {

    @Override
    public String getDescription() {
        return "Frutti Di Mare" ;
    }

    @Override
    public double cost() {
        return 60.00;
    }


    @Override
    public Pizza clone() {
        return new FruttiDiMare() ;
    }
}



