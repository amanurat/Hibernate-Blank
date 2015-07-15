package com.hibernate.annotation.entity;

import com.hibernate.annotation.dao.EmployeeDao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Ignore;
import org.junit.Test;


public class EmployeeDaoTest {


    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private Session session;
    @Test
    @Ignore
    public void testGetByIdShouldFoundData() throws Exception {

        session = sessionFactory.openSession();
        Employee employee1 = (Employee) session.get(Employee.class, 1);
        System.out.println(employee1.getFirstName());

        EmployeeDao employeeDao = new EmployeeDao(session);
        Employee employee2 = employeeDao.readById(2);
        System.out.println("employee : "+ employee2.getFirstName());

    }


    @Test
    @Ignore
    public void testDeleteEmployee() throws Exception {

        session = sessionFactory.openSession();

        EmployeeDao employeeDao = new EmployeeDao(session);
        employeeDao.delete(employeeDao.readById(2));

    }
}
