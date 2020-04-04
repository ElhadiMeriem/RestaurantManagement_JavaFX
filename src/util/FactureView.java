package util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class FactureView {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty commandId;
    private final SimpleDoubleProperty total;

    public FactureView(SimpleIntegerProperty id, SimpleIntegerProperty commandId, SimpleDoubleProperty total) {
        this.id = id;
        this.commandId = commandId;
        this.total = total;
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

    public int getCommandId() {
        return commandId.get();
    }

    public SimpleIntegerProperty commandIdProperty() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId.set(commandId);
    }

    public double getTotal() {
        return total.get();
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }
}
