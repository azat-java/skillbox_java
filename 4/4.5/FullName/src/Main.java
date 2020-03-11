import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите фамилию, имя и отчество:");
        Scanner scanner = new Scanner(System.in);
        String fullName = scanner.nextLine();
        scanner.close();
        if (fullName.trim().matches("([А-ЯЁ][а-яё]+[\\-\\s]?){3}")) {
            String[] splitedName = fullName.trim().split("\\s+");
            System.out.println("Фамилия: " + splitedName[0]);
            System.out.println("Имя: " + splitedName[1]);
            System.out.println("Отчество: " + splitedName[2]);
        }
    }
}