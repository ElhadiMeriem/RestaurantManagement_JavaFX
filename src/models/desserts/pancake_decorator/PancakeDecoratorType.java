package models.desserts.pancake_decorator;

public enum PancakeDecoratorType {
    ALMOND("Almond"), BANANA("Banana"), CARAMEL("Caramel"), COCONUT("Coconut"), KIWI("Kiwi"), NUTELLA("Nutella"), STRAWBERRY("Strawberry");

    private String name;

    private PancakeDecoratorType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
