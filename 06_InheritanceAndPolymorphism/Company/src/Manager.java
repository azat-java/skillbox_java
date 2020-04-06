import java.math.BigDecimal;

public class Manager extends Worker implements Employee {
    private final BigDecimal BONUS_PERCENT = new BigDecimal("5");

    Manager(String name, BigDecimal wage, Company company, BigDecimal income) {
        super(name, wage, company);
        this.income = income;
    }

    @Override
    public BigDecimal getMonthSalary() {
        BigDecimal bonus = BONUS_PERCENT.multiply(income).divide(new BigDecimal("100"));
        BigDecimal salary = this.getWage().add(bonus);
        return salary;
    }
}