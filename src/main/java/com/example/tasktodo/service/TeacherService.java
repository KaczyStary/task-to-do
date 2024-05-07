package com.example.tasktodo.service;

import com.example.tasktodo.entity.Student;
import com.example.tasktodo.entity.StudentTask;
import com.example.tasktodo.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TeacherService {

    List<Student> findAllStudentsByTeacherId(long id);
    Map<Long, List<StudentTask>> findAllTaskForStudentByTeacherId(long id);
    Student findStudentById(long id);
    Task findTaskById(long id);
    StudentTask findStudentTaskByStudentIdAndTaskId(long studentId, long taskId);
    List<Task> findAllTaskByTeacherId(long teacherId);
    @Transactional
    void rateStudentTask(StudentTask task);
    @Transactional
    void deleteRateStudentTask(StudentTask studentTask);
    @Transactional
    void updateTask(Task task);
}
