import java.math.BigDecimal;

abstract class Worker implements Employee, Comparable<Worker> {
    private String name;
    private BigDecimal wage;
    Company company;
    protected BigDecimal income = new BigDecimal("0");

    Worker(String name, BigDecimal wage, Company company) {
        this.name = name;
        this.wage = wage;
        this.company = company;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getIncome() {
        return income;
    }

    @Override
    public int compareTo(Worker worker) {
        if (getMonthSalary().compareTo(worker.getMonthSalary()) >= 0) {
            return 1;
        } else {
            return -1;
        }
    }
}