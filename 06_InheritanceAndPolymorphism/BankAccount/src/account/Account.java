package account;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance = new BigDecimal(0);

    protected void putMoney(BigDecimal money) {
        if (money.compareTo(BigDecimal.valueOf(0)) > 0) {
            balance = balance.add(money);
        }
    }

    protected void getMoney(BigDecimal money) {
        if (balance.compareTo(money) >= 0) {
            balance.subtract(money);
        } else {
            System.out.println("Недостаточно средств");
        }
    }

    protected BigDecimal getBalance() {
        return balance;
    }
}
