package employee;

import company.Company;

import java.math.BigDecimal;

public class TopManager extends Worker implements Employee {
    private final BigDecimal SALES_PLAN = new BigDecimal("10000000");
    private final BigDecimal BONUS_PERCENT = new BigDecimal("150");

    public TopManager(String name, BigDecimal wage, Company company) {
        super(name, wage, company);
    }

    @Override
    public BigDecimal getMonthSalary() {
        BigDecimal bonus = new BigDecimal("0");
        if (company.getIncome(company).compareTo(SALES_PLAN) > 0) {
            bonus = this.getWage().multiply(BONUS_PERCENT).divide(BigDecimal.valueOf(100));
        }
        BigDecimal salary = this.getWage().add(bonus);
        return salary;
    }
}