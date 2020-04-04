package models.strategies;

import models.commands.CommandSurplace;
import models.reservations.Reservation;
import models.users.Client;

public class CommandAndReservationStrategy implements ClientStrategy {

    private Reservation reservation;
    private CommandSurplace command;

    public CommandAndReservationStrategy(Reservation reservation, CommandSurplace command) {
        this.reservation = reservation;
        this.command = command;
    }

    @Override
    public void action(Client client) {
        reservation.setClient(client);
        command.setClient(client);
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public CommandSurplace getCommand() {
        return command;
    }

    public void setCommand(CommandSurplace command) {
        this.command = command;
    }

}
