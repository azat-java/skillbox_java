import java.math.BigDecimal;

public class Operation {
    private OperationType type;
    private String expenseType;

    private BigDecimal amount;

    public Operation(OperationType type, String expenseType, BigDecimal amount) {
        this.type = type;
        this.expenseType = expenseType;
        this.amount = amount;
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