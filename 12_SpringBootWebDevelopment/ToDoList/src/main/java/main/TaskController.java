package main;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import response.Task;

import java.util.concurrent.ConcurrentHashMap;

@RestController
public class TaskController {
    private final Storage storage;

    public TaskController(Storage storage) {
        this.storage = storage;
    }

    @RequestMapping(value = "/tasks/", method = RequestMethod.GET)
    public ConcurrentHashMap<Integer, Task> list() {
        return storage.getAllTasks();
    }

    @RequestMapping(value = "/tasks/", method = RequestMethod.POST)
    public int add(Task task) {
        return storage.addTask(task);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public void removeTask(@PathVariable int id) {
        storage.removeTask(id);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public void updateTask(@PathVariable int id, boolean done, String name) {
        storage.updateTask(id, done, name);
    }

    @RequestMapping(value = "/tasks/", method = RequestMethod.DELETE)
    public void removeAllTasks() {
        storage.removeAllTasks();
    }
}
