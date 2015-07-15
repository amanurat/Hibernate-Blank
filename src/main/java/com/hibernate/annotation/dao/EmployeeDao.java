package com.hibernate.annotation.dao;

import com.hibernate.annotation.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EmployeeDao {

    private Session session;

    public EmployeeDao(Session session) {
        this.session = session;
    }

    public void create(Employee employee) {
        session.getTransaction().begin();
        session.save(employee);

        session.getTransaction().commit();
    }



    public void update(Employee employee) {
        session.getTransaction().begin();
        session.update(employee);

        session.getTransaction().commit();
    }

    public Employee readById(Integer id) {
        Employee employee = (Employee) session.get(Employee.class, id);

        return employee;
    }

    public void delete(Integer id) throws Exception{
        Employee employee = readById(id);

        if (employee == null) {
            throw new Exception("Cannot found data in database");
        }

        session.getTransaction().begin();
        session.delete(employee);
        session.getTransaction().commit();

    }

    public void delete(Employee employee) {
        session.getTransaction().begin();
        session.delete(employee);
        session.getTransaction().commit();
    }



}
