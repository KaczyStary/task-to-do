package com.example.tasktodo.config;

import com.example.tasktodo.service.StudentDetailService;
import com.example.tasktodo.service.TeacherDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration{

    private final StudentDetailService studentDetailService;
    private final TeacherDetailService teacherDetailService;

    public SecurityConfiguration(StudentDetailService studentDetailService, TeacherDetailService teacherDetailService) {
        this.studentDetailService = studentDetailService;
        this.teacherDetailService = teacherDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers((headers) -> headers.disable())
                .formLogin(withDefaults())
                .logout((log)->
                        log.logoutSuccessUrl("/"))
                .authorizeHttpRequests((aut)->
                        aut
                                .requestMatchers("/student", "/student/**").hasRole("STUDENT")
                                .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
                                .requestMatchers("/teacher", "/teacher/**").hasRole("TEACHER")
                                .anyRequest().permitAll()
                );

        return http.build();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//
//
//        return http.build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return   http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(studentDetailService)
                .passwordEncoder(passwordEncoder())
                .and()
                .userDetailsService(teacherDetailService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
