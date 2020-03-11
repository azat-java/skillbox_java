import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        HashSet<String> emailList = new HashSet<>();
        while (true) {
            System.out.println("Введите команду: ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().trim();
            String commands[] = command.split("\\s+");

            if (commands[0].equals("LIST")) {
                for (String email : emailList) {
                    System.out.println(email);
                }
            } else if (commands[0].equals("ADD") && commands.length == 2) {
                String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(commands[1]);
                if (matcher.matches()) {
                    emailList.add(commands[1]);
                } else {
                    System.out.println("Некорректный ввод");
                }
            } else {
                System.out.println("Некорректный ввод");
            }
        }
    }
}