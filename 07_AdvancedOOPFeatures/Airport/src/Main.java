import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Terminal> terminals = airport.getTerminals();
        List<Flight> flights = new ArrayList();
        terminals.forEach(t -> flights.addAll(t.getFlights()));
        Date dateFrom = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFrom);
        calendar.add(Calendar.HOUR, 2);
        Date dateTo = calendar.getTime();
        flights.stream()
                .filter(f -> f.getDate().after(dateFrom) && f.getDate().before(dateTo))
                .forEach(f -> System.out.println(f.getDate() + " - " + f.getAircraft()));
    }

}
