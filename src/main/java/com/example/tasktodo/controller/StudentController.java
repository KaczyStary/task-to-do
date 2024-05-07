package com.example.tasktodo.controller;

import com.example.tasktodo.config.IAppConfiguration;
import com.example.tasktodo.config.ITableConfiguration;
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
    private final IAppConfiguration appConfiguration;

    public StudentController(StudentService studentService, ITableConfiguration tableConfiguration, IAppConfiguration appConfiguration) {
        this.studentService = studentService;
        this.tableConfiguration = tableConfiguration;
        this.appConfiguration = appConfiguration;
    }

    @GetMapping("/student")
    public String studentTaskList(@AuthenticationPrincipal Student student, Model model) {
        model.addAttribute("tableColor", tableConfiguration.getTableColor() + " table");
        model.addAttribute("tasks", studentService.findAllTaskForStudent(student.getId()));
        model.addAttribute("pageTheme", appConfiguration.getPageTheme());

        return "./student/tasks";
    }

    @GetMapping("/student/home")
    public String homeStudent(@AuthenticationPrincipal Student student, Model model){
        model.addAttribute("tasks", studentService.findAllTaskForStudent(student.getId()));
        model.addAttribute("pageTheme", appConfiguration.getPageTheme());

        return "./student/home-student";
    }

    @GetMapping("/student/task/{id}")
    public String showTaskForm(@PathVariable long id, Model model){
        final Optional<StudentTask> studentTaskById = studentService.findStudentTaskById(id);
        model.addAttribute("pageTheme", appConfiguration.getPageTheme());

        if(studentTaskById.isPresent()) {
            model.addAttribute("task", studentTaskById.get());
            return "./student/task-form";
        }else {
            model.addAttribute("message", "Task not found");
            return "error";
        }
    }

    @GetMapping("/student/task-edit/{id}")
    public String showTaskEdit(@PathVariable long id, Model model){
        final Optional<StudentTask> studentTaskById = studentService.findStudentTaskById(id);
        model.addAttribute("pageTheme", appConfiguration.getPageTheme());

        if(studentTaskById.isPresent()) {
            model.addAttribute("task", studentTaskById.get());
            return "./student/task-form";
        }else {
            model.addAttribute("message", "Task not found");
            return "error";
        }
    }

    @GetMapping("/student/task-delete/{id}")
    public String deleteStudentTask(@PathVariable long id, @AuthenticationPrincipal Student student, Model model){
        Optional<StudentTask> opTask = studentService.findStudentTaskById(id);
        model.addAttribute("pageTheme", appConfiguration.getPageTheme());

        if(opTask.isPresent()) {
            studentService.deleteStudentTask(student.getId(), opTask.get());
            return "redirect:/student/home";
        } else {
            model.addAttribute("message", "StudentTask not found");
            return "error";
        }
    }

    @GetMapping("/student/task-description/{id}")
    public String showTaskDescription(@PathVariable long id, Model model){
        final Optional<StudentTask> studentTaskById = studentService.findStudentTaskById(id);
        model.addAttribute("pageTheme", appConfiguration.getPageTheme());

        if(studentTaskById.isPresent()) {
            model.addAttribute("taskDescription", studentTaskById.get().getTask());
            return "./student/task-description";
        }else {
            model.addAttribute("message", "Task not found");
            return "error";
        }
    }

    @PostMapping("/student/finish-task")
    public String finishStudentTask(@ModelAttribute StudentTask studentTask){
        studentService.finishStudentTask(0, studentTask);
        return "redirect:/student/home";
    }

}
