package no.privat.tasks.service;

import no.privat.tasks.model.Task;
import no.privat.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TaskService {

    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Collection<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            return Optional.of(task.get());
        } else {
            return Optional.empty();
        }
    }

    public Task addTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);

        taskRepository.save(task);
        return task;
    }

    public boolean updateTitle(Long id, String title) {
        Optional<Task> task = getTask(id);

        if (!task.isPresent()) {
            return false;
        }

        task.get().setTitle(title);
        taskRepository.save(task.get());
        return true;
    }

    public boolean removeTask(Long id) {
        Optional<Task> task = getTask(id);

        if (!task.isPresent()) {
            return false;
        }

        taskRepository.deleteById(id);
        return true;
    }
}
