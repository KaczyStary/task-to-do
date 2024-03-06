package com.example.tasktodo.controller;

import com.example.tasktodo.entity.Teacher;
import com.example.tasktodo.repository.StudentTaskRepository;
import com.example.tasktodo.service.StudentService;
import com.example.tasktodo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final StudentService studentService;

    public TeacherController(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping("")
    public String teacherPanel(@AuthenticationPrincipal Teacher teacher, Model model){

        model.addAttribute("students", teacherService.findAllStudentsByTeacherId(teacher.getId()));

        return "./teacher/teacher-panel";
    }

    @GetMapping("/teacher-student-panel/{id}")
    public String teacherStudentPanel(@PathVariable long id, Model model){

        model.addAttribute("studentInfo", teacherService.findStudentById(id));
        model.addAttribute("studentTasks", studentService.findAllTaskForStudent(id));
        return "./teacher/teacher-student-panel";
    }

}
