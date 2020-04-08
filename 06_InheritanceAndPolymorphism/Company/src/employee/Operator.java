package employee;

import company.Company;

import java.math.BigDecimal;

public class Operator extends Worker implements Employee {

    public Operator(String name, BigDecimal salary, Company company) {
        super(name, salary, company);
    }

    @Override
    public BigDecimal getMonthSalary() {
        return getWage();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}