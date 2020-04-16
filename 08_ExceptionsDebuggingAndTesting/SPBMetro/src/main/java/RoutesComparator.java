import core.Station;

import java.util.Comparator;
import java.util.List;

public class RoutesComparator implements Comparator<List<Station>> {
    @Override
    public int compare(List<Station> route1, List<Station> route2) {
        if (RouteCalculator.calculateDuration(route1) > RouteCalculator.calculateDuration(route2)) {
            return 1;
        } else if (RouteCalculator.calculateDuration(route1) < RouteCalculator.calculateDuration(route2)) {
            return -1;
        }
        return 0;
    }
}
