package company;

import employee.Employee;
import employee.IncomeReceivable;
import employee.Manager;
import employee.Worker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Company {
    private String name;
    public TreeSet<Worker> employees = new TreeSet<>();

    public Company(String name) {
        this.name = name;
    }

    public void hire(Worker worker) {
        employees.add(worker);
    }

    public void hireAll(ArrayList<Worker> workers) {
        employees.addAll(workers);
    }

    public void fire(Iterator worker) {
        worker.next();
        worker.remove();
        worker.next();
    }

    public BigDecimal getIncome(Company company) {
        Iterator<Worker> itr = employees.iterator();
        BigDecimal income = new BigDecimal("0");
        while (itr.hasNext()) {
            if (itr.next() instanceof IncomeReceivable) {
                income = income.add(((Manager) itr.next()).getIncome());
            }
        }
        return income;
    }

    private List<Employee> getSalaries(Iterator<Worker> itr, int count) {
        int i = 0;
        ArrayList<Employee> salaryStaff = new ArrayList<>();
        while (itr.hasNext() && i < count) {
            salaryStaff.add(itr.next());
            i++;
        }
        return salaryStaff;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        Iterator<Worker> itr = employees.descendingIterator();
        return getSalaries(itr, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        Iterator<Worker> itr = employees.iterator();
        return getSalaries(itr, count);
    }

    public void printTopSalaries(int count) {
        System.out.printf("Highest %d salaries are:\n", count);
        List<Employee> getTopSalaryStaff = getTopSalaryStaff(count);
        Iterator<Employee> itr = getTopSalaryStaff.iterator();
        int i = 1;
        while (itr.hasNext()) {
            System.out.println(i + ". " + itr.next().getMonthSalary() + " ");
            i++;
        }
    }

    public void printLowestSalaries(int count) {
        System.out.printf("Lowest %d salaries are:\n", count);
        List<Employee> getLowestSalaryStaff = getLowestSalaryStaff(count);
        Iterator<Employee> itr = getLowestSalaryStaff.iterator();
        int i = 1;
        while (itr.hasNext()) {
            System.out.println(i + ". " + itr.next().getMonthSalary() + " ");
            i++;
        }
    }
}