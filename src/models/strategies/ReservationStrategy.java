package models.strategies;

import models.reservations.Reservation;
import models.users.Client;

public class ReservationStrategy implements ClientStrategy {

    private Reservation reservation;

    public ReservationStrategy(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void action(Client client) {
        reservation.setClient(client);
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}
