package com.example.tasktodo.service;

import com.example.tasktodo.entity.StudentTask;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    Optional<StudentTask> findStudentTaskById(long id);
    List<StudentTask> findAllTaskForStudent(long studentId);
    void finishStudentTask(long studentId, StudentTask task);
    void deleteStudentTask(long studentId, StudentTask task);


}
