package account;

import java.math.BigDecimal;
import java.util.Calendar;

public class Debit extends Account {
    public Calendar lastPut;

    public void getMoney(BigDecimal money) {
        Calendar isExpired = lastPut;
        isExpired.add(Calendar.MONTH, 1);
        Calendar now = Calendar.getInstance();
        if (now.compareTo(isExpired) > 0) {
            super.getMoney(money);
        } else {
            System.out.println("С момента последнего пополнения прошло меньше месяца");
        }
    }
}
