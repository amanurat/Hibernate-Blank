package com.hibernate.annotation.repository;

import com.hibernate.annotation.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StudentRepositoryTest {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Session session;

    private StudentRepository studentRepository;
    @Before
    public void setUp() throws Exception {
        session = sessionFactory.openSession();

        studentRepository = new StudentRepository(session);
    }


    @After
    public void tearDown() throws Exception {
        session.close();

    }

    @Test
    public void testFindById() throws Exception {
        Student student = studentRepository.findById(1l);

        System.out.println(student);

        assertThat(student.getName(), is("first"));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Student> students = studentRepository.findAll();

        assertThat(students.size(), is(5));
    }

    @Test
    public void testUpdate() throws Exception {
        Student student = studentRepository.findById(1l);
        student.setName("first_updated");


        studentRepository.update(student);

        Student result = studentRepository.findById(1l);
        assertThat(result.getName(), is("first_updated"));

    }
}