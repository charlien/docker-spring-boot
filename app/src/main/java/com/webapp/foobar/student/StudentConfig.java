/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.foobar.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author bonobo
 */
@Configuration
public class StudentConfig {

    // this just tells spring to run this method or "create" this bean to run this command
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            List<Student> students = new ArrayList<Student>() {
                {
                    add(new Student("charlie", "nguyen@gmail.com", LocalDate.of(1981, Month.JANUARY, 16)));
                    add(new Student("kanita", "viranond@gmail.com", LocalDate.of(1981, Month.JULY, 8)));

                }
            };
            studentRepository.saveAll(students);
        };
    }
}
