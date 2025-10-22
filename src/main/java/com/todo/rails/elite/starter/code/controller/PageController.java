package com.todo.rails.elite.starter.code.controller;

import com.todo.rails.elite.starter.code.model.Task;
import com.todo.rails.elite.starter.code.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class PageController {

    private final TaskService taskService;

    @Autowired
    public PageController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getHomePage(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("totalPendingToday", taskService.getTasksDueToday().size());
        model.addAttribute("totalPending", taskService.getAllPendingTasks().size());
        model.addAttribute("totalCompleted", taskService.getAllCompletedTasks().size());
        model.addAttribute("totalTasks", taskService.getAllTasks());
        model.addAttribute("todayTasks", taskService.getTasksDueToday());
        model.addAttribute("pendingTasks", taskService.getAllPendingTasks());
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/tasks")
    public String getTasksPage(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    @GetMapping("/tasks/view/{id}")
    public String viewTaskDetails(@PathVariable("id") Long id, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("task", taskService.getTaskById(id));
        return "details";
    }

    @GetMapping("/tasks/add")
    public String getAddTaskPage(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("task", new Task("", "", false, LocalDate.now()));
        return "add";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
            model.addAttribute("totalTasks", taskService.getAllTasks().size());
        }
        return "profile";
    }

    @GetMapping("/edit")
    public String getEditTaskPage(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "edit";
    }

}
