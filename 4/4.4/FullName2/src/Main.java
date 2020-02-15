import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите фамилию, имя и отчество:");
        Scanner scanner = new Scanner(System.in);
        String fullName = scanner.nextLine();
        scanner.close();

        String[] splitedName = fullName.trim().split("\\s+");

        if (splitedName.length > 0 && splitedName[0] != null && !splitedName[0].isEmpty()) {
            System.out.println("Фамилия: " + splitedName[0]);
        }
        if (splitedName.length > 1 && splitedName[1] != null) {
            System.out.println("Имя: " + splitedName[1]);
        }
        if (splitedName.length > 2 && splitedName[2] != null) {
            System.out.println("Отчество: " + splitedName[2]);
        }
    }
}
