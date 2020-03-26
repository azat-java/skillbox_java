import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    final static String ADD = "ADD";
    final static String LIST = "LIST";
    static HashSet<String> emailList = new HashSet<>();
    final static String REG_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите команду: ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().trim();
            String commands[] = command.split("\\s+");

            if (commands[0].equals(LIST)) {
                printList();
            } else if (commands[0].equals(ADD) && commands.length == 2) {
                add(commands[1]);
            } else {
                error();
            }
        }
    }

    static void printList() {
        for (String email : emailList) {
            System.out.println(email);
        }
    }

    static void add(String email) {
        Pattern pattern = Pattern.compile(REG_EMAIL);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            emailList.add(email);
        } else {
            error();
        }
    }

    static void error() {
        System.out.println("Некорректный ввод");
    }
}