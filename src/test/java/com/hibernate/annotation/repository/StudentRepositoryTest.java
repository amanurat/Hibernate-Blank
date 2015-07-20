package com.hibernate.annotation.repository;

import com.hibernate.annotation.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StudentRepositoryTest {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Session session;


    @Before
    public void setUp() throws Exception {
        session = sessionFactory.openSession();
    }


    @After
    public void tearDown() throws Exception {
        session.close();

    }

    @Test
    public void testFindById() throws Exception {

        StudentRepository studentRepository = new StudentRepository(session);
        Student student = studentRepository.findById(1l);

        System.out.println(student);

        assertThat(student.getName(), is("first"));


    }
}