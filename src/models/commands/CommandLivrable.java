package models.commands;

import java.util.Date;

public class CommandLivrable extends Command {

    private String address;

    public CommandLivrable(Date date, String address) {
        super(date);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
