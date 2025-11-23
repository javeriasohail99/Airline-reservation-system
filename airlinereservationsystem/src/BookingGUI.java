import javax.swing.*;
import java.util.ArrayList;

public class BookingGUI extends JFrame {
    Passenger passenger;
    JComboBox<String> flightComboBox;
    JButton bookButton;

    public BookingGUI(Passenger passenger){
        this.passenger = passenger;
        setTitle("Book a Flight");
        setSize(400,200);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel selectFlight = new JLabel("Select Flight:");
        selectFlight.setBounds(20,20,100,25);
        add(selectFlight);

        ArrayList<Flight> flights = Database.loadFlights();
        String[] flightArr = flights.stream().map(Flight::toString).toArray(String[]::new);
        flightComboBox = new JComboBox<>(flightArr);
        flightComboBox.setBounds(120,20,220,25);
        add(flightComboBox);

        bookButton = new JButton("Book");
        bookButton.setBounds(150,70,100,30);
        add(bookButton);
        bookButton.addActionListener(e -> bookFlight(flights));

        setVisible(true);
    }

    private void bookFlight(ArrayList<Flight> flights){
        int index = flightComboBox.getSelectedIndex();
        Flight selectedFlight = flights.get(index);

        if(selectedFlight.bookSeat()){
            Database.saveFlights(flights);

            ArrayList<Booking> bookings = Database.loadBookings();
            bookings.add(new Booking(passenger,selectedFlight));
            Database.saveBookings(bookings);

            JOptionPane.showMessageDialog(this,"Flight booked successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,"No seats available!");
        }
    }
}
