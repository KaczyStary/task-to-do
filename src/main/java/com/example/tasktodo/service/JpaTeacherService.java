package com.example.tasktodo.service;

import com.example.tasktodo.entity.Student;
import com.example.tasktodo.entity.Teacher;
import com.example.tasktodo.repository.StudentRepository;
import com.example.tasktodo.repository.TeacherRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JpaTeacherService implements TeacherService{

    private final TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    public JpaTeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> findAllStudentsByTeacherId(long id) {
        final Optional<Teacher> opTeacher = teacherRepository.findById(id);
        if (opTeacher.isPresent()){
            return opTeacher.get().getStudents();
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public Student findStudentById(long id) {
        final Optional<Student> opStudent = studentRepository.findById(id);
        if (opStudent.isPresent()) {
            return opStudent.get();
        }else {
            throw new ObjectNotFoundException(id, "Student");
        }
    }
}
