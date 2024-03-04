package com.example.tasktodo.service;

import com.example.tasktodo.entity.Student;
import com.example.tasktodo.entity.StudentTask;
import com.example.tasktodo.repository.StudentRepository;
import com.example.tasktodo.repository.StudentTaskRepository;
import com.example.tasktodo.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JpaStudentService implements StudentService{

    private final StudentRepository studentRepository;
    private final StudentTaskRepository studentTaskRepository;
    private final TaskRepository taskRepository;

    @Autowired
    private LocalDateTime now;

    public JpaStudentService(StudentRepository studentRepository, StudentTaskRepository studentTaskRepository, TaskRepository taskRepository) {
        this.studentRepository = studentRepository;
        this.studentTaskRepository = studentTaskRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<StudentTask> findStudentTaskById(long id) {
        return studentTaskRepository.findStudentTaskById(id);
    }

    @Override
    public List<StudentTask> findAllTaskForStudent(long studentId) {
        final Optional<Student> opStudent = studentRepository.findById(studentId);

        if (opStudent.isPresent()){
            return studentTaskRepository.findByStudent(opStudent.get());
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public void finishStudentTask(long studentId, StudentTask task) {
        final Optional<StudentTask> opTask = studentTaskRepository.findById(task.getId());

        if (opTask.isPresent()){
            var originalTask = opTask.get();
            originalTask.setContent(task.getContent());
            originalTask.setFinishDate(now);
            studentTaskRepository.save(originalTask);
        }
    }
}
