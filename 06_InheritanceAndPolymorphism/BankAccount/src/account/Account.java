package account;

public class Account {
    public float balance;

    public void putMoney(float money) {
        if (money > 0) {
            balance += money;
        }
    }

    public void getMoney(float money) {
        if (balance >= money) {
            balance -= money;
        } else {
            System.out.println("Недостаточно средств");
        }
    }

    public float getBalance() {
        return balance;
    }
}
