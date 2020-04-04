package models.plats.entree;

public class ChefSalad extends Salad{

    @Override
    public Salad clone() {
        return new ChefSalad();
    }

    @Override
    public double cost() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Chef Salad";
    }

}
