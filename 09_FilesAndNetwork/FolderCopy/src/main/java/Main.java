import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.stream.Stream;

import exceptions.FolderNotEmptyException;

public class Main {
    static int totalFilesCount = 0;
    static int totalFoldersCount = 0;

    public static void main(String[] args) {
        System.out.println("Программа копирования папки\n");
        System.out.println("Введите путь до папки, которая будет скопирована:");
        try {
            Scanner scanner = new Scanner(System.in);
            Path sourceFolder = Paths.get(scanner.nextLine().trim());
            while (!sourceFolder.toFile().isDirectory()) {
                System.out.println("Введен неверный путь");
                sourceFolder = Paths.get(scanner.nextLine().trim());
            }
            System.out.println("Введите путь до места, куда будет скопирована папка:");
            Path destinationFolder = Paths.get(scanner.nextLine().trim());
            while (!getDestinationFolder(destinationFolder)) {
                destinationFolder = Paths.get(scanner.nextLine().trim());
            }
            scanner.close();
            copyFolder(sourceFolder, destinationFolder);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Папка успешно скопирована");
        System.out.printf("Количество скопированных папок: %d\n", totalFoldersCount);
        System.out.printf("Количество скопированных файлов: %d\n", totalFilesCount);
    }

    static boolean getDestinationFolder(Path destinationFolder) {
        try {
            if (!destinationFolder.toFile().isDirectory()) {
                System.out.println("Новая папка успешно создана");
            } else if (Files.list(destinationFolder).findAny().isPresent()) {
                throw new FolderNotEmptyException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    static void copyFolder(Path src, Path dest) {
        try (Stream<Path> stream = Files.walk(src)) {
            stream.forEach(source -> {
                copy(source, dest.resolve(src.relativize(source)));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copy(Path source, Path dest) {
        try {
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (AccessDeniedException e) {
            System.out.println("Ошибка доступа");
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (source.toFile().isDirectory()) {
            totalFoldersCount++;
        } else {
            totalFilesCount++;
        }
    }
}