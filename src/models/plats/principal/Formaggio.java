package models.plats.principal;

public class Formaggio extends Pizza {

    @Override
    public String getDescription() {
        return "Formaggio Pizza";
    }

    @Override
    public double cost() {
        return 50.00;
    }


    @Override
    public Pizza clone() {
        return new Formaggio();
    }
}



