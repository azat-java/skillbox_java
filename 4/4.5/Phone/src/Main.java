import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите номер телефона:");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.nextLine();
        scanner.close();
        phone = phone.replaceAll("[^0-9]", "");
        if (9 < phone.length() && phone.length() < 12) {
            if (phone.length() == 11) {
                phone = phone.substring(1);
            }
            System.out.println("+7 " + phone.substring(0, 3) + " " + phone.substring(3, 6) + "-" + phone.substring(6, 8) + "-" + phone.substring(8, 10));
        } else {
            System.out.println("Некорректный ввод");
        }
    }
}
