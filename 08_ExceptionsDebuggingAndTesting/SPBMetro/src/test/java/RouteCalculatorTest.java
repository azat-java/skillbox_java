import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    private Station s1;
    private Station s2;
    private Station s3;
    private Station s4;

    @Override
    protected void setUp() throws Exception {
        route = new ArrayList<>();

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        s1 = new Station("Петровская", line1);
        s2 = new Station("Арбузная", line1);
        s3 = new Station("Морковная", line2);
        s4 = new Station("Яблочная", line3);
        route.add(s1);
        route.add(s2);
        route.add(s3);
        route.add(s4);
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 9.5;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
