package models.desserts;

public class CirclePancake extends Pancake {

    public CirclePancake() {
        this.setDescription("Pancake Circulaire");
    }

    @Override
    public double cost() {
        return 40.00;
    }

}
