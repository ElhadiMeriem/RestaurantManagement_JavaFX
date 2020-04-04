package models.plats.principal;

public class CheesePizza extends Pizza {

    @Override
    public String getDescription() {
        return "Cheese Pizza";
    }

    @Override
    public double cost() {
        return 50.00;
    }

    @Override
    public Pizza clone() {
        return new CheesePizza() ;
    }
}

