package com.hibernate.annotation.repository;

import com.hibernate.annotation.entity.Student;
import org.hibernate.Session;

public class StudentRepository {


    private final Session session;

    public StudentRepository(Session session) {

        this.session = session;
    }


    public Student findById(Long id) {

        return (Student) session.get(Student.class, id);

    }

}
