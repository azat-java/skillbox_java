import java.math.BigDecimal;
import java.util.TreeMap;

public class Operation {
    private OperationType type;
    private String expenseType;

    private BigDecimal amount;
    static TreeMap<Integer, String> expenseTypes = new TreeMap<>();

    public Operation(OperationType type, Integer mcc, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
        if (expenseTypes.get(mcc) != null) {
            expenseType = expenseTypes.get(mcc);
        } else {
            expenseType = "Доход";
        }
    }

    public OperationType getType() {
        return type;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
