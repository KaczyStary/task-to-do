package com.example.tasktodo.service;

import com.example.tasktodo.repository.TeacherRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TeacherDetailService implements UserDetailsService {

    private final TeacherRepository teacherRepository;

    public TeacherDetailService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return teacherRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }



}
