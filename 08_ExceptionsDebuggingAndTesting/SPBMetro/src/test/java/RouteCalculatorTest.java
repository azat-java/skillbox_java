import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex = new StationIndex();
    RouteCalculator calculator = new RouteCalculator(stationIndex);

    Line line1 = new Line(1, "Первая");
    Line line2 = new Line(2, "Вторая");
    Line line3 = new Line(3, "Третья");

    Station s1 = new Station("A", line1);
    Station s2 = new Station("B", line1);
    Station s3 = new Station("C", line1);
    Station s4 = new Station("D", line1);
    Station s5 = new Station("E", line1);
    Station s6 = new Station("F", line1);

    Station s7 = new Station("L", line2);
    Station s8 = new Station("M", line2);
    Station s9 = new Station("N", line2);
    Station s10 = new Station("P", line2);

    Station s11 = new Station("H", line3);
    Station s12 = new Station("I", line3);
    Station s13 = new Station("G", line3);
    Station s14 = new Station("K", line3);

    List<Station> connection1 = new ArrayList<>();
    List<Station> connection2 = new ArrayList<>();

    /**
     * .                                Line 2
     * .                                  L
     * .                                  |
     * .                                  |
     * .     line 1       A -- B -- C -- D/M -- E -- F
     * .                                  |
     * .                                  |
     * .                                  N
     * .                                  |
     * .                                  |
     * .     line 3                 H -- I/P -- G -- K
     */
    @Override
    protected void setUp() throws Exception {
        line1.addStation(s1);
        line1.addStation(s2);
        line1.addStation(s3);
        line1.addStation(s4);
        line1.addStation(s5);
        line1.addStation(s6);

        line2.addStation(s7);
        line2.addStation(s8);
        line2.addStation(s9);
        line2.addStation(s10);

        line3.addStation(s11);
        line3.addStation(s12);
        line3.addStation(s13);
        line3.addStation(s14);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(s1);
        stationIndex.addStation(s2);
        stationIndex.addStation(s3);
        stationIndex.addStation(s4);
        stationIndex.addStation(s5);
        stationIndex.addStation(s6);
        stationIndex.addStation(s7);
        stationIndex.addStation(s8);
        stationIndex.addStation(s9);
        stationIndex.addStation(s10);
        stationIndex.addStation(s11);
        stationIndex.addStation(s12);
        stationIndex.addStation(s13);
        stationIndex.addStation(s14);

        connection1.add(s4);
        connection1.add(s8);

        connection2.add(s10);
        connection2.add(s12);

        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);
    }

    public void test_distance_to_same_station() {
        List<Station> actualRoute = calculator.getShortestRoute(s1, s1);
        List<Station> expectedRoute = new ArrayList<>(Arrays.asList(s1));
        assertEquals(expectedRoute, actualRoute);

        double actualDuration = RouteCalculator.calculateDuration(actualRoute);
        double expectedDuration = 0;
        assertEquals(expectedDuration, actualDuration);
    }

    public void test_stations_next_to_each_other_on_single_line() {
        List<Station> actualRoute = calculator.getShortestRoute(s1, s2);
        List<Station> expectedRoute = new ArrayList<>(Arrays.asList(s1, s2));
        assertEquals(expectedRoute, actualRoute);

        double actualDuration = RouteCalculator.calculateDuration(actualRoute);
        double expectedDuration = 2.5;
        assertEquals(expectedDuration, actualDuration);
    }

    public void test_stations_on_single_line_backwards() {
        List<Station> actualRoute = calculator.getShortestRoute(s3, s1);
        List<Station> expectedRoute = new ArrayList<>(Arrays.asList(s3, s2, s1));
        assertEquals(expectedRoute, actualRoute);

        double actualDuration = RouteCalculator.calculateDuration(actualRoute);
        double expectedDuration = 5;
        assertEquals(expectedDuration, actualDuration);
    }

    public void test_opposite_stations_on_single_line() {
        List<Station> actualRoute = calculator.getShortestRoute(s1, s6);
        List<Station> expectedRoute = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5, s6));
        assertEquals(expectedRoute, actualRoute);

        double actualDuration = RouteCalculator.calculateDuration(actualRoute);
        double expectedDuration = 12.5;
        assertEquals(expectedDuration, actualDuration);
    }

    public void test_opposite_stations_with_one_transfer() {
        List<Station> actualRoute = calculator.getShortestRoute(s1, s7);
        List<Station> expectedRoute = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s8, s7));
        assertEquals(expectedRoute, actualRoute);

        double actualDuration = RouteCalculator.calculateDuration(actualRoute);
        double expectedDuration = 13.5;
        assertEquals(expectedDuration, actualDuration);
    }

    public void test_opposite_stations_with_two_transfers() {
        List<Station> actualRoute = calculator.getShortestRoute(s6, s14);
        List<Station> expectedRoute = new ArrayList<>(Arrays.asList(s6, s5, s4, s8, s9, s10, s12, s13, s14));
        assertEquals(expectedRoute, actualRoute);

        double actualDuration = RouteCalculator.calculateDuration(actualRoute);
        double expectedDuration = 22;
        assertEquals(expectedDuration, actualDuration);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}