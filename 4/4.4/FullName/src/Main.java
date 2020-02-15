import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите фамилию, имя и отчество:");
        Scanner scanner = new Scanner(System.in);
        String fullName = scanner.nextLine();
        scanner.close();
        fullName = fullName.trim();
        int surnameIndex = fullName.indexOf(" ");
        int patronymicIndex = fullName.lastIndexOf(" ");
        if (fullName.length() > 0 && surnameIndex > 0) {
            System.out.println("Фамилия: " + fullName.substring(0, surnameIndex));
            if (patronymicIndex > surnameIndex) {
                System.out.println("Имя: " + fullName.substring(surnameIndex, patronymicIndex));
                System.out.println("Отчество: " + fullName.substring(patronymicIndex));
            } else {
                System.out.println("Имя: " + fullName.substring(surnameIndex));
            }
        } else {
            System.out.println("Некорректный ввод");
        }
    }
}
