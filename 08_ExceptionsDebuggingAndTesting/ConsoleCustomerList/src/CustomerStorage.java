import java.util.HashMap;
import java.util.regex.Pattern;

public class CustomerStorage {
    private HashMap<String, Customer> storage;
    final static String REG_PHONE = "^[+]?[0-9]{3,15}";
    final static String REG_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong command! Available command example:\n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        String name = components[0] + " " + components[1];
        if (!Pattern.compile(REG_PHONE).matcher(components[3]).matches()) {
            throw new IllegalArgumentException("Wrong format of phone! Available format example:\n" +
                    "+79215637722");
        }
        if (!Pattern.compile(REG_EMAIL).matcher(components[2]).matches()) {
            throw new IllegalArgumentException("Wrong format of email! Available format example:\n" +
                    "vasily.petrov@gmail.com");
        }
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}