package account;

import java.math.BigDecimal;

public class Account {
    public BigDecimal balance;

    public void putMoney(BigDecimal money) {
        if (balance.compareTo(money) > 0) {
            balance.add(money);
        }
    }

    public void getMoney(BigDecimal money) {
        if (balance.compareTo(money) >= 0) {
            balance.subtract(money);
        } else {
            System.out.println("Недостаточно средств");
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
