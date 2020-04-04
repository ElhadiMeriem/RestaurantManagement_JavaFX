package models.plats.principal;

public enum PizzaType {
    FORMAGGIO("Formaggio Pizza"), FRUTTI_DI_MARE("Frutti Di Mare"), CHEESE("Cheese Pizza"), FRUIT_DE_MER("Pizza Fruit De Mer");

    private String name;

    private PizzaType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
