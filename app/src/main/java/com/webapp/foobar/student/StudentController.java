/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.foobar.student;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bonobo
 */
@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {
    
    private static final Log logger = LogFactory.getLog(StudentController.class);
        
    @Autowired
    private StudentService studentService;

//    @Autowired
//    public StudentController(StudentService studentService) { 
//        this.studentService = studentService;
//    }
    
//    @GetMapping
//    public List<Student> getStudents() {
//        return studentService.getStudents();
//    }
    
    @GetMapping(path="{studentId}")
    public Student findStudent(@PathVariable("studentId") UUID id) {
        logger.debug("Searching for:" + id);
        return studentService.findById(id);
    }
    
    @GetMapping
    public Page<Student> findAllStudents(@PageableDefault(value=5, page=0) Pageable pageable) {
        return studentService.findAllStudents(pageable);
    }
    
    // probably a better approach is to use a filter api with search object and
    // implement some simple query semantics
    @GetMapping(path="dob/{dob}")
    public Page<Student> findStudentsbyDob(@PathVariable("dob") String dob,
            @PageableDefault(value=5, page=0) Pageable pageable) {
        return studentService.getStudentsByDob(LocalDate.parse(dob), pageable);
    }
            
    // Use request body to consume content of the post
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        Objects.requireNonNull(student, "no student provided");
        studentService.addNewStudent(student);
    }
    
    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") UUID id) {
        Objects.requireNonNull(id, "id cannot be empty");
        studentService.deleteStudent(id);
    }
    
    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") UUID id, 
            @RequestParam(required = false) String name, 
            @RequestParam(required = false) String email) {
        studentService.updateStudent(id, name, email);
    }
            

}
