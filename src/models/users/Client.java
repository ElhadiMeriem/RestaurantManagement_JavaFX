package models.users;

import models.strategies.ClientStrategy;

public class Client extends User {

    private ClientStrategy strategy;

    public Client(String username, String firstName, String lastName, String email, String password) {
        super(username, firstName, lastName, email, password);
    }

    public ClientStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ClientStrategy strategy) {
        this.strategy = strategy;
    }

}
