/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.foobar.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bonobo
 * https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
 */
@Service
public class StudentService {
    private static final String INVALID_ID_MESSAGE = "student id: %s not found";    
    private static final String INVALID_EMAIL_MESSAGE = "email: %s invalid";

    private static final Log logger = LogFactory.getLog(StudentService.class);
    
    @Autowired
    private StudentRepository studentRepository;
    
//    @Autowired
//    public StudentService(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
    
    public List<Student> getStudents() {
//        List<Student> result = new ArrayList<>();
//        result.add(new Student(
//                1L,
//                "charlie nguyen",
//                "nguyen@gmail.com",
//                LocalDate.of(1981,Month.JANUARY, 16),
//                21
//                ));
        return studentRepository.findAll();
    }
    
    public void addNewStudent(Student student) {
        Optional<Student> result = 
                studentRepository.findStudentByEmail(student.getEmail());
        if (result.isPresent()) throw new IllegalEmailStateException(String.format(INVALID_EMAIL_MESSAGE, student.getEmail()));
        studentRepository.save(student);
    }

    public void deleteStudent(UUID id) {
        Student result = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStudentIdStateException(String.format(INVALID_ID_MESSAGE, id)));
        studentRepository.delete(result);
    }
    
    // https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
    @Transactional(timeout = 10)
    public void updateStudent(UUID id, String name, String email) {
        Student result = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStudentIdStateException(String.format(INVALID_ID_MESSAGE, id)));
        if (name != null && name.length() > 0 && 
                !Objects.equals(name, result.getName())) {
            result.setName(name);
        }
        if (email != null && email.length() > 0 && 
                !Objects.equals(email, result.getEmail())) {
            if(studentRepository.findStudentByEmail(email).isPresent())
                    throw new IllegalEmailStateException(String.format(INVALID_EMAIL_MESSAGE, email));
            result.setEmail(email);
        }
    }

    public Page<Student> findAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
    
    public Page<Student> getStudentsByDob(LocalDate dob, Pageable pageable) {
        return studentRepository.findStudentByDob(dob, pageable);
    }

    public Student findById(UUID id) {
        logger.debug("findById:" + id + " uuid:" + (id instanceof UUID));
        Student result = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStudentIdStateException(String.format(INVALID_ID_MESSAGE, id.toString())));
        return result;
    }

    public static final class IllegalStudentIdStateException extends IllegalStateException {
        public IllegalStudentIdStateException(String message) { super(message); }
    }
    
    public static final class IllegalEmailStateException extends IllegalStateException {
        public IllegalEmailStateException(String message) { super(message); }
    }
}
