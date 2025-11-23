import java.io.Serializable;

public class Flight implements Serializable {
    private String flightNumber;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;

    public Flight(String flightNumber, String source, String destination, int totalSeats) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getFlightNumber() { return flightNumber; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getAvailableSeats() { return availableSeats; }

    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    public String toString() {
        return flightNumber + " | " + source + " -> " + destination + " | Seats: " + availableSeats;
    }
}
