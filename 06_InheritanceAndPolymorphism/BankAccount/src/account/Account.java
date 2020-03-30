package account;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;

    protected void putMoney(BigDecimal money) {
        if (balance.compareTo(money) > 0) {
            balance.add(money);
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
