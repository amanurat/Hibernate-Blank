package com.hibernate.annotation.entity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

/**
 * Created by amanurat on 3/30/15 AD.
 */
public class StudentHibernateTest {


    SessionFactory sessionFactory = new Configuration()
            .configure().buildSessionFactory();


    @Test
    public void setup() throws Exception {

    }

    @Test
    public void testInsertStudent() throws Exception {

        Session session = sessionFactory.openSession();

        //Begin transaction
        session.getTransaction().begin();

        Student student = new Student();
        student.setName("aut");
        student.setEmail("aut@gmail.com");

        session.save(student);



        session.getTransaction().commit();

    }



}
