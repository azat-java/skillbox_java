import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static int THREADS = Runtime.getRuntime().availableProcessors();
    private static int TRANSACTIONS = 1000;
    private static HashMap<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        Bank bank = new Bank();

        for (int i = 0; i < 100; i++) {
            Account a = new Account(String.valueOf(i), 1000000);
            accounts.put(a.getAccNumber(), a);
        }

        bank.setAccounts(accounts);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREADS; i++) {
            threads.add(new Thread(() -> {
                for (int i1 = 0; i1 < TRANSACTIONS; i1++) {
                    bank.transfer(getRandomAccount(), getRandomAccount(), getRandomMoneyAmount());
                }
            }));
        }

        bank.getAllBalance();

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        bank.getAllBalance();
    }

    private static long getRandomMoneyAmount() {
        return ThreadLocalRandom.current().nextLong(1, 52000);
    }

    private static Account getRandomAccount() {
        Random generator = new Random();
        Account[] values = accounts.values().toArray(new Account[0]);
        return values[generator.nextInt(values.length)];
    }
}
