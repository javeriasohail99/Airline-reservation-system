import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainMenuGUI extends JFrame {
    boolean isAdmin;
    Passenger passenger;

    public MainMenuGUI(boolean isAdmin, Passenger passenger) {
        this.isAdmin = isAdmin;
        this.passenger = passenger;

        setTitle("Main Menu");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Airline Reservation - Main Menu", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setOpaque(true);
        header.setBackground(new Color(0, 123, 255));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(header, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JButton viewFlights = new JButton("View Flights");
        viewFlights.addActionListener(e -> viewFlights());
        buttonPanel.add(viewFlights);

        if(isAdmin){
            JButton addFlight = new JButton("Add Flight");
            addFlight.addActionListener(e -> addFlight());
            buttonPanel.add(addFlight);

            JButton manageFlights = new JButton("Manage Flights");
            manageFlights.addActionListener(e -> manageFlights());
            buttonPanel.add(manageFlights);
        } else {
            JButton bookFlight = new JButton("Book Flight");
            bookFlight.addActionListener(e -> new BookingGUI(passenger));
            buttonPanel.add(bookFlight);

            JButton myBookings = new JButton("My Bookings");
            myBookings.addActionListener(e -> viewMyBookings());
            buttonPanel.add(myBookings);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void viewFlights() {
        ArrayList<Flight> flights = Database.loadFlights();
        StringBuilder sb = new StringBuilder();
        for (Flight f : flights) sb.append(f).append("\n");
        JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "No flights available.");
    }

    private void addFlight() {
        String flightNo = JOptionPane.showInputDialog(this, "Flight Number:");
        String source = JOptionPane.showInputDialog(this, "Source:");
        String dest = JOptionPane.showInputDialog(this, "Destination:");
        int seats = Integer.parseInt(JOptionPane.showInputDialog(this, "Total Seats:"));

        ArrayList<Flight> flights = Database.loadFlights();
        flights.add(new Flight(flightNo, source, dest, seats));
        Database.saveFlights(flights);

        JOptionPane.showMessageDialog(this, "Flight added successfully!");
    }

    private void manageFlights() {
        ArrayList<Flight> flights = Database.loadFlights();
        if(flights.isEmpty()){
            JOptionPane.showMessageDialog(this,"No flights available.");
            return;
        }

        String[] options = flights.stream().map(Flight::toString).toArray(String[]::new);
        String selected = (String) JOptionPane.showInputDialog(this,"Select flight to update/delete:","Manage Flights",
                JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if(selected==null) return;

        Flight flight = flights.get(java.util.Arrays.asList(options).indexOf(selected));

        String[] actions = {"Update","Delete"};
        String action = (String) JOptionPane.showInputDialog(this,"Choose action:","Action",
                JOptionPane.QUESTION_MESSAGE,null,actions,actions[0]);
        if(action==null) return;

        if(action.equals("Update")){
            String newSource = JOptionPane.showInputDialog(this,"New Source:",flight.getSource());
            String newDest = JOptionPane.showInputDialog(this,"New Destination:",flight.getDestination());
            int newSeats = Integer.parseInt(JOptionPane.showInputDialog(this,"New Total Seats:",flight.getAvailableSeats()));

            flights.set(flights.indexOf(flight), new Flight(flight.getFlightNumber(),newSource,newDest,newSeats));
            Database.saveFlights(flights);
            JOptionPane.showMessageDialog(this,"Flight updated!");
        } else if(action.equals("Delete")){
            flights.remove(flight);
            Database.saveFlights(flights);
            JOptionPane.showMessageDialog(this,"Flight deleted!");
        }
    }

    private void viewMyBookings() {
        ArrayList<Booking> bookings = Database.loadBookings();
        StringBuilder sb = new StringBuilder();
        for(Booking b : bookings){
            if(b.getPassenger().getUsername().equals(passenger.getUsername()))
                sb.append(b).append("\n");
        }
        JOptionPane.showMessageDialog(this,sb.length()>0?sb.toString():"No bookings yet.");
    }
}
