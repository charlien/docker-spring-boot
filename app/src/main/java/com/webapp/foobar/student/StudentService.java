/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.foobar.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bonobo
 */
@Service
public class StudentService {
    private static final String INVALID_ID_MESSAGE = "student id:%d not found";    
    private static final String INVALID_EMAIL_MESSAGE = "email:%s invalid";

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

    public void deleteStudent(Long id) {
//        Student result = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException());
        if(!studentRepository.existsById(id)) {
            throw new IllegalStudentIdStateException(String.format(INVALID_ID_MESSAGE, id));
        }
        studentRepository.deleteById(id);
    }
    
    
    @Transactional
    public void updateStudent(Long id, String name, String email) {
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
    
    public static final class IllegalStudentIdStateException extends IllegalStateException {
        public IllegalStudentIdStateException(String message) { super(message); }
    }
    
    public static final class IllegalEmailStateException extends IllegalStateException {
        public IllegalEmailStateException(String message) { super(message); }
    }
}
