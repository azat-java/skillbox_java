import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Terminal> terminals = airport.getTerminals();
        List<Flight> flights = terminals.stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .collect(Collectors.toList());

        Date dateFrom = java.util.Date
                .from(LocalDateTime.now().atZone(ZoneId.systemDefault())
                        .toInstant());

        Date dateTo = java.util.Date
                .from(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault())
                        .toInstant());

        flights.stream()
                .filter(f -> f.getDate().after(dateFrom)
                        && f.getDate().before(dateTo)
                        && f.getType().equals(Flight.Type.DEPARTURE))
                .forEach(f -> System.out.println(f.getDate() + " - " + f.getAircraft()));
    }
}