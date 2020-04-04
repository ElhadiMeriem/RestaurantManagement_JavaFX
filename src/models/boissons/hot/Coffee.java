package models.boissons.hot;

public class Coffee extends HotBoisson {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double cost() {
        return 50.5;
    }

    @Override
    public String getDescription() {
        return "Coffee";
    }

    @Override
    public void brew() {
        System.out.println("Verser de cafe");

    }

    @Override
    public void addCondiment() {
        System.out.println("Ajouter lait");
    }

    @Override
    public HotBoisson clone() {
        return new Coffee() ;
    }

}
