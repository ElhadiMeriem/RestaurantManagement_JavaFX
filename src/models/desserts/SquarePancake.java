package models.desserts;

public class SquarePancake extends Pancake {

    public SquarePancake() {
        this.setDescription("Pancake Carré");
    }

    @Override
    public double cost() {
        return 50.00;
    }

}
