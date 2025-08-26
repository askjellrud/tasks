package no.privat.tasks.controller;

import no.privat.tasks.model.Task;
import no.privat.tasks.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<Collection<Task>> getAll() {
        return ResponseEntity.ok().body(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        Optional<Task> task = taskService.getTask(id);

        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(task.get());
    }

    @PostMapping()
    public ResponseEntity<Void> addTask(@RequestBody AddTask body) {
        Task task = taskService.addTask(body.getTitle());

        URI taskURI = URI.create("/tasks/" + task.getId());
        return ResponseEntity.created(taskURI).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable("id") Long id, @RequestBody UpdateTask body) {
        boolean updated = taskService.updateTitle(id, body.getTitle());

        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTask(@PathVariable("id") Long id) {
        boolean removed = taskService.removeTask(id);

        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
