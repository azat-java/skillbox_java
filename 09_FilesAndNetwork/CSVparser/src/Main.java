import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String MOVEMENT_FILE = "data/movementList.csv";

    public static void main(String[] args) {
        Operation.expenseTypes.put(6536, "Денежные переводы");
        Operation.expenseTypes.put(5814, "Рестораны-закусочные");
        Operation.expenseTypes.put(6011, "Снятие наличности");
        Operation.expenseTypes.put(4121, "Лимузины и такси");
        Operation.expenseTypes.put(6538, "Денежные переводы");
        Operation.expenseTypes.put(5812, "Рестораны-закусочные");
        Operation.expenseTypes.put(7399, "Бизнес–сервис");
        Operation.expenseTypes.put(5995, "Зоомагазины");
        Operation.expenseTypes.put(5411, "Супермаркеты");
        Operation.expenseTypes.put(5968, "Прямой маркетинг – Торговые точки подписки");
        Operation.expenseTypes.put(5977, "Магазины косметики");
        Operation.expenseTypes.put(5818, "Цифровые товары");
        Operation.expenseTypes.put(7372, "Программирование, дизайн");
        Operation.expenseTypes.put(5691, "Магазины одежды");

        ArrayList<Operation> operations = loadOperationsFromFile();

        BigDecimal totalIncome = new BigDecimal(0);
        BigDecimal totalExpense = new BigDecimal(0);
        Map<String, BigDecimal> expenseByType = new TreeMap<>();

        for (Operation operation : operations) {
            if (operation.getType().equals(OperationType.INCOME)) {
                totalIncome = totalIncome.add(operation.getAmount());
            } else {
                totalExpense = totalExpense.add(operation.getAmount());
                BigDecimal oldExpenseByType = expenseByType.get(operation.getExpenseType());
                if (oldExpenseByType == null) {
                    oldExpenseByType = new BigDecimal(0);
                }
                expenseByType.put(operation.getExpenseType(), oldExpenseByType.add(operation.getAmount()));
            }
        }

        System.out.println("Сводная информация по банковской выписке:");
        System.out.println("------------------------------------------");
        System.out.println("Общий приход: " + totalIncome + " руб.");
        System.out.println("Общий расход: " + totalExpense + " руб.");
        System.out.println("\nРасходы по категориям:");
        System.out.println("-------------------------");
        BigDecimal check = new BigDecimal(0);
        for (Map.Entry<String, BigDecimal>
                type : expenseByType.entrySet()) {
            System.out.println(type.getKey() + ": " + type.getValue() + " руб.");
        }
    }

    private static ArrayList<Operation> loadOperationsFromFile() {
        ArrayList<Operation> operations = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(MOVEMENT_FILE));
            for (String line : lines) {
                String[] fragments = line.split(";");
                if (fragments.length != 8) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }

                OperationType type;
                BigDecimal amount;

                if (fragments[7].equals("0")) {
                    type = OperationType.INCOME;
                    amount = new BigDecimal(fragments[6].replace(',', '.'));
                } else if (fragments[6].equals("0")) {
                    type = OperationType.EXPENSE;
                    amount = new BigDecimal(fragments[7].replace(',', '.'));
                } else {
                    continue;
                }
                Matcher m = Pattern.compile(".*MCC([0-9]{4})").matcher(fragments[5]);
                int mcc = 0;
                if (m.find()) {
                    mcc = Integer.parseInt(m.group(1));
                }

                operations.add(new Operation(
                        type,
                        mcc,
                        amount
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return operations;
    }
}