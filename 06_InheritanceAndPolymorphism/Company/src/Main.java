import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Company skillbox = new Company("Skillbox");

        for (int i = 0; i < 180; i++) {
            skillbox.hire(new Operator("operator num. " + i, new BigDecimal("25000"), skillbox));
        }

        ArrayList<Worker> managers = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            int income = new Random().nextInt(1400000);
            managers.add(new Manager("manager num. " + i, BigDecimal.valueOf(30000), skillbox, BigDecimal.valueOf(income)));
        }
        skillbox.hireAll(managers);

        for (int i = 0; i < 10; i++) {
            skillbox.hire(new TopManager("top manager num. " + i, new BigDecimal("100000"), skillbox));
        }

        skillbox.printTopSalaries(15);
        skillbox.printLowestSalaries(30);

        final int STAFF_PERCENT_TO_FIRE = 50;
        int staffToFireIndex = 100 / STAFF_PERCENT_TO_FIRE;
        int counterToFire = 1;
        Iterator<Worker> itr = skillbox.employees.iterator();
        while (itr.hasNext()) {
            if (counterToFire % staffToFireIndex == 0) {
                skillbox.fire(itr);
            }
            counterToFire++;
        }

        skillbox.printTopSalaries(15);
        skillbox.printLowestSalaries(30);
    }
}