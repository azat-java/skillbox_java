package main;

import response.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    private final static List<Task> tasks = Collections.synchronizedList(new ArrayList<>());

    public static List<Task> getAllTasks() {
        return tasks;
    }

    public synchronized static int addTask(Task task) {
        int id = 1;
        if (!tasks.isEmpty()) {
            id = tasks.get(tasks.size() - 1).getId() + 1;
        }
        task.setId(id);
        tasks.add(task);
        return id;
    }

    public synchronized static boolean removeTask(Integer id) {
        if (getTaskById(id) != null) {
            tasks.remove(getTaskById(id));
            return true;
        }
        return false;
    }

    public synchronized static boolean updateTask(int id, boolean done, String name) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setDone(done);
            task.setName(name);
            return true;
        }
        return false;
    }

    private static Task getTaskById(Integer id) {
        return tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findAny()
                .orElse(null);
    }

    public static void removeAllTasks() {
        tasks.clear();
    }
}
