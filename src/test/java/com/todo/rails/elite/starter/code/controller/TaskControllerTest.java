package com.todo.rails.elite.starter.code.controller;

import com.todo.rails.elite.starter.code.model.Task;
import com.todo.rails.elite.starter.code.service.TaskService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    private Task sampleTask;

    @BeforeEach
    void setUp() {
        // TODO: Initialize mocks
        // TODO: Initialize sampleTask object
        MockitoAnnotations.openMocks(this);
        sampleTask = new Task("Sample Task", "This is a sample task.", false, LocalDate.now());
    }

    @Test
    void getAllTasks_Success() throws Exception {
        // TODO: Mock taskService.getAllTasks() to return a list
        // TODO: Perform GET request to /api/tasks/all
        // TODO: Assert response status and JSON content
        when(taskService.getAllTasks()).thenReturn(List.of(sampleTask));
        mockMvc.perform(get("/api/tasks/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Sample Task"));
        //.andExpect(jsonPath("$[0].completed").value(false));
    }

    // TODO: Add more test cases for createTask, updateTask, deleteTask
}
