package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repo){
        return args -> {
            Student juan = new Student(
                    "Juan",
                    LocalDate.of(2002,5,11),
                    "jmsanchezdiazdev@gmail.com");

            Student eze = new Student(
                    "Eze",
                    LocalDate.of(2000,12,1),
                    "ezecapo123@gmail.com");

            repo.saveAll(List.of(juan,eze));
        };
    }
}
