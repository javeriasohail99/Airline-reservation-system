import java.io.Serializable;

public class Booking implements Serializable {
    private Passenger passenger;
    private Flight flight;

    public Booking(Passenger passenger, Flight flight) {
        this.passenger = passenger;
        this.flight = flight;
    }

    public Passenger getPassenger() { return passenger; }

    public String toString() {
        return passenger.getName() + " booked " + flight.getFlightNumber() + " (" + flight.getSource() + " -> " + flight.getDestination() + ")";
    }
}
