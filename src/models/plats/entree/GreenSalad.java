package models.plats.entree;

public class GreenSalad extends Salad {

    @Override
    public Salad clone() {
        return new GreenSalad();
    }

    @Override
    public double cost() {
        return 19;
    }

    @Override
    public String getDescription() {
        return "Green Salad";
    }

}
