package account;

import java.util.Calendar;

public class Debit extends Account {
    public float balance;
    public Calendar lastPut;

    public void getMoney(float money) {
        Calendar isExpired = lastPut;
        isExpired.add(Calendar.MONTH, 1);
        Calendar now = Calendar.getInstance();
        if (now.compareTo(isExpired) > 0) {
            if (balance >= money) {
                balance -= money;
            } else {
                System.out.println("Недостаточно средств");
            }
        } else {
            System.out.println("С момента последнего пополнения прошло меньше месяца");
        }
    }
}
