package com.todo.rails.elite.starter.code.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO 12: Test Validation Logic. Write tests to verify that invalid inputs (e.g., empty title) are rejected.
public class TaskValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        } catch (Exception exception) {
            System.err.println("Error setting up validator: " + exception.getMessage());
        }
    }

    @Test
    void validateTask_Success() {
        // TODO 12: Create a valid Task object and assert no violations
        //Task task = null; // learner should initialize this
        Task task = new Task("Test", "Testing with a valid data", false, LocalDate.now());
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertTrue(violations.isEmpty());
    }

    @Test
    void validateTask_Failure_EmptyTitle() {
        // TODO 12: Create a Task object with empty title and assert violations exist
        //Task task = null; // learner should initialize this
        Task task = new Task("", "Description", false, LocalDate.now());
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertFalse(violations.isEmpty());
    }
}