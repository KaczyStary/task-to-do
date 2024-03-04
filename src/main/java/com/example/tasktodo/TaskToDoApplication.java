package com.example.tasktodo;

import com.example.tasktodo.config.AppConfiguration;
import com.example.tasktodo.config.IAppConfiguration;
import com.example.tasktodo.entity.Student;
import com.example.tasktodo.entity.StudentTask;
import com.example.tasktodo.entity.Task;
import com.example.tasktodo.entity.Teacher;
import com.example.tasktodo.repository.StudentRepository;
import com.example.tasktodo.repository.StudentTaskRepository;
import com.example.tasktodo.repository.TaskRepository;
import com.example.tasktodo.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TaskToDoApplication implements CommandLineRunner {

    private final StudentTaskRepository studentTaskRepository;
    private final StudentRepository studentRepository;
    private final TaskRepository taskRepository;
    private final TeacherRepository teacherRepository;

    private final IAppConfiguration appConfiguration;

    public TaskToDoApplication(StudentTaskRepository studentTaskRepository, StudentRepository studentRepository, TaskRepository taskRepository, TeacherRepository teacherRepository, IAppConfiguration appConfiguration) {
        this.studentTaskRepository = studentTaskRepository;
        this.studentRepository = studentRepository;
        this.taskRepository = taskRepository;
        this.teacherRepository = teacherRepository;
        this.appConfiguration = appConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskToDoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(appConfiguration.getPassword());
        seedData();
    }

    public void seedData(){
        final Task task1 = taskRepository.save(Task.builder()
                .name("Task 1")
                .description("Description 1")
                .deadline(LocalDateTime.of(2024, 1, 1, 0, 0, 0))
                .max(5)
                .build());

        final Task task2 = taskRepository.save(Task.builder()
                .name("Task 2")
                .description("Description 2")
                .deadline(LocalDateTime.of(2024, 1, 1, 0, 0, 0))
                .max(5)
                    .build());

        final Student student1 = studentRepository.save(Student.builder()
                .firstName("Student 1")
                .lastName("Student 1")
                .email("student1")
                .password(new BCryptPasswordEncoder().encode("student1"))
                .role("ROLE_STUDENT")
                .enabled(true)
                    .build()
        );

        final Student student2 = studentRepository.save(Student.builder()
                .firstName("Student 2")
                .lastName("Student 2")
                .email("student2")
                .password(new BCryptPasswordEncoder().encode("student2"))
                .role("ROLE_STUDENT")
                .enabled(true)
                    .build()
        );

        final Teacher teacher1 = teacherRepository.save(Teacher.builder()
                .firstName("Teacher 1")
                .lastName("Teacher 1")
                .email("teacher1")
                .password(new BCryptPasswordEncoder().encode("teacher1"))
                .role("ROLE_TEACHER")
                .enabled(true)
                .students(List.of(student1, student2))
                    .build()
        );


        studentTaskRepository.save(StudentTask.builder().task(task1).student(student1).build());
        studentTaskRepository.save(StudentTask.builder().task(task2).student(student1).build());
        studentTaskRepository.save(StudentTask.builder().task(task1).student(student2).build());
    }
}
