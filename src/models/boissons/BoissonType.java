package models.boissons;

public enum BoissonType {
    THE("The"), COFFEE("Coffee"), COLA("Cola"), FANTA("Fanta"), SPRITE("Sprite");

    private String name;

    private BoissonType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
