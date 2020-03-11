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
            String formattedPhone = phone.replaceAll("(\\d{3})(\\d{3})(\\d{2})(\\d{2})", "+7 $1 $2-$3-$4");
            System.out.println(formattedPhone);
        } else {
            System.out.println("Некорректный ввод");
        }
    }
}
