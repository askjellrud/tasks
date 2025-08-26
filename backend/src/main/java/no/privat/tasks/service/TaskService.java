package no.privat.tasks.service;

import no.privat.tasks.model.Task;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private AtomicLong counter = new AtomicLong(0);
    private Map<Long, Task> tasks = new ConcurrentHashMap<>();

    public Collection<Task> getTasks() {
        return tasks.values();
    }

    public Optional<Task> getTask(Long id) {
        if (tasks.containsKey(id)) {
            return Optional.of(tasks.get(id));
        } else {
            return Optional.empty();
        }
    }

    public Task addTask(String title) {
        Long newId = counter.incrementAndGet();

        Task task = new Task();
        task.setId(newId);
        task.setTitle(title);
        task.setCompleted(false);

        tasks.put(newId, task);
        return task;
    }

    public boolean updateTitle(Long id, String title) {
        Optional<Task> task = getTask(id);

        if (!task.isPresent()) {
            return false;
        }

        task.get().setTitle(title);
        return true;
    }
    
    public boolean removeTask(Long id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
