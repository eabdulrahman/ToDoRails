package com.todo.rails.elite.starter.code.error;

import com.todo.rails.elite.starter.code.model.Task;
import com.todo.rails.elite.starter.code.repository.TaskRepository;
import com.todo.rails.elite.starter.code.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class Errorhandlingtest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        // TODO: Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTaskById_NotFound() {
        // TODO: Mock repository to simulate task not found
        // TODO: Assert that appropriate exception is thrown
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            taskService.getTaskById(1L);
        });
        assertEquals("Task not found", exception.getMessage());
    }

    // TODO: Add more tests for other error scenarios
}