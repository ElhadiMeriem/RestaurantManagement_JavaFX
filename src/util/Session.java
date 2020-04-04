package util;

import models.users.User;

public class Session {

    private static User connectedClient;

    public static User getConnectedClient() {
        return connectedClient;
    }

    public static void setConnectedClient(User connectedClient) {
        Session.connectedClient = connectedClient;
    }

}
