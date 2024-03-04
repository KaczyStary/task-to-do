package com.example.tasktodo.service;

import com.example.tasktodo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeacherService {

    List<Student> findAllStudentsByTeacherId(long id);
    Student findStudentById(long id);
}
