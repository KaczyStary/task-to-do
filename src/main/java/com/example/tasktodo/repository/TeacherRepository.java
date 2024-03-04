package com.example.tasktodo.repository;

import com.example.tasktodo.entity.Student;
import com.example.tasktodo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findById(long id);
}
