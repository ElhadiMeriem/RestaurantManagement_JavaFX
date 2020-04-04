package models.plats.entree;

public enum SaladType {

    CHEF("Chef Salad"), GREEN("Green Salad"), PASTA("Pasta Salad");

    private String name;

    private SaladType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
