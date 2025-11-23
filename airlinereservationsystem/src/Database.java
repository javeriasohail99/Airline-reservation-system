import java.io.*;
import java.util.ArrayList;

public class Database {
    private static final String FLIGHT_FILE = "data/flights.txt";
    private static final String PASSENGER_FILE = "data/passengers.txt";
    private static final String BOOKING_FILE = "data/bookings.txt";

    // Create data folder if it doesn't exist
    static {
        File folder = new File("data");
        if (!folder.exists()) folder.mkdir();
    }

    public static void saveFlights(ArrayList<Flight> flights) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FLIGHT_FILE))) {
            out.writeObject(flights);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static ArrayList<Flight> loadFlights() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FLIGHT_FILE))) {
            return (ArrayList<Flight>) in.readObject();
        } catch (Exception e) { return new ArrayList<>(); }
    }

    public static void savePassengers(ArrayList<Passenger> passengers) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PASSENGER_FILE))) {
            out.writeObject(passengers);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static ArrayList<Passenger> loadPassengers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(PASSENGER_FILE))) {
            return (ArrayList<Passenger>) in.readObject();
        } catch (Exception e) { return new ArrayList<>(); }
    }

    public static void saveBookings(ArrayList<Booking> bookings) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(BOOKING_FILE))) {
            out.writeObject(bookings);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static ArrayList<Booking> loadBookings() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(BOOKING_FILE))) {
            return (ArrayList<Booking>) in.readObject();
        } catch (Exception e) { return new ArrayList<>(); }
    }
}
