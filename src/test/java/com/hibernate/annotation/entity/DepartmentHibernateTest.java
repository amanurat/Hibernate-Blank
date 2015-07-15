package com.hibernate.annotation.entity;

import com.hibernate.annotation.dao.DepartmentDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class DepartmentHibernateTest {


    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private Session session;

    @Before
    public void setup() throws Exception {
        this.session = sessionFactory.openSession();
    }

    @After
    public void tearDown() throws Exception {
        this.session.close();
    }

    @Test
    public void testDao() throws Exception {
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.setSession(session);
        List<Department> all = departmentDao.findAll();
        System.out.println("findAll : "+ all);
        System.out.println("findById : "+ departmentDao.findById(1));
        departmentDao.makePersistent(new Department("SALE"));
        System.out.println("find last record : "+ departmentDao.findById(3));

    }

    @Test
//    @Ignore
    public void testCreateDepartment() throws Exception {
        session.getTransaction().begin();
        session.save(new Department("IT"));
        session.save(new Department("HR"));
        session.getTransaction().commit();
    }


    @Test
    public void saveOrUpdate() throws Exception {

        Department department = (Department) session.get(Department.class, 1);
        department.setName("IT");
        doSaveOrUpdate(department);


        doSaveOrUpdate(new Department("Finance"));

    }

    private void doSaveOrUpdate(Department department) {

        session.getTransaction().begin();

        session.saveOrUpdate(department);

        session.getTransaction().commit();
    }

    @Test
    public void getDepartment() throws Exception {
        Department department = (Department) session.get(Department.class, 1);
        System.out.println("department : "+ department.getName());
        List<Employee> employees = department.getEmployees();
        for (Employee employee : employees) {
            System.out.println("employee name : "+ employee.getFirstName());
            System.out.println("employee salary : "+ employee.getSalary());
        }
    }


    @Test
    public void testDeleteDepartment() throws Exception {
        session.getTransaction().begin();
        Department department = (Department)session.get(Department.class, 1);

        if (department != null) {
            session.delete(department);
        }

        session.getTransaction().commit();
    }
}
