package employee;

import company.Company;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class Salesman extends Worker implements Employee, IncomeReceivable {
    private final int MIN_INCOME_BOUND = 40000;
    private final int MAX_INCOME_BOUND = 50000;

    public Salesman(String name, Company company) {
        super(name, new BigDecimal("10000"), company);
        int randIncome = ThreadLocalRandom.current().nextInt(MIN_INCOME_BOUND, MAX_INCOME_BOUND + 1);
        income = BigDecimal.valueOf(randIncome);
    }

    @Override
    public BigDecimal getMonthSalary() {
        return getWage();
    }

    @Override
    public BigDecimal getIncome() {
        return income;
    }
}
