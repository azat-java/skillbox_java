package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class TaskController {
    private final Storage storage;

    public TaskController(Storage storage) {
        this.storage = storage;
    }

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/tasks/", method = RequestMethod.GET)
    public ConcurrentHashMap<Integer, Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<>();
        taskIterable.forEach(task -> tasks.put(task.getId(), task));
        return tasks;
    }

    @RequestMapping(value = "/tasks/", method = RequestMethod.POST)
    public int add(Task task) {
        return taskRepository.save(task).getId();
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeTask(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.deleteById(id);
        return null;
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTask(@PathVariable int id, boolean done, String name) {
        if (!taskRepository.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.save(new Task(id, name, done));
        return null;
    }

    @RequestMapping(value = "/tasks/", method = RequestMethod.DELETE)
    public void removeAllTasks() {
        taskRepository.deleteAll();
    }
}
