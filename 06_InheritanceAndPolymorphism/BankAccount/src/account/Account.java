package account;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance = new BigDecimal(0);

    public void putMoney(BigDecimal money) {
        if (money.compareTo(BigDecimal.valueOf(0)) > 0) {
            balance = balance.add(money);
        }
    }

    public void getMoney(BigDecimal money) {
        if (balance.compareTo(money) >= 0) {
            balance = balance.subtract(money);
        } else {
            System.out.println("Недостаточно средств");
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
