import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        preloadData();
        new LoginGUI();
    }

    private static void preloadData(){
        ArrayList<Passenger> passengers = Database.loadPassengers();
        if(passengers.isEmpty()){
            passengers.add(new Passenger("Javeria Sohail","javeria","12345"));
            Database.savePassengers(passengers);
        }

        ArrayList<Flight> flights = Database.loadFlights();
        if(flights.isEmpty()){
            flights.add(new Flight("PK101","Karachi","Islamabad",50));
            flights.add(new Flight("PK102","Lahore","Karachi",45));
            flights.add(new Flight("PK103","Islamabad","Lahore",60));
            Database.saveFlights(flights);
        }
    }
}
