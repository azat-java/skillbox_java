import java.util.HashMap;
import java.util.Random;

public class Bank {
    private HashMap<String, Account> accounts;
    private final Random random = new Random();

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(Account fromAccount, Account toAccount, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(Account fromAccount, Account toAccount, long amount) {
        synchronized (fromAccount.compareTo(toAccount) > 0 ? fromAccount : toAccount) {
            synchronized (fromAccount.compareTo(toAccount) < 0 ? fromAccount : toAccount) {
                if (fromAccount == toAccount) {
                    return;
                }
                if (fromAccount.isBlocked() || toAccount.isBlocked()) {
                    return;
                }
                if (fromAccount.getMoney() < amount) {
                    return;
                }

                fromAccount.setMoney(fromAccount.getMoney() - amount);
                toAccount.setMoney(toAccount.getMoney() + amount);

                if (amount > 50000) {
                    try {
                        if (isFraud(fromAccount, toAccount, amount)) {
                            fromAccount.setBlocked(true);
                            toAccount.setBlocked(true);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public void getAllBalance() {
        long sum = accounts.values().stream().mapToLong(account -> getBalance(account.getAccNumber())).sum();
        System.out.println("Total money amount in accounts is " + sum);
    }
}
