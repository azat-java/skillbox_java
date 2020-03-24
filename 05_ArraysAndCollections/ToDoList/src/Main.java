import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<String> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        while (true) {
            System.out.println("Введите команду: ");
            String commandLine = scanner.nextLine();
            commandLine.trim();
            String commands[] = commandLine.split("\\s+");
            if (commands.length == 0) {
                continue;
            } else if (commands[0].equals("LIST")) {
                main.printList();
            } else if (commands[0].equals("ADD")) {
                main.add(commands);
            } else if (commands[0].equals("EDIT") && commands.length >= 3 && commands[1].matches("[0-9]+") && Integer.parseInt(commands[1]) < main.toDoList.size()) {
                main.edit(commands);
            } else if (commands[0].equals("DELETE") && commands.length == 2 && commands[1].matches("[0-9]+") && Integer.parseInt(commands[1]) < main.toDoList.size()) {
                main.delete(Integer.parseInt(commands[1]));
            }
        }
    }

    void printList() {
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(i + " " + toDoList.get(i));
        }
    }

    void add(String commands[]) {
        if (commands.length >= 2) {
            int taskStartsFrom;
            int priority = toDoList.size();
            if (commands[1].matches("[0-9]+")) {
                taskStartsFrom = 2;
                if (Integer.parseInt(commands[1]) <= priority) {
                    priority = Integer.parseInt(commands[1]);
                }
            } else {
                taskStartsFrom = 1;
            }
            String task = "";
            for (int i = taskStartsFrom; i < commands.length; i++) {
                task += commands[i] + " ";
            }
            if (task.trim().length() != 0) {
                toDoList.add(priority, task.trim());
            }
        }
    }

    void edit(String commands[]) {
        String task = "";
        for (int i = 2; i < commands.length; i++) {
            task += commands[i] + " ";
        }
        if (task.trim().length() != 0) {
            toDoList.set(Integer.parseInt(commands[1]), task.trim());
        }
    }

    void delete(int task) {
        toDoList.remove(task);
    }
}