package util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderDetailView {

    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty customerId;
    private final SimpleStringProperty customerName;
    private final SimpleStringProperty orderDate;
    private final SimpleStringProperty livrable;

    public OrderDetailView(SimpleIntegerProperty id, SimpleIntegerProperty customerId, SimpleStringProperty customerName, SimpleStringProperty orderDate, SimpleStringProperty livrable) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.livrable = livrable;
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

    public int getCustomerId() {
        return customerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public SimpleStringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getOrderDate() {
        return orderDate.get();
    }

    public SimpleStringProperty orderDateProperty() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate.set(orderDate);
    }

    public String getLivrable() {
        return livrable.get();
    }

    public SimpleStringProperty livrableProperty() {
        return livrable;
    }

    public void setLivrable(String livrable) {
        this.livrable.set(livrable);
    }
}
