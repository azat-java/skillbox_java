import java.util.*;

public class Main {
    final static String LIST = "LIST";
    static HashMap<String, String> phoneBook = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            String subscriber = scanner.nextLine().trim();
            if (subscriber.equals(LIST)) {
                printMap(phoneBook);
            } else {
                addSubscriber(subscriber, scanner);
            }
        }
    }

    private static void printMap(Map<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
    }

    private static void addSubscriber(String subscriber, Scanner scanner) {
        if (phoneBook.containsKey(subscriber)) {
            System.out.println(subscriber + " " + phoneBook.get(subscriber));
        } else if (phoneBook.containsValue(subscriber)) {
            for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                if (Objects.equals(subscriber, entry.getValue())) {
                    System.out.println(entry.getKey() + " " + subscriber);
                }
            }
        } else if (subscriber.replaceAll("[^0-9]", "").length() >= 5) { // min length of phone number is 5
            System.out.println("Введите имя абонента: ");
            String name = scanner.nextLine().trim();
            phoneBook.put(name, subscriber.replaceAll("[^0-9]", ""));
            System.out.println("Контакт сохранен");
        } else {
            System.out.println("Введите номер абонента: ");
            String phone = scanner.nextLine().trim();
            if (phone.replaceAll("[^0-9]", "").length() >= 5) {
                phoneBook.put(subscriber, phone.replaceAll("[^0-9]", ""));
                System.out.println("Контакт сохранен");
            } else {
                System.out.println("Некорректный номер");
            }
        }
    }
}
