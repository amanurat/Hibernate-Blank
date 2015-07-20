package com.hibernate.annotation.repository;

import com.hibernate.annotation.entity.Student;
import org.hibernate.Session;

import java.util.List;

public class StudentRepository {


    private final Session session;

    public StudentRepository(Session session) {
        this.session = session;
    }

    public Student findById(Long id) {
        return (Student) session.get(Student.class, id);
    }


    public List<Student> findAll() {
        return session.createQuery("from Student").list();
    }

    public void update(Student student) {
        session.getTransaction().begin();
        session.update(student);
        session.getTransaction().commit();
    }

    public void create(Student student) {
        session.getTransaction().begin();
        session.save(student);
        session.getTransaction().commit();
    }

    public void merge(Student student) {
        session.getTransaction().begin();
        session.saveOrUpdate(student);
        session.getTransaction().commit();
    }

    public void delete(Student student) {
        session.getTransaction().begin();
        session.delete(student);
        session.getTransaction().commit();
    }

    public void deleteById(Long id) {

        Student student = findById(id);

        if (student == null) {
            throw new IllegalArgumentException("Cannot found id " + id);
        }

        session.getTransaction().begin();
        session.delete(student);
        session.getTransaction().commit();

    }




}
