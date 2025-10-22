package com.todo.rails.elite.starter.code.service;

import com.todo.rails.elite.starter.code.model.Task;
import com.todo.rails.elite.starter.code.repository.TaskRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// TODO 8: reformat code. Use your IDE's formatting tools to ensure consistent indentation and spacing.
// TODO 9: add method comments. Add method-level comments to explain the purpose and logic of methods.
// TODO 16: Log Exceptions. Use SLF4J to log exceptions in the service and controller layers.

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    // TODO 16: Add SLF4J logger here
    // Example: private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * This method add new tasks to the tasks lists on the DB,
     *
     * @param task the task details
     * @return returns the task information once it's been saved.
     * @throws RuntimeException when trying to add an existing task.
     */
    public Task addNewTask(@NotNull(message = "Task cannot be null") Task task) throws RuntimeException {
        if (taskRepository.findByTitle(task.getTitle()).isPresent()) {
            logger.warn("Attempt to add a task already exists {}", task.getTitle());
            throw new RuntimeException("Task already exists");
        }
        return taskRepository.save(task);
    }

    /**
     * This method returns a task from the database using a task id number
     *
     * @param id the id of a task
     * @return returns a task once found
     * @throws RuntimeException when a task doesn't exist
     */
    public Task getTaskById(@NotNull(message = "Id cannot be null") Long id) throws RuntimeException {
        return taskRepository.findById(id).orElseGet(() -> {
            logger.warn("Attempt to get a non-existent task using id: {}", id);
            throw new RuntimeException("Task not found");
        });
    }

    /**
     * This method returns a task by its title
     *
     * @param title the title of a task that we're looking for
     * @return returns a task once found
     * @throws RuntimeException when a task with provided title doesn't exist.
     */
    public Task getTaskByTitle(
            @NotNull(message = "Title cannot be null")
            @NotBlank(message = "Title cannot be blank")
            String title
    ) throws RuntimeException {
        return taskRepository.findByTitle(title)
                .orElseThrow(
                        () -> new RuntimeException("Task not found")
                );
    }

    /**
     * This method returns a list of all existing tasks.
     *
     * @return the list of existing tasks.
     */
    public List<Task> getAllTasks() {
        if (taskRepository.findAll().isEmpty()) {
            return List.of();
        }
        return taskRepository.findAll();
    }

    /**
     * Updates details of an existing task
     *
     * @param task the new task information.
     * @return returns details of the updated task
     * @throws RuntimeException when attempting to update a task that doesn't exist.
     */
    public Task updateExistingTask(@NotNull(message = "Task cannot be null") Task task) throws RuntimeException {
        // TODO 10: use meaningful names. Rename variables and methods for clarity. Ex - taskByTitle can be refactored to existingTask.
        //Optional<Task> taskByTitle = taskRepository.findByTitle(task.getTitle());
        Optional<Task> taskByTitle = taskRepository.findById(task.getId());
        if (taskByTitle.isEmpty()) {
            logger.warn("Attempt to update a non-existing task using Title: {}", task.getTitle());
            throw new RuntimeException("Task not found");
        }
        Task taskToUpdate = taskByTitle.get();
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setCompleted(task.isCompleted());
        taskToUpdate.setDueDate(task.getDueDate());
        System.out.println(taskToUpdate.toString());
        return taskRepository.save(taskToUpdate);
    }

    /**
     * Deletes a taks
     *
     * @param task the task to be deleted
     * @throws RuntimeException when attempting to delete a nonexistent task.
     */
    public void deleteTask(@NotNull(message = "Task cannot be null") Task task) throws RuntimeException {
        Optional<Task> taskByTitle = taskRepository.findByTitle(task.getTitle());
        if (taskByTitle.isEmpty()) {
            logger.warn("Attempt to delete a non-existing task using Title: {}", task.getTitle());
            throw new RuntimeException("Task not found");
        }
        taskRepository.delete(task);
    }

    /**
     * This method retreivs a list of pending tasks from the database
     *
     * @return the list of tasks that are currently pending.
     */
    public List<Task> getAllPendingTasks() {
        List<Task> allTasks = getAllTasks();
        if (allTasks.isEmpty()) {
            return List.of();
        }
        return allTasks.stream()
                .filter(task -> !task.isCompleted())
                .toList();
    }

    /**
     * This method retrives a list of completed tasks from the database
     *
     * @return the list of completed tasks.
     */
    public List<Task> getAllCompletedTasks() {
        List<Task> allTasks = getAllTasks();
        if (allTasks.isEmpty()) {
            return List.of();
        }
        return allTasks.stream()
                .filter(Task::isCompleted)
                .toList();
    }

    /**
     * This method retrives a list of tasks that are due today
     *
     * @return the list of that's that are due today.
     */
    public List<Task> getTasksDueToday() {
        List<Task> allTasks = getAllTasks();
        if (allTasks.isEmpty()) {
            return List.of();
        }
        return allTasks.stream()
                .filter(
                        task -> !task.isCompleted()
                )
                .filter(
                        task -> task.getDueDate()
                                .isEqual(LocalDate.now())
                )
                .toList();
    }
}
