package com.example.tasktodo.service;

import com.example.tasktodo.entity.Student;
import com.example.tasktodo.entity.StudentTask;
import com.example.tasktodo.entity.Task;
import com.example.tasktodo.entity.Teacher;
import com.example.tasktodo.repository.StudentRepository;
import com.example.tasktodo.repository.StudentTaskRepository;
import com.example.tasktodo.repository.TaskRepository;
import com.example.tasktodo.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JpaTeacherService implements TeacherService{

    private final TeacherRepository teacherRepository;
    private final StudentTaskRepository studentTaskRepository;
    private final TaskRepository taskRepository;

    @Autowired
    private StudentRepository studentRepository;

    public JpaTeacherService(TeacherRepository teacherRepository, StudentTaskRepository studentTaskRepository, TaskRepository taskRepository) {
        this.teacherRepository = teacherRepository;
        this.studentTaskRepository = studentTaskRepository;
        this.taskRepository = taskRepository;
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
    public Map<Long, List<StudentTask>> findAllTaskForStudentByTeacherId(long id) {
        final Optional<Teacher> opTeacher = teacherRepository.findById(id);
        if (opTeacher.isPresent()){
            final List<Student> students = opTeacher.get().getStudents();
            LinkedHashMap<Long, List<StudentTask>> studentTaskMap = new LinkedHashMap<>();
            for (Student student: students){
                studentTaskMap.put(student.getId(), studentTaskRepository.findByStudent(student));
            }
            return studentTaskMap;
        }else {
            return Collections.emptyMap();
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

    @Override
    public Task findTaskById(long id) {
        final Optional<Task> opTask = taskRepository.findById(id);
        if (opTask.isPresent()){
            return opTask.get();
        }else {
            throw new ObjectNotFoundException(id, "Task");
        }
    }

    @Override
    public StudentTask findStudentTaskByStudentIdAndTaskId(long studentId, long taskId) {
        final Optional<Student> opStudent = studentRepository.findById(studentId);
        if (opStudent.isPresent()){
            final Optional<StudentTask> opTask = studentTaskRepository.findById(taskId);
            if (opTask.isPresent()){
                return opTask.get();
            }else {
                throw new ObjectNotFoundException(taskId, "Task");
            }
        }else {
            throw new ObjectNotFoundException(studentId, "Student");
        }
    }

    @Override
    public List<Task> findAllTaskByTeacherId(long teacherId) {
        final Optional<Teacher> opTeacher = teacherRepository.findById(teacherId);
        if (opTeacher.isPresent()){
            return opTeacher.get().getTasks();
            //return studentTaskRepository.findByTeacher(opTeacher.get());
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public void rateStudentTask(StudentTask studentTask){
        final Optional<Student> opStudent = studentRepository.findById(studentTask.getStudent().getId());
        if (opStudent.isPresent()){
            var ogTask = findStudentTaskByStudentIdAndTaskId(opStudent.get().getId(), studentTask.getId());
            ogTask.setNote(studentTask.getNote());
            studentTaskRepository.save(ogTask);
        }else {
            throw new ObjectNotFoundException(studentTask.getStudent().getId(), "Student");
        }
    }

    @Override
    @Transactional
    public void deleteRateStudentTask(StudentTask studentTask){
        final Optional<Student> opStudent = studentRepository.findById(studentTask.getStudent().getId());
        if(opStudent.isPresent()){
            var ogTask = findStudentTaskByStudentIdAndTaskId(opStudent.get().getId(), studentTask.getId());
            ogTask.setNote(null);
            studentTaskRepository.save(ogTask);
        }else {
            throw new ObjectNotFoundException(studentTask.getStudent().getId(), "Student");
        }
    }

    @Override
    @Transactional
    public void updateTask(Task task) {
        final Optional<Task> opTask = taskRepository.findById(task.getId());
        if (opTask.isPresent()) {
            var ogTask = opTask.get();
            ogTask.setName(task.getName());
            ogTask.setMax(task.getMax());
            if (task.getDeadline()!= null){
                ogTask.setDeadline(task.getDeadline());
            }
            ogTask.setDescription(task.getDescription());
            taskRepository.save(ogTask);
        }else {
            throw new ObjectNotFoundException(task.getId(), "Task");
        }
    }
}
