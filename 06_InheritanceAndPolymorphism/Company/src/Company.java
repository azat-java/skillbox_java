import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class Company {
    private BigDecimal income = new BigDecimal("0");
    private String name;
    TreeSet<Worker> employees = new TreeSet<>();

    public Company(String name) {
        this.name = name;
    }

    void hire(Worker worker) {
        employees.add(worker);
    }

    void hireAll(ArrayList<Worker> workers) {
        employees.addAll(workers);
    }

    void fire(Iterator worker) {
        worker.next();
        worker.remove();
        worker.next();
    }

    protected BigDecimal getIncome(Company company) {
        Iterator<Worker> itr = employees.iterator();
        while (itr.hasNext()) {
            income = income.add(itr.next().getIncome());
        }
        return company.income;
    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {
        Iterator<Worker> itr = employees.descendingIterator();
        int i = 0;
        ArrayList<Employee> topSalaryStaff = new ArrayList<>();
        while (itr.hasNext() && i < count) {
            topSalaryStaff.add(itr.next());
            i++;
        }
        return topSalaryStaff;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        Iterator<Worker> itr = employees.iterator();
        int i = 0;
        ArrayList<Employee> lowestSalaryStaff = new ArrayList<>();
        while (itr.hasNext() && i < count) {
            lowestSalaryStaff.add(itr.next());
            i++;
        }
        return lowestSalaryStaff;
    }

    public void printTopSalaries(int count) {
        System.out.printf("Highest %d salaries are:\n", count);
        ArrayList<Employee> getTopSalaryStaff = getTopSalaryStaff(count);
        Iterator<Employee> itr = getTopSalaryStaff.iterator();
        int i = 1;
        while (itr.hasNext()) {
            System.out.println(i + ". " + itr.next().getMonthSalary() + " ");
            i++;
        }
    }

    public void printLowestSalaries(int count) {
        System.out.printf("Lowest %d salaries are:\n", count);
        ArrayList<Employee> getLowestSalaryStaff = getLowestSalaryStaff(count);
        Iterator<Employee> itr = getLowestSalaryStaff.iterator();
        int i = 1;
        while (itr.hasNext()) {
            System.out.println(i + ". " + itr.next().getMonthSalary() + " ");
            i++;
        }
    }
}