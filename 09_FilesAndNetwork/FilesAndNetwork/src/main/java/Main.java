import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Программа расчёта размера содержимого папки");
        while (true) {
            System.out.println("Введите путь:");
            Scanner scanner = new Scanner(System.in);
            String path = scanner.nextLine().trim();
            File directory = new File(path);
            if (directory.isDirectory()) {
                System.out.println("Размер содержимого папки: " + FileUtils.byteCountToDisplaySize(FileUtils.sizeOfDirectory(directory)));
            } else {
                System.out.println("Введен неверный путь");
            }
        }
    }
}
