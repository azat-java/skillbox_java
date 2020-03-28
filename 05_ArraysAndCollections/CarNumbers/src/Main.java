import java.util.*;

public class Main {
    public static void main(String[] args) {
        char[] letters = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
        String[] roundNumbers = {"000", "111", "222", "333", "444", "555", "666", "777", "888", "999"};
        ArrayList<String> carNumbers = new ArrayList<>();
        for (char letter1 : letters) {
            for (String number : roundNumbers) {
                for (char letter2 : letters) {
                    for (char letter3 : letters) {
                        for (int region = 1; region < 200; region++) {
                            carNumbers.add(letter1 + number + letter2 + letter3 + String.format("%02d", region));
                        }
                    }
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        String carNumber = scanner.nextLine().trim();
        if (!carNumber.isEmpty()) {
            System.out.print("Поиск перебором: ");
            long start = System.nanoTime();
            boolean bruteForce = carNumbers.contains(carNumber);
            long duration = System.nanoTime() - start;
            if (bruteForce) {
                System.out.printf("номер найден, поиск занял %d нс\n", duration);
            } else {
                System.out.printf("номер не найден, поиск занял %d нс\n", duration);
            }
            Collections.sort(carNumbers);
            System.out.print("Бинарный поиск: ");
            start = System.nanoTime();
            int binarySearch = Collections.binarySearch(carNumbers, carNumber);
            duration = System.nanoTime() - start;
            if (binarySearch >= 0) {
                System.out.printf("номер найден, поиск занял %d нс\n", duration);
            } else {
                System.out.printf("номер не найден, поиск занял %d нс\n", duration);
            }
            HashSet<String> carNumbersSet = new HashSet<>();
            carNumbersSet.addAll(carNumbers);
            System.out.print("Поиск в HashSet: ");
            start = System.nanoTime();
            boolean hashSetSearch = carNumbersSet.contains(carNumber);
            duration = System.nanoTime() - start;
            if (hashSetSearch) {
                System.out.printf("номер найден, поиск занял %d нс\n", duration);
            } else {
                System.out.printf("номер не найден %d, поиск занял %d нс\n", binarySearch, duration);
            }
            TreeSet<String> carNumbersTreeSet = new TreeSet<>();
            carNumbersTreeSet.addAll(carNumbersSet);
            System.out.print("Поиск в TreeSet: ");
            start = System.nanoTime();
            boolean treeSort = carNumbersTreeSet.contains(carNumber);
            duration = System.nanoTime() - start;
            if (treeSort) {
                System.out.printf("номер найден, поиск занял %d нс\n", duration);
            } else {
                System.out.printf("номер не найден %d, поиск занял %d нс\n", binarySearch, duration);
            }
        }
    }
}