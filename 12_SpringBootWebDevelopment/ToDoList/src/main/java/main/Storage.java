package main;

import org.springframework.stereotype.Service;
import response.Task;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Storage {
    private final ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicInteger taskId = new AtomicInteger(0);

    public ConcurrentHashMap<Integer, Task> getAllTasks() {
        return tasks;
    }

    public int addTask(Task task) {
        int id = taskId.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public void removeTask(Integer id) {
        tasks.remove(id);
    }

    public void updateTask(int id, boolean done, String name) {
        Task task = tasks.get(id);
        task.setDone(done);
        task.setName(name);
    }

    public void removeAllTasks() {
        tasks.clear();
    }
}
