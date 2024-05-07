package com.example.tasktodo.controller;

import com.example.tasktodo.config.IAppConfiguration;
import com.example.tasktodo.entity.StudentTask;
import com.example.tasktodo.entity.Task;
import com.example.tasktodo.entity.Teacher;
import com.example.tasktodo.service.StudentService;
import com.example.tasktodo.service.TeacherService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final IAppConfiguration appConfiguration;

    public TeacherController(TeacherService teacherService, StudentService studentService, IAppConfiguration appConfiguration) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.appConfiguration = appConfiguration;
    }

    @GetMapping("/students")
    public String teacherPanel(@AuthenticationPrincipal Teacher teacher, Model model){
        model.addAttribute("students", teacherService.findAllStudentsByTeacherId(teacher.getId()));
        model.addAttribute("studentsTasks", teacherService.findAllTaskForStudentByTeacherId(teacher.getId()));
        model.addAttribute("tasks", teacherService.findAllTaskByTeacherId(teacher.getId()));
        model.addAttribute("pageTheme", appConfiguration.getPageTheme());

        return "./teacher/teacher-panel";
    }

    @GetMapping("/teacher-student-panel/{id}")
    public String teacherStudentPanel(@PathVariable long id, Model model){
        model.addAttribute("studentInfo", teacherService.findStudentById(id));
        model.addAttribute("studentTasks", studentService.findAllTaskForStudent(id));
        model.addAttribute("pageTheme", appConfiguration.getPageTheme());

        return "./teacher/teacher-student-panel";
    }

    @GetMapping("/edit-task/{taskId}")
    public String showEditTask(@PathVariable long taskId, Model model){
        model.addAttribute("task", teacherService.findTaskById(taskId));
        model.addAttribute("pageTheme", appConfiguration.getPageTheme());

        return "./teacher/edit-task";
    }

//    @GetMapping("/task-teacher-panel/{studentId}/{taskId}")
//    public String teacherTaskPanel(@PathVariable long studentId, @PathVariable long taskId, Model model){
//        model.addAttribute("task", teacherService.findStudentTaskByStudentIdAndTaskId(studentId, taskId));
//        model.addAttribute("pageTheme", appConfiguration.getPageTheme());
//
//        return "./teacher/task-teacher-panel";
//    }

    @GetMapping("/task-teacher-panel")
    public String teacherTaskPanel(@RequestParam long studentId, @RequestParam long taskId, Model model){
        model.addAttribute("task", teacherService.findStudentTaskByStudentIdAndTaskId(studentId, taskId));
        model.addAttribute("pageTheme", appConfiguration.getPageTheme());

        return "./teacher/task-teacher-panel";
    }

    @PostMapping("/edit-task")
    public String editTask(@ModelAttribute Task task){
        teacherService.updateTask(task);
        return "redirect:/teacher/edit-task/" + task.getId();
    }
    @PostMapping("/rate-task")
    public String rateStudentTask(@ModelAttribute StudentTask studentTask){
        teacherService.rateStudentTask(studentTask);
        return "redirect:/teacher/task-teacher-panel?studentId=" + studentTask.getStudent().getId() + "&taskId=" + studentTask.getTask().getId();
    }

    @PostMapping("/unRate-task")
    public String unRateStudentTask(@ModelAttribute StudentTask studentTask){
        teacherService.deleteRateStudentTask(studentTask);
        return "redirect:/teacher/task-teacher-panel?studentId=" + studentTask.getStudent().getId() + "&taskId=" + studentTask.getTask().getId();
    }

}
