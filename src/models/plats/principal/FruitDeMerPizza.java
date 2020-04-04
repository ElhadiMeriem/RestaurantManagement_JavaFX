package models.plats.principal;

public class FruitDeMerPizza extends Pizza{

    @Override
    public String getDescription() {
        return "Pizza Fruit De Mer" ;
    }

    @Override
    public double cost() {
        return 50.00;
    }

    @Override
    public Pizza clone() {
        return new FruitDeMerPizza() ;
    }

}

