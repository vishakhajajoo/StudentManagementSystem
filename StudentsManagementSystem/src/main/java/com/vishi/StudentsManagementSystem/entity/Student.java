package com.vishi.StudentsManagementSystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
    public Student(int id, String firstName, String lastName, String email ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Student() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(name="first_name")
    public String firstName;
    @Column(name="last_name")
    public String lastName;
    public String email;

}
