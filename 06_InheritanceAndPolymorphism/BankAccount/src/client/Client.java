package client;

import java.math.BigDecimal;

public abstract class Client {
    private BigDecimal balance = new BigDecimal(0);

    void putMoney(BigDecimal money) {
        if (money.compareTo(BigDecimal.valueOf(0)) > 0) {
            balance = balance.add(money);
        }
    }

    void getMoney(BigDecimal money) {
        if (balance.compareTo(money) >= 0) {
            balance.subtract(money);
        } else {
            System.out.println("Недостаточно средств");
        }
    }

    BigDecimal getBalance() {
        return balance;
    }
}
