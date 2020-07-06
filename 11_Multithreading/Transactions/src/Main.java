import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Account> accounts = new HashMap<>();
        Bank bank = new Bank();

        for (int i = 0; i < 100; i++) {
            Account a = new Account(String.valueOf(i), 1000000);
            accounts.put(a.getAccNumber(), a);
        }

        bank.setAccounts(accounts);

        for (int i = 0; i < 100; i++) {
            new Thread(bank).start();
        }
    }
}
