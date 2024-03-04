package com.example.tasktodo.repository;

import com.example.tasktodo.entity.Student;
import com.example.tasktodo.entity.StudentTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentTaskRepository extends JpaRepository<StudentTask, Long> {
    List<StudentTask> findByStudent(Student student);
    Optional<StudentTask> findStudentTaskById(long id);
}
