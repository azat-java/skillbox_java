import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Bank implements Runnable {
    private HashMap<String, Account> accounts;
    private final Random random = new Random();

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
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
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        if (fromAccount != toAccount && !fromAccount.isBlocked() && !toAccount.isBlocked() && fromAccount.getMoney() >= amount) {
            synchronized (accounts) {
                fromAccount.setMoney(fromAccount.getMoney() - amount);
                toAccount.setMoney(toAccount.getMoney() + amount);
            }
            System.out.printf("Transfer from %s to %s: %d\n", fromAccountNum, toAccountNum, amount);
        } else
            return;
        if (amount > 50000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    synchronized (accounts) {
                        fromAccount.setBlocked(true);
                        toAccount.setBlocked(true);
                    }
                    System.out.printf("%s and %s have been blocked\n", fromAccountNum, toAccountNum);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int randomAccountNum1 = ThreadLocalRandom.current().nextInt(1, 100);
            int randomAccountNum2 = ThreadLocalRandom.current().nextInt(1, 100);
            long randomAmount = ThreadLocalRandom.current().nextLong(1, 52000);

            transfer(accounts.get(String.valueOf(randomAccountNum1)).getAccNumber(), accounts.get(String.valueOf(randomAccountNum2)).getAccNumber(), randomAmount);

            System.out.println(accounts.get(String.valueOf(randomAccountNum1)).getAccNumber() + " - " + getBalance(accounts.get(String.valueOf(randomAccountNum1)).getAccNumber()));
            System.out.println(accounts.get(String.valueOf(randomAccountNum2)).getAccNumber() + " - " + getBalance(accounts.get(String.valueOf(randomAccountNum2)).getAccNumber()));
        }
        long sum = accounts.values().stream().mapToLong(account -> getBalance(account.getAccNumber())).sum();
        System.out.println("Thread " + Thread.currentThread().getName() + " stopped. Sum is " + sum);
    }
}
