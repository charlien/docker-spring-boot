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
                    add(new Student("charlie", "nguyen1@gmail.com", LocalDate.of(1981, Month.JANUARY, 16)));
                    add(new Student("charlie", "nguyen2@gmail.com", LocalDate.of(1981, Month.JANUARY, 16)));
                    add(new Student("charlie", "nguyen3@gmail.com", LocalDate.of(1981, Month.JANUARY, 16)));
                    add(new Student("charlie", "nguyen4@gmail.com", LocalDate.of(1981, Month.JANUARY, 16)));
                    add(new Student("charlie", "nguyen5@gmail.com", LocalDate.of(1981, Month.JANUARY, 16)));
                    add(new Student("charlie", "nguyen6@gmail.com", LocalDate.of(1981, Month.JANUARY, 16)));
                    add(new Student("charlie", "nguyen7@gmail.com", LocalDate.of(1981, Month.JANUARY, 16)));
                    add(new Student("charlie", "nguyen8@gmail.com", LocalDate.of(1981, Month.JANUARY, 16)));
                    add(new Student("kanita", "viranond1@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond2@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond3@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond4@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond5@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond6@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond7@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond8@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond9@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond10@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond11@gmail.com", LocalDate.of(1981, Month.JULY, 8)));
                    add(new Student("kanita", "viranond12@gmail.com", LocalDate.of(1981, Month.JULY, 8)));

                }
            };
            studentRepository.saveAll(students);
        };
    }
}
