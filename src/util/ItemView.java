package util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemView {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty description;
    private final SimpleDoubleProperty price;
    private final SimpleStringProperty type;

    public ItemView(SimpleIntegerProperty id, SimpleStringProperty description, SimpleDoubleProperty price, SimpleStringProperty type) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
