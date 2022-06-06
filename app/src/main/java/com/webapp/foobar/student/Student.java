/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.foobar.student;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author bonobo
 * For Entity inheritance, look at MappedSuperclass
 * https://www.baeldung.com/hibernate-inheritance
 */
// hibernate needs to know its an entity and then table is for jpa
@Entity
@Table
public class Student {
    // required for JPA for Primary Key
    // https://tomharrisonjr.com/uuid-or-guid-as-primary-keys-be-careful-7b2aa3dcb439
    // need to define column type or else it becomes too large in the database and queries fail
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @Size(min=2, max=64)
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    
    @Column(nullable = true)
    private LocalDate dob;
        
    @Transient
    private Integer age;

    public Student() {}
    
    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }
    
    public Student(String name, String email) {
        this(name, email, null);
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<LocalDate> getDob() {
        return Optional.ofNullable(dob);
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return getDob().isPresent() ? Period.between(dob, LocalDate.now()).getYears() : 0;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + getAge() + '}';
    }
    
}
