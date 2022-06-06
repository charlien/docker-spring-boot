/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.foobar.student;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bonobo
 * https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation
 * https://www.baeldung.com/spring-data-jpa-pagination-sorting
 * https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
 */
@Repository
//public interface StudentRepository extends JpaRepository<Student,UUID>, JpaSpecificationExecutor<Student> {
public interface StudentRepository extends JpaRepository<Student,UUID> {

    // This query is not required, but helpful to know the JBQL
    // Student is Student.class
    @Query("SELECT s FROM Student s WHERE s.email = :email")
    public Optional<Student> findStudentByEmail(@Param("email") String email);
    
//    public Optional<Student> findStudentById(UUID uuid);
    
    public Page<Student> findStudentByDob(LocalDate dob, Pageable pageable);
}
