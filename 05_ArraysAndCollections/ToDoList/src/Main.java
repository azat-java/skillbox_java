import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> toDoList = new ArrayList<>();
        while (true) {
            System.out.println("Введите команду: ");
            Scanner scanner = new Scanner(System.in);
            String commandLine = scanner.nextLine();
            commandLine.trim();
            String commands[] = commandLine.split("\\s+");

            if (commands[0].equals("LIST")) {
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println(i + " " + toDoList.get(i));
                }
            }
            if (commands[0].equals("ADD")) {
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
            if (commands[0].equals("EDIT") && commands.length >= 3 && commands[1].matches("[0-9]+") && Integer.parseInt(commands[1]) < toDoList.size()) {
                String task = "";
                for (int i = 2; i < commands.length; i++) {
                    task += commands[i] + " ";
                }
                if (task.trim().length() != 0) {
                    toDoList.set(Integer.parseInt(commands[1]), task.trim());
                }
            }
            if (commands[0].equals("DELETE") && commands.length == 2 && commands[1].matches("[0-9]+") && Integer.parseInt(commands[1]) < toDoList.size()) {
                toDoList.remove(Integer.parseInt(commands[1]));
            }
        }
    }
}