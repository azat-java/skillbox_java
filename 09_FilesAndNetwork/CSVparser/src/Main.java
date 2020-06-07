import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String MOVEMENT_FILE = "data/movementList.csv";
    static List<MCC> expenseTypes = new ArrayList<>();

    public static void main(String[] args) {
        expenseTypes.add(new MCC(6536, "Денежные переводы"));
        expenseTypes.add(new MCC(5814, "Рестораны-закусочные"));
        expenseTypes.add(new MCC(6011, "Снятие наличности"));
        expenseTypes.add(new MCC(4121, "Лимузины и такси"));
        expenseTypes.add(new MCC(6538, "Денежные переводы"));
        expenseTypes.add(new MCC(5812, "Рестораны-закусочные"));
        expenseTypes.add(new MCC(7399, "Бизнес–сервис"));
        expenseTypes.add(new MCC(5995, "Зоомагазины"));
        expenseTypes.add(new MCC(5411, "Супермаркеты"));
        expenseTypes.add(new MCC(5968, "Прямой маркетинг – Торговые точки подписки"));
        expenseTypes.add(new MCC(5977, "Магазины косметики"));
        expenseTypes.add(new MCC(5818, "Цифровые товары"));
        expenseTypes.add(new MCC(7372, "Программирование, дизайн"));
        expenseTypes.add(new MCC(5691, "Магазины одежды"));

        List<Operation> operations = loadOperationsFromFile();

        BigDecimal totalIncome = getIncomeSum(operations);
        BigDecimal totalExpense = getExpenseSum(operations);
        Map<String, BigDecimal> expenseByType = getExpenseByType(operations);

        System.out.println("Сводная информация по банковской выписке:");
        System.out.println("------------------------------------------");
        System.out.println("Общий приход: " + totalIncome + " руб.");
        System.out.println("Общий расход: " + totalExpense + " руб.");
        System.out.println("\nРасходы по категориям:");
        System.out.println("-------------------------");
        for (Map.Entry<String, BigDecimal>
                type : expenseByType.entrySet()) {
            System.out.println(type.getKey() + ": " + type.getValue() + " руб.");
        }
    }

    private static List<Operation> loadOperationsFromFile() {
        List<Operation> operations = new ArrayList<>();
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
                        getExpenseType(mcc),
                        amount
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return operations;
    }

    private static String getExpenseType(Integer mcc) {
        Iterator<MCC> itr = expenseTypes.iterator();

        while (itr.hasNext()) {
            MCC current = itr.next();
            if (current.getId().equals(mcc)) {
                return current.getDescription();
            }
            ;
        }
        return "Прочие расходы";
    }

    private static BigDecimal getIncomeSum(List<Operation> operations) {
        BigDecimal totalIncome = new BigDecimal(0);

        for (Operation operation : operations) {
            if (operation.getType().equals(OperationType.INCOME)) {
                totalIncome = totalIncome.add(operation.getAmount());
            }
        }
        return totalIncome;
    }

    private static BigDecimal getExpenseSum(List<Operation> operations) {
        BigDecimal totalExpense = new BigDecimal(0);

        for (Operation operation : operations) {
            if (operation.getType().equals(OperationType.EXPENSE)) {
                totalExpense = totalExpense.add(operation.getAmount());
            }
        }
        return totalExpense;
    }

    private static Map getExpenseByType(List<Operation> operations) {
        Map<String, BigDecimal> expenseByType = new TreeMap<>();

        for (Operation operation : operations) {
            if (operation.getType().equals(OperationType.EXPENSE)) {
                BigDecimal oldExpenseByType = expenseByType.get(operation.getExpenseType());
                if (oldExpenseByType == null) {
                    oldExpenseByType = new BigDecimal(0);
                }
                expenseByType.put(operation.getExpenseType(), oldExpenseByType.add(operation.getAmount()));
            }
        }
        return expenseByType;
    }
}