package com.example.tasktodo.controller;

import com.example.tasktodo.config.ITableConfiguration;
import com.example.tasktodo.config.TableConfiguration;
import com.example.tasktodo.entity.Student;
import com.example.tasktodo.entity.StudentTask;
import com.example.tasktodo.service.StudentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final ITableConfiguration tableConfiguration;

    public StudentController(StudentService studentService, ITableConfiguration tableConfiguration) {
        this.studentService = studentService;
        this.tableConfiguration = tableConfiguration;
    }

    @GetMapping("/student")
    public String studentTaskList(@AuthenticationPrincipal Student student, Model model) {
        model.addAttribute("tableColor", tableConfiguration.getTableColor() + " table");
        model.addAttribute("tasks", studentService.findAllTaskForStudent(student.getId()));

        return "/student/tasks";
    }

    @GetMapping("/student/task/{id}")
    public String showTaskForm(@PathVariable long id, Model model){
        final Optional<StudentTask> studentTaskById = studentService.findStudentTaskById(id);
        if(studentTaskById.isPresent()) {
            model.addAttribute("task", studentTaskById.get());
            return "/student/task-form";
        }else {
            model.addAttribute("message", "Task not found");
            return "error";
        }
    }

    @GetMapping("/student/task-description/{id}")
    public String showTaskDescription(@PathVariable long id, Model model){
        final Optional<StudentTask> studentTaskById = studentService.findStudentTaskById(id);
        if(studentTaskById.isPresent()) {
            model.addAttribute("taskDescription", studentTaskById.get().getTask());
            return "/student/task-description";
        }else {
            model.addAttribute("message", "Task not found");
            return "error";
        }
    }

    @PostMapping("/student/finish-task")
    public String finishStudentTask(@ModelAttribute StudentTask studentTask){
        studentService.finishStudentTask(0, studentTask);
        return "redirect:/student";
    }
}
