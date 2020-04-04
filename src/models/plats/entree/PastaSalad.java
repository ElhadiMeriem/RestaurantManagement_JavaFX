package models.plats.entree;

public class PastaSalad extends  Salad {

    @Override
    public Salad clone() {
        return new PastaSalad();
    }

    @Override
    public double cost() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Pasta Salad";
    }
}
