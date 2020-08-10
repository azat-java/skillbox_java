package main;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import response.Task;

import java.util.List;

@RestController
public class TaskController {
    @RequestMapping(value = "/tasks/", method = RequestMethod.GET)
    public List<Task> list() {
        return Storage.getAllTasks();
    }

    @RequestMapping(value = "/tasks/", method = RequestMethod.POST)
    public int add(Task task) {
        return Storage.addTask(task);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public boolean removeTask(@PathVariable int id) {
        return Storage.removeTask(id);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public boolean updateTask(@PathVariable int id, boolean done, String name) {
        return Storage.updateTask(id, done, name);
    }

    @RequestMapping(value = "/tasks/", method = RequestMethod.DELETE)
    public void removeAllTasks() {
        Storage.removeAllTasks();
    }

}
